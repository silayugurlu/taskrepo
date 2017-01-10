/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.impl;

import com.company.crawler.ParallelCrawler;
import com.company.crawler.core.Crawler;
import com.company.crawler.core.JsoupCrawler;
import com.company.crawler.model.CrawlerUrl;
import com.company.crawler.task.CrawlerThread;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author silay
 */
public class ParallelCrawlerImpl implements ParallelCrawler {

    private void prepareHazelCast() {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        Queue<CrawlerUrl> urlsToCrawl = instance.getQueue("urlsToCrawl");
        Queue<CrawlerUrl> crawledUrls = instance.getQueue("crawledUrls");

    }

    private HazelcastInstance createNewInstance(String instanceName) {
        Config cfg = new Config();
        cfg.setInstanceName(instanceName);
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);

        Queue<CrawlerUrl> urlsToCrawl = instance.getQueue("urlsToCrawl");
        return instance;
    }

    @Override
    public Queue<CrawlerUrl> crawl(String url, int depth) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        Crawler crawler = new JsoupCrawler();
//        List<CrawlerUrl> urls = new ArrayList<CrawlerUrl>();
//        urls.add(new CrawlerUrl(url, 0, ""));
//
//        CrawlerThread thread = new CrawlerThread(urls, depth);
//        executor.execute(thread);

        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IQueue<CrawlerUrl> crawledUrls = hz.getQueue("crawledUrls");

        CrawlerUrl crawlerUrl = new CrawlerUrl(url, 0, "");
        List<CrawlerUrl> results = crawler.crawl(crawlerUrl, "", null);
        IQueue<CrawlerUrl> urlsToCrawl = hz.getQueue("urlsToCrawl");
        for (CrawlerUrl result : results) {
            crawledUrls = hz.getQueue("crawledUrls");
            if (!crawledUrls.contains(result) && result.getDepth() < depth) {
                urlsToCrawl.add(result);
            }
        }

        crawledUrls.add(crawlerUrl);
        urlsToCrawl = hz.getQueue("urlsToCrawl");

        while (urlsToCrawl.size() > 0 || executor.getTaskCount() > 0) {

            urlsToCrawl = hz.getQueue("urlsToCrawl");

            if (urlsToCrawl.size() > 10) {
                // daha once ziyaret edilenler kontrol edilmeli

                List<CrawlerUrl> urls = new ArrayList<CrawlerUrl>();
                urlsToCrawl.drainTo(urls);
                Runnable worker = new CrawlerThread(urls, depth);
                executor.execute(worker);
            }

            CrawlerUrl urlToCrawl = urlsToCrawl.poll();
            if (urlToCrawl != null) {
                List<CrawlerUrl> urls = crawler.crawl(urlToCrawl, hz.getName(), null);

                for (CrawlerUrl result : urls) {
                    crawledUrls = hz.getQueue("crawledUrls");
                    if (!crawledUrls.contains(result) && result.getDepth() < depth) {
                        urlsToCrawl.add(result);
                    }
                }
            }
        }

        
        return hz.getQueue("urlsToCrawl");
    }

}

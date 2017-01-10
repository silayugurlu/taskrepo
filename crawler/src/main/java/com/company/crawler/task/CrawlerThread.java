/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.task;

import com.company.crawler.core.Crawler;
import com.company.crawler.core.JsoupCrawler;
import com.company.crawler.model.CrawlerUrl;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import java.util.List;

/**
 *
 * @author silay
 */
public class CrawlerThread implements Runnable {

    private List<CrawlerUrl> urlsToCrawl;
    private Crawler crawler;
    private int maximumDepth;

    public CrawlerThread(List<CrawlerUrl> urls, int depth) {

        this.urlsToCrawl = urls;
        this.maximumDepth = depth;
        crawler = new JsoupCrawler();
    }

    @Override
    public void run() {
        for (CrawlerUrl url : urlsToCrawl) {

            HazelcastInstance hz = Hazelcast.newHazelcastInstance();
            IQueue<CrawlerUrl> results = hz.getQueue("urlsToCrawl");
            IQueue<CrawlerUrl> crawledUrls = hz.getQueue("crawledUrls");
            crawledUrls.add(url);
            List<CrawlerUrl> urls = crawler.crawl(url, hz.getName(), null);

            for (CrawlerUrl result : urls) {
                if (!crawledUrls.contains(result) && result.getDepth() < maximumDepth) {
                    results.add(result);
                }
            }

        }
    }
}

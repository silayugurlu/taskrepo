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
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author silay
 */
public class ParallelCrawlerImpl implements ParallelCrawler {

    @Override
    public List<CrawlerUrl> crawl(String url, int depth) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        boolean newlyStarted = true;
        Crawler crawler = new JsoupCrawler();
//        List<CrawlerUrl> urls = crawler.crawl(new CrawlerUrl(url,0,""));

        while (urls.size()>0 || executor.getTaskCount()>0 ) {
          
            if (Hazelcast.urlstocrow.size() > 10) {
                // daha once ziyaret edilenler kontrol edilmeli

                //
                Runnable worker = new CrawlerThread(Hazelcast.urlstocrow.get(10));
                executor.execute(worker);
            }
        }
        
        if(Hazelcast.urlstocrow.size()>0){
            for(CrawlerUrl crawlerUrl : urlstocrow){
                crawler.crawl(new CrawlerUrl(crawlerUrl,0,""));
            }
        }

    }

}

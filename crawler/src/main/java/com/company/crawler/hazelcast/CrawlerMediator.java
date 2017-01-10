/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.hazelcast;

import com.company.crawler.model.CrawlerUrl;
import com.company.crawler.task.CrawlerThread;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author silay
 */
public class CrawlerMediator {

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    Hazelcast hc;
    int maximumDepth;

    public void addQueue(CrawlerUrl url) {

        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IQueue<CrawlerUrl> urlsToCrawl = hz.getQueue("urlsToCrawl");
        IQueue<CrawlerUrl> crawledUrls = hz.getQueue("crawledUrls");

        
//        crawledUrls.
        if (!crawledUrls.contains(url) && url.getDepth() < maximumDepth) {
            urlsToCrawl.add(url);
        }

    }

    public void consumer() {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IQueue<CrawlerUrl> urlsToCrawl = hz.getQueue("urlsToCrawl");
        IQueue<CrawlerUrl> crawledUrls = hz.getQueue("crawledUrls");
        
//        if(urlsToCrawl>10){
//            
//        }
//        
//        Runnable worker = new CrawlerThread(urlsToCrawl);
//        executor.execute(worker);
    }
}

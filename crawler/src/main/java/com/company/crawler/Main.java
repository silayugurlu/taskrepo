/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler;

import com.company.crawler.impl.ParallelCrawlerImpl;
import com.company.crawler.model.CrawlerUrl;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import java.io.Serializable;
import java.util.Queue;

/**
 *
 * @author silay.ugurlu
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
//        IExecutorService executor = hazelcastInstance.getExecutorService("exec");
//        System.out.println("INSTANCE NAME    "+ hazelcastInstance.getName());
////        for (int k = 1; k <= 1000; k++) {
////            Thread.sleep(1000);
////            System.out.println("Producing echo task: " + k);
//            executor.execute(new EchoTask("D"));
////        }

        ParallelCrawler parallelCrawler = new ParallelCrawlerImpl();
       Queue<CrawlerUrl> result =  parallelCrawler.crawl("http://gmail.com", 3);
       
       for(CrawlerUrl url : result){
           System.out.println(url.toString());
       }
        System.out.println("EchoTaskMain finished!");
    }

    public static class EchoTask implements Runnable, Serializable {

        private final String msg;

        public EchoTask(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            Config cfg = new Config();
            HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
            System.out.println("TASK   " + instance.getCluster().getLocalMember().toString() + ":");

            System.out.println("sub INSTANCE NAME    " + instance.getName());
        }
    }

}

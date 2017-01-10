/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.hazelcast;

import com.company.crawler.model.CrawlerUrl;
import com.company.crawler.task.CrawlerThread;
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
    public void addLink(CrawlerUrl url){
        if(!hc contains){
            add hc // synchronized method
        }
        
        if(hc.size > 10){
            createTask(hc get 10 synchmethod);
        }
    }
    
    
    public void createTask(List<CrawlerUrl> urlsToCrawl){
         Runnable worker = new CrawlerThread(urlsToCrawl);
                executor.execute(worker);
    }
}

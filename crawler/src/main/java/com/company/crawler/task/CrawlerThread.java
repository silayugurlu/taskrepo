/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.task;

import com.company.crawler.core.Crawler;
import com.company.crawler.core.JsoupCrawler;
import com.company.crawler.model.CrawlerUrl;
import java.util.List;

/**
 *
 * @author silay
 */
public class CrawlerThread implements Runnable{
    
    private List<CrawlerUrl> urlsToCrawl;
    private Crawler crawler;
    

    public CrawlerThread(List<CrawlerUrl> urls) {
        
        this.urlsToCrawl = urls;
        crawler = new JsoupCrawler();
    }

    
    @Override
    public void run() {
        for(CrawlerUrl url : urlsToCrawl){
            crawler.crawl(url);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.core;

import com.company.crawler.hazelcast.CrawlerMediator;
import com.company.crawler.model.CrawlerUrl;
import java.util.List;

/**
 *
 * @author ayhan
 */
public interface Crawler {
    
   /**
    * find urls in the page given as a url parameter
    * 
    * @param url
    * @return list of urls
    */
   List<CrawlerUrl> crawl(CrawlerUrl url,CrawlerMediator mediator);
}

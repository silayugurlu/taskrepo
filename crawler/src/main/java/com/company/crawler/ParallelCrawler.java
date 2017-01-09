/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler;

import com.company.crawler.model.CrawlerUrl;
import java.util.List;

/**
 *
 * @author silay
 */
public interface ParallelCrawler {
    
    
     List<CrawlerUrl> crawl(String url, int depth);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler;

import com.company.crawler.impl.ParallelCrawlerImpl;
import com.company.crawler.model.CrawlerUrl;
import java.util.Queue;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author silay.ugurlu
 */
public class ParallelCrawlerTest {

    @Test
    public void testParallelCrawl() {

        String url = "";
        int depth = 0;
        ParallelCrawler crawler = new ParallelCrawlerImpl();

        Queue<CrawlerUrl> results = crawler.crawl(url, depth);

        for (CrawlerUrl crawlerUrl : results) {
            System.out.println(url.toString());
            assertTrue("Exceeded given depth",crawlerUrl.getDepth() <= depth);
        }
    }

}

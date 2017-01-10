/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.core;

import com.company.crawler.model.CrawlerUrl;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.when;

/**
 *
 * @author silay
 */
public class JsoupCrawlerTest {

    @Test
    public void testCrawl() {
        try {
            JsoupCrawler crawler = new JsoupCrawler();
            
            File input = new File("C:\\personal\\repo\\taskrepo\\test.html");
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            
//            when(crawler.getDocumentHelper("http://example.com/")).thenReturn(doc);
            List<CrawlerUrl> urls = crawler.crawl(new CrawlerUrl("http://example.com/",0,""), "", null);
            Assert.assertEquals(1,urls.size());
        } catch (IOException ex) {
            Logger.getLogger(JsoupCrawlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

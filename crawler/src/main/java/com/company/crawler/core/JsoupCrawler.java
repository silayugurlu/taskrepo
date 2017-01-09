/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.core;

import com.company.crawler.model.CrawlerUrl;
import java.io.IOException;
import java.util.List;
import org.jsoup.Connection;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author silay
 */
public class JsoupCrawler implements Crawler {

    @Override
    public List<CrawlerUrl> crawl(CrawlerUrl url) {
        try {

            Connection connection = Jsoup.connect(url)
            Document doc = connection.get();
            Elements linksOnPage = doc.select("a[href]");
            Elements importsOnPage = doc.select("link[href]");

            
            
//            if (connection.response().statusCode() == 200) // 200 is the HTTP OK status code
//            // indicating that everything is great.
//            {
//                System.out.println("\n**Visiting** Received web page at " + url);
//            }
//            if (!connection.response().contentType().contains("text/html")) {
//                System.out.println("**Failure** Retrieved something other than HTML");
//                return null;
//            }
//            Elements linksOnPage = htmlDocument.select("a[href]");
//            System.out.println("Found (" + linksOnPage.size() + ") links");
            for (Element link : linksOnPage) {
//                this.links.add(link.absUrl("href"));
            }
            return null;
        } catch (IOException ioe) {
            // We were not successful in our HTTP request
            return null;
        }
//    }

}

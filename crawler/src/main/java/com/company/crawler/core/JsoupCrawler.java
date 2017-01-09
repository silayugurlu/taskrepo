/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.core;

import com.company.crawler.model.CrawlerUrl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;

import org.jsoup.Jsoup;
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
        List<CrawlerUrl> urlsToCrawl = new ArrayList<CrawlerUrl>();
        try {

            Connection connection = Jsoup.connect(url.getUrl()); // connect url with jsoup

            Document doc = connection.get();   //download document from connection
            Elements linksOnPage = doc.select("a[href]"); //get links in document 

            int urlCount = linksOnPage.size();
            url.setUrlCount(urlCount); // set count of links in the page with given url

            /*add each url to the list*/
            for (Element link : linksOnPage) {

                CrawlerUrl urlToCrawl = new CrawlerUrl();
                urlToCrawl.setUrl(link.absUrl("href"));
                urlsToCrawl.add(urlToCrawl);
            }
        } catch (IOException ex) {
            Logger.getLogger(JsoupCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return urlsToCrawl;
    }

}

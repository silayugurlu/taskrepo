/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.core;

import com.company.crawler.hazelcast.CrawlerMediator;
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

    public Document getDocumentHelper(String globalScheduleURL) throws IOException {
        return Jsoup.connect(globalScheduleURL).get();
    }

    @Override
    public List<CrawlerUrl> crawl(CrawlerUrl url, String instanceName, CrawlerMediator mediator) {
        List<CrawlerUrl> urlsToCrawl = new ArrayList<CrawlerUrl>();
        try {

//            Connection connection = Jsoup.connect(url.getUrl()); // connect url with jsoup

            Document doc = getDocumentHelper(url.getUrl());   //download document from connection
            Elements linksOnPage = doc.select("a[href]"); //get links in document 

            int urlCount = linksOnPage.size();
            url.setUrlCount(urlCount); // set count of links in the page with given url
            int depth = url.getDepth() + 1;
            /*add each url to the list*/

            for (Element link : linksOnPage) {

                String hrefStr = link.absUrl("href");
                if (!hrefStr.isEmpty()) {
                    CrawlerUrl urlToCrawl = new CrawlerUrl();
                    urlToCrawl.setUrl(hrefStr);
                    urlToCrawl.setDepth(depth);
                    urlToCrawl.setInstanceName(instanceName);
                    urlsToCrawl.add(urlToCrawl);
//                    System.out.println(urlToCrawl.toString());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(JsoupCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return urlsToCrawl;
    }

}

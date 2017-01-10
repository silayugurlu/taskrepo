/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.model;

import java.io.Serializable;

/**
 *
 * @author silay
 */
public class CrawlerUrl implements Serializable {

    private String url;

    private int depth;

    private int urlCount;

    private String instanceName;

    public CrawlerUrl() {
    }

    public CrawlerUrl(String urlStr, int depthOfUrl, String crawlerInstanceName) {
        this.url = urlStr;
        this.depth = depthOfUrl;
        this.instanceName = crawlerInstanceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String urlStr) {
        this.url = urlStr;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depthOfUrl) {
        this.depth = depthOfUrl;
    }

    public int getUrlCount() {
        return urlCount;
    }

    public void setUrlCount(int urlTotalCount) {
        this.urlCount = urlTotalCount;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String crawlerInstanceName) {
        this.instanceName = crawlerInstanceName;
    }

    public int hashCode() {
        return url.hashCode();
    }

    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CrawlerUrl)) {
            return false;
        }

        CrawlerUrl crawlerUrl = (CrawlerUrl) obj;
        return crawlerUrl.getUrl().equals(url);

    }
    
    public String toString(){
        return "URL: "+url+" - instance name: "+instanceName+" - depth: "+depth;
    }
}

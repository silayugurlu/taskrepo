/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.crawler.model;

/**
 *
 * @author silay
 */
public class CrawlerUrl {

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
        this.url = url;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depthOfUrl) {
        this.depth = depth;
    }

    public int getUrlCount() {
        return urlCount;
    }

    public void setUrlCount(int urlTotalCount) {
        this.urlCount = urlCount;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String crawlerInstanceName) {
        this.instanceName = instanceName;
    }

}

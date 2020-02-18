package com.pegasus.indexation.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author hongyu.ma
 *
 */
public class GetResponse implements Serializable {

    private static final long serialVersionUID = -7081805896943833645L;
    private List<String> urls;

    /**
     * @return the urls.
     */
    public List<String> getUrls() {
        return urls;
    }

    /**
     * @param urls the urls to set.
     */
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}

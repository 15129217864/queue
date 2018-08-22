package com.xct.media.queuing.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 创建配置存储更路径
 * Created by Chris on 2018/5/8.
 */
@ConfigurationProperties("storage")
public class StorageProperties {


    private String location = "queue";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

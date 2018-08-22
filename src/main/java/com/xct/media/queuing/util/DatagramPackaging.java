package com.xct.media.queuing.util;

/**
 * 报文数据封装
 * <p>
 * Created by Chris on 2018/5/1.
 */
public class DatagramPackaging<T> {

    // 类别
    private String category;

    //服务器ip和端口
    private String ipAndPort;

    // 包文内容
    private T datagram;


    public String getIpAndPort() {
        return ipAndPort;
    }

    public void setIpAndPort(String ipAndPort) {
        this.ipAndPort = ipAndPort;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public T getDatagram() {
        return datagram;
    }

    public void setDatagram(T datagram) {
        this.datagram = datagram;
    }
}

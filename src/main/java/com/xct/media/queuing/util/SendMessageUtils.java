/*
 * Copyright 2015-2016 XCT group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xct.media.queuing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

/**
 * xct-api-queue
 *
 * @author yuChao
 * @Date 2016年12月9日-下午9:57:10
 * @Desc 发送消息公用方法
 * @Since 1.8
 */
public class SendMessageUtils {

    private static Logger logger = LoggerFactory.getLogger(SendMessageUtils.class);

    /**
     * @param ip
     * @param message
     * @param port
     */
    public static void sendUdpDatagramPacket(String ip, String command, String message, int port) {
        logger.info("send ip-[" + ip + "] port-[" + port + "] message-[" + message + "] .");
        DatagramSocket socket = null;
        try {

            // 加密message
            String msg = DESPlusUtil.Get().encrypt(command + message);
            byte[] data = msg.getBytes();
            // 封装报文数据包
            DatagramPacket dp = new DatagramPacket(data, data.length);
            socket = new DatagramSocket();
            dp.setAddress(InetAddress.getByName(ip));
            dp.setPort(port);
            // 推送udp包
            socket.send(dp);
        } catch (SocketException e) {
            logger.error("send udp ---- socket exception {}", e.getMessage());
        } catch (UnknownHostException e) {
            logger.error("send udp ---- unknown host exception {}", e.getMessage());
        } catch (IOException e) {
            logger.error("send udp ---- io exception {}", e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

}

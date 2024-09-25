/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy.server.method.response.impl;

import com.nhnacademy.server.method.response.Response;
import com.nhnacademy.server.runable.MessageServer;
import com.nhnacademy.server.thread.channel.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

@Slf4j
public class BroadCastResponse implements Response {
    @Override
    public String getMethod() {
        //TODO#1-1 method = "broadcast" 설정 합니다.
        return "broadcast";
    }

    @Override
    public String execute(String value) {

        /* TODO#1-2 MessageServer.getClientIds() 해당되는 모든 client에게 message를 전송 합니다.
            - PrintWriter를  사용해서 각 client에게 응답 합니다.
            - 응답시 sendCount가 증가 됩니다.
            - value 값은 client에게 전송할 message 입니다.
         */

        List<String> ids = MessageServer.getClientIds();
        int sendCount=0;

        for(String id : ids){
            Socket client = MessageServer.getClientSocket(id);

            if(client.isClosed()){
                if(Session.isLogin()){
                    MessageServer.removeClient(Session.getCurrentId());
                }
                continue;
            }

            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                out.println(value);
                sendCount++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        

        return String.format("{%d}명에게 \"{%S}\"를 전송 했습니다.",sendCount,value);
    }
}
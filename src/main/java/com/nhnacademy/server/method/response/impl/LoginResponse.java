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

import java.util.List;

public class LoginResponse implements Response {
    @Override
    public String getMethod() {
        //TODO#4-1 method = login 설정 합니다.
        return "login";
    }

    @Override
    public String execute(String value) {
        /*TODO#4-2 login list 형태로 호출하면 , 즉 value == "list" 이면 로그인된 전체 id를 출력 합니다.
            login list

            marco
            nhnacademy
            ann
            jone
            landy
            ...
        */

        if(value.equals("list")){
            List<String> list = MessageServer.getClientIds();

            return list.size() > 0 ? String.join(System.lineSeparator(), list) : "empty";

        }

        /*TODO#4-3 MessageServer.addClient()를 이용해서 clientMap에 client Socket을 추가 합니다.
            client Socket은 Session을 이용해서 획득할 수 있습니다.
        */
        boolean loginSuccess = MessageServer.addClient(value, Session.getCurrentSocket());



        /*TODO#4-4 loginSuccess == true 이면 Session.initializeId를 호출해서 현제 clinet의 id를 설정 합니다.
             - login success!를 client에게 반환 합니다.
        */
        //TODO#4-5 loginSuccess == false이면 "login fail!" 반환 합니다.
        if(loginSuccess){
            Session.initializeId(value);
            return "login success";
        }
        return "login fail!";

    }
}

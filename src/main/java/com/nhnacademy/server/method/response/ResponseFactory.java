package com.nhnacademy.server.method.response;

import com.nhnacademy.server.method.response.exception.ResponseNotFoundException;
import com.nhnacademy.server.method.response.impl.EchoResponse;
import com.nhnacademy.server.method.response.impl.PortResponse;
import com.nhnacademy.server.method.response.impl.TimeResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
public class ResponseFactory {
    private static final ArrayList<Response> responseList = new ArrayList<>(){{
        add(new EchoResponse());
        add(new TimeResponse());
        //TODO#2 PortResponse 생성 후 추가 합니다.

    }};

    public static Response getResponse(String method){
        Response response = responseList.stream()
                .filter(o->o.validate(method))
                .findFirst()
                .orElse(null);

        if(Objects.isNull(response)){
            log.error("response not found : {}",method);
        }
        return response;
    }
}

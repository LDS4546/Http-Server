package com.nhnacademy.server.main.method.response;

import com.nhnacademy.server.main.method.response.exception.ResponseNotFoundException;
import com.nhnacademy.server.main.method.response.impl.EchoResponse;
import com.nhnacademy.server.main.method.response.impl.PortResponse;
import com.nhnacademy.server.main.method.response.impl.TimeResponse;

import java.util.ArrayList;
import java.util.Objects;

public class ResponseFactory {
    private static final ArrayList<Response> responseList = new ArrayList<>(){{
        add(new EchoResponse());
        add(new PortResponse());
        add(new TimeResponse());
    }};

    public static Response getResponse(String method){
        Response response = responseList.stream()
                .filter(o->o.validate(method))
                .findFirst()
                .orElse(null);
        if(Objects.isNull(response)){
            throw new ResponseNotFoundException();
        }
        return response;
    }
}

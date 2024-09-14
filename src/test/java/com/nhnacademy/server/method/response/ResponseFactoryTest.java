package com.nhnacademy.server.method.response;

import com.nhnacademy.server.method.response.exception.ResponseNotFoundException;
import com.nhnacademy.server.method.response.impl.EchoResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResponseFactoryTest {

    @Test
    @DisplayName("getResponse By method")
    void getResponse() {
        Response response = ResponseFactory.getResponse("echo");
        //TODO#1-18 response가 EchoResponse의 구현체인지 검증 합니다.
        Assertions.assertInstanceOf(EchoResponse.class, response);
    }

    @Test
    @DisplayName("getResponse by something")
    void getResponseByNotExistMethodName(){

        //TODO#1-19 존재하지 않는 response를 요청했을 때 ResponseNotFoundException가 발생하는지 검증 합니다.
        Assertions.assertThrows(ResponseNotFoundException.class,()->{
            ResponseFactory.getResponse("something");
        });
    }
}
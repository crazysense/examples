package com.bistel.a3.cube;

import com.bistel.a3.common.data.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class TestClass implements Serializable {

    private static final Logger U = LoggerFactory.getLogger(TestClass.class);

    public Message func1(Message message) {
        U.info("Function-1 : " + message.toString());
        InnerData data = new InnerData("parsed-" + message.getKey(), "parsed-" + message.getValue());
        System.out.println(data);
        return new Message(data.getKey(), data.getValue());
    }

    public Message func2(Message message) {
        U.info("Function-2 : " + message.toString());
        return message;
    }

}

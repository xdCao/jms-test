package rocketmq;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RocketService {

    @Autowired
    private RocketSpringProducer producer;

    @Autowired
    private RocketSpringConsumer consumer;

    public void sendMessage(String body){

        Message msg=new Message("MyTopic","MyTag",body.getBytes());

        SendResult sendResult=null;

        try {
            sendResult=producer.getDefaultMQProducer().send(msg);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (sendResult==null||sendResult.getSendStatus()!=SendStatus.SEND_OK){

            //...

        }else {
            System.out.println("发送成功: "+ body);
        }

    }




}

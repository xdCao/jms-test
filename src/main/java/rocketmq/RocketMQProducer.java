package rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

public class RocketMQProducer {

    public static void main(String[] args) throws MQClientException {

        DefaultMQProducer producer=new DefaultMQProducer("Producer1");

        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        for (int i = 0; i < 10; i++) {

            try {
                Message msg=new Message("TopicTest","TagA",("Hello RocketMQ"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult=producer.send(msg);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        producer.shutdown();

    }

}

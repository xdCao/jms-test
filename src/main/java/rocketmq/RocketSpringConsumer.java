package rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class RocketSpringConsumer {

    private DefaultMQPushConsumer defaultMQPushConsumer;

    private String namesrvAddr;

    private String consumerGroup;

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public void init() throws MQClientException {

        defaultMQPushConsumer=new DefaultMQPushConsumer(consumerGroup);

        defaultMQPushConsumer.setNamesrvAddr(namesrvAddr);

        defaultMQPushConsumer.setInstanceName(String .valueOf(System.currentTimeMillis()));

        defaultMQPushConsumer.subscribe("MyTopic","MyTag");

        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);

        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                MessageExt msg=msgs.get(0);

                if (msg.getTopic().equals("MyTopic")){
                    if (msg.getTags()!=null&&msg.getTags().equals("MyTag")){
                        System.out.println("收到消息： "+new String(msg.getBody()));
                    }
                }


                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        defaultMQPushConsumer.start();

    }


    public void destroy(){
        defaultMQPushConsumer.shutdown();
    }


}

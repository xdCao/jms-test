package rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

public class RocketSpringProducer {

    private DefaultMQProducer defaultMQProducer;

    private String producerGroup;

    private String namesrvAddr;

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public DefaultMQProducer getDefaultMQProducer() {
        return defaultMQProducer;
    }

    public void init() throws MQClientException {

        defaultMQProducer=new DefaultMQProducer(producerGroup);

        defaultMQProducer.setNamesrvAddr(namesrvAddr);

        defaultMQProducer.setInstanceName(String.valueOf(System.currentTimeMillis()));

        defaultMQProducer.start();

    }


    public void destroy(){

        defaultMQProducer.shutdown();

    }


}

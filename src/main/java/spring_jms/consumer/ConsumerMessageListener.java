package spring_jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {


    public void onMessage(Message message) {

        TextMessage message1= (TextMessage) message;
        try {
            System.out.println(message1.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }


}

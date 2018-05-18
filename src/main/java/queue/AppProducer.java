package queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducer {


    private static final String url="tcp://127.0.0.l:61616";

    private static final String queueName="queue-test";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);

        Connection connection=connectionFactory.createConnection();

        connection.start();

        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//是否使用事务

        Destination destination = session.createQueue(queueName);


        //创建生产者

        MessageProducer producer=session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            TextMessage textMessage=session.createTextMessage("test"+i);
            producer.send(textMessage);

            System.out.println("发送消息"+textMessage.getText());
        }

        //关闭连接

        connection.close();

    }

}

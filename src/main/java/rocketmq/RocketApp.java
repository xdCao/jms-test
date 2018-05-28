package rocketmq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RocketApp {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("rocketmq.xml");
        RocketService bean = context.getBean(RocketService.class);
        bean.sendMessage("哈哈哈");

    }

}

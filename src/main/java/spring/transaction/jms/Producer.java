package spring.transaction.jms;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Producer {

    private static Logger logger = Logger.getLogger(Producer.class);
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://reyjyype:iWR8wTKrx7smy5KjqLV9pWUWO7-NHRhh@spotted-monkey.rmq.cloudamqp.com/reyjyype");
        factory.setRequestedHeartbeat(30);
        factory.setConnectionTimeout(30000);

        Connection connection = factory.newConnection(); Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";

        ScheduledExecutorService ex = Executors.newScheduledThreadPool(2);
        ex.scheduleWithFixedDelay(() -> {
            try {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(" [x] Sent '" + message + "'");
        }, 10, 10, TimeUnit.SECONDS);
    }
}

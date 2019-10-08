package spring.transaction.jms;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Consumer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://reyjyype:iWR8wTKrx7smy5KjqLV9pWUWO7-NHRhh@spotted-monkey.rmq.cloudamqp.com/reyjyype");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(Producer.QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        System.out.println(declareOk);

        DeliverCallback callback = (consumerTag, message) -> {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println("msq: " + msg);
        };

        channel.basicConsume(Producer.QUEUE_NAME, true, callback, (consumerTag) -> {});

    }
}

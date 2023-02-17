package com.example.demo;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.autoconfigure.data.mongo.ReactiveStreamsMongoClientDependsOnBeanFactoryPostProcessor;

public class Sender {

    private static String NAME_QUEUE = "HELLO";

    public static void main (String[] args0) throws Exception {
        // First create a connection
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);

       try(Connection connection = factory.newConnection()){
        // System.out.println(connection.hashCode());

        // create a new channel
        Channel channel = connection.createChannel();
        System.out.println(channel);

        // declared a row that will be used
        // row name, exclusive, autodelete, durable, map(args)
        channel.queueDeclare(NAME_QUEUE, false, false, false, null);

        // create a message
        String message = "Hello world, this is my first Spring program.";

        //Send message
        channel.basicPublish("", NAME_QUEUE, null, message.getBytes());
        System.out.println("[x] Sent '" + message + "'");


    }
    }
}

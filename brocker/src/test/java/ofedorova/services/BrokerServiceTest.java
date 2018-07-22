package ofedorova.services;

import ofedorova.entity.Message;
import ofedorova.entity.Subscriber;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BrokerServiceTest {

    private BrokerService brokerService;

    @Before
    public void init() {
        brokerService = new BrokerService(new SenderMessage());
    }

    @Test
    public void whenAddSubscriberTrue() {
        final String topic = "topic1";
        final Subscriber subscriber = new Subscriber();
        subscriber.setAddress("address1");
        subscriber.setEndpoint("endpoint1");

        Assert.assertTrue(brokerService.addSubscriber(topic, subscriber));
    }

    @Test
    public void whenAddSubscriberFalse() {
        final String topic = "topic1";
        final Subscriber subscriber1 = new Subscriber();
        subscriber1.setAddress("address1");
        subscriber1.setEndpoint("endpoint1");
        brokerService.addSubscriber(topic, subscriber1);

        final Subscriber subscriber2 = new Subscriber();
        subscriber2.setAddress("address1");
        subscriber2.setEndpoint("endpoint1");

        Assert.assertFalse(brokerService.addSubscriber(topic, subscriber2));
    }

    @Test
    public void whenRemoveSubscriberTrue() {
        final String topic = "topic1";
        final Subscriber subscriber1 = new Subscriber();
        subscriber1.setAddress("address1");
        subscriber1.setEndpoint("endpoint1");
        brokerService.addSubscriber(topic, subscriber1);

        Assert.assertTrue(brokerService.removeSubscriber(topic, subscriber1));
    }

    @Test
    public void whenRemoveSubscriberFalseAndNotSubscribe() {
        final String topic = "topic1";
        final Subscriber subscriber1 = new Subscriber();
        subscriber1.setAddress("address1");
        subscriber1.setEndpoint("endpoint1");

        Assert.assertFalse(brokerService.removeSubscriber(topic, subscriber1));
    }

    @Test
    public void whenRemoveSubscriberFalse() {
        final String topic = "topic1";
        final Subscriber subscriber1 = new Subscriber();
        subscriber1.setAddress("address1");
        subscriber1.setEndpoint("endpoint1");
        brokerService.addSubscriber(topic, subscriber1);

        final Subscriber subscriber2 = new Subscriber();
        subscriber2.setAddress("address2");
        subscriber2.setEndpoint("endpoint2");

        Assert.assertFalse(brokerService.removeSubscriber(topic, subscriber2));
    }


    @Test
    public void addMessage() {
        String topic = "topic1";
        final Subscriber subscriber1 = new Subscriber();
        subscriber1.setAddress("address1");
        subscriber1.setEndpoint("endpoint1");
        brokerService.addSubscriber(topic, subscriber1);

        final Message message = new Message(topic, "message");
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        brokerService.addMessage(message);
        Assert.assertEquals(String.format("%s, %s%s", subscriber1, message, System.lineSeparator()),
                new String(out.toByteArray()));
    }

}
package ofedorova.services.publishers;

import ofedorova.entity.Message;
import ofedorova.services.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Заглушка, которая генерирует сообщения по таймеру для топиков "topic2", "topic4", "topic6".
 */
@Component
public class StubPublisherEven implements Publisher {

    private final List<String> topics = Arrays.asList("topic2", "topic4", "topic6");
    private final Random rand = new Random();
    private final BrokerService brokerService;

    @Autowired
    public StubPublisherEven(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @Scheduled(fixedRate = 2000)
    @Override
    public void publish() {
        brokerService.addMessage(generateMessage());
    }

    private Message generateMessage() {
        return new Message(topics.get(rand.nextInt(topics.size())),
                "Message from StubPublisherEven!");
    }
}

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
 * Заглушка, которая генерирует сообщения по таймеру для топиков "topic1", "topic3", "topic5".
 */
@Component
public class StubPublisherOdd implements Publisher {

    private final List<String> topics = Arrays.asList("topic1", "topic3", "topic5");
    private final Random rand = new Random();
    private final BrokerService brokerService;

    @Autowired
    public StubPublisherOdd(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @Scheduled(fixedRate = 1000)
    @Override
    public void publish() {
        brokerService.addMessage(generateMessage());
    }

    private Message generateMessage() {
        return new Message(topics.get(rand.nextInt(topics.size())),
                "Message from StubPublisherOdd!");
    }
}

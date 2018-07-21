package ofedorova.services;

import ofedorova.entity.Subscriber;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Заглушка, генерирующая подписку/отписку на различные топики.
 */
@Component
public class StubRegister {

    private final List<String> topics = Arrays.asList("topic1", "topic2", "topic3", "topic4", "topic5", "topic6");
    private final List<Subscriber> subscribers = Arrays.asList(new Subscriber("address1", "endpoint1"),
            new Subscriber("address2", "endpoint2"), new Subscriber("address3", "endpoint3"), new Subscriber("address4", "endpoint4"),
            new Subscriber("address5", "endpoint5"), new Subscriber("address6", "endpoint6"));
    private final Random rand = new Random();
    private RestTemplate restTemplate = new RestTemplate();


    @Scheduled(fixedRate = 500)
    public void subscribe() {
        restTemplate.postForLocation(String.format("http://localhost:8080/subscribe/%s",
                topics.get(rand.nextInt(topics.size()))),
                subscribers.get(rand.nextInt(subscribers.size())));
    }

    @Scheduled(fixedRate = 600)
    public void unsubscribe() {
        restTemplate.postForLocation(String.format("http://localhost:8080/unsubscribe/%s",
                topics.get(rand.nextInt(topics.size()))),
                subscribers.get(rand.nextInt(subscribers.size())));
    }

}

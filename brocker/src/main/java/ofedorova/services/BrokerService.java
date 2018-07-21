package ofedorova.services;

import ofedorova.entity.Message;
import ofedorova.entity.Subscriber;
import org.apache.commons.collections4.SetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Брокер-сервис.
 */
@Service
public class BrokerService {

    /**
     * Логгер.
     */
    private final Logger logger = LoggerFactory.getLogger(BrokerService.class);
    /**
     * Сервис для отпрвки сообщений.
     */
    private final SenderMessage senderMessage;
    /**
     * Подписчики по топикам.
     */
    private final Map<String, Set<Subscriber>> subscribersByTopic = new ConcurrentHashMap<>();


    @Autowired
    public BrokerService(SenderMessage senderMessage) {
        this.senderMessage = senderMessage;
    }

    /**
     * Метод добавляет подписчика.
     *
     * @param topic      топик
     * @param subscriber подписчик
     * @return
     */
    public boolean addSubscriber(String topic, Subscriber subscriber) {
        logger.info("Subscribe {} on {}", subscriber, topic);
        return subscribersByTopic.computeIfAbsent(topic, (k) -> ConcurrentHashMap.newKeySet())
                .add(subscriber);
    }

    /**
     * Метод удаляет подписчика.
     *
     * @param topic      топик
     * @param subscriber подписчик
     * @return
     */
    public boolean removeSubscriber(String topic, Subscriber subscriber) {
        logger.info("Unsubscribe {} on {}", subscriber, topic);
        return SetUtils.emptyIfNull(subscribersByTopic.get(topic)).remove(subscriber);
    }

    /**
     * Отправка сообщений подписчикам.
     *
     * @param message
     */
    public void addMessage(Message message) {
        SetUtils.emptyIfNull(this.subscribersByTopic.get(message.getTopic())).parallelStream()
                .forEach(subscriber -> senderMessage.send(subscriber, message));
    }

}

package ofedorova.controllers;

import ofedorova.entity.Subscriber;
import ofedorova.services.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API для брокер-сервиса.
 */
@RestController
public class BrokerApi {

    private final BrokerService brokerService;

    @Autowired
    public BrokerApi(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    /**
     * Подписка на топик
     *
     * @param topic      топик
     * @param subscriber информация о подписчике
     */
    @PostMapping(value = "/subscribe/{topic}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void subscribe(@PathVariable(value = "topic") String topic, @RequestBody Subscriber subscriber) {
        brokerService.addSubscriber(topic, subscriber);
    }

    /**
     * Отписка от топика
     *
     * @param topic      топик
     * @param subscriber информация о подписчике
     */
    @PostMapping(value = "/unsubscribe/{topic}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void unsubscribe(@PathVariable(value = "topic") String topic, @RequestBody Subscriber subscriber) {
        brokerService.removeSubscriber(topic, subscriber);
    }

}

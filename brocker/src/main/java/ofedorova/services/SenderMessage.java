package ofedorova.services;

import ofedorova.entity.Message;
import ofedorova.entity.Subscriber;
import org.springframework.stereotype.Service;

/**
 * Сервисный класс, который отвечает за логику отправки сообщений подписчикам.
 */
@Service
public class SenderMessage {

    /**
     * Отправка сообщения подписчику.
     *
     * @param subscriber - подписчик
     * @param message    - сообщение
     */
    public void send(Subscriber subscriber, Message message) {
        System.out.println(String.format("%s, %s", subscriber, message));
    }

}

package ofedorova.entity;

/**
 * Модель сообщения.
 */
public class Message {

    /**
     * Тема сообщения.
     */
    private final String topic;
    /**
     * Тело сообщения.
     */
    private final String body;

    public Message(String topic, String body) {
        this.topic = topic;
        this.body = body;
    }

    public String getTopic() {
        return topic;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "topic='" + topic + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

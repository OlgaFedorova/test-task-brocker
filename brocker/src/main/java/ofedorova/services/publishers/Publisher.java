package ofedorova.services.publishers;

/**
 * Источник доставки сообщений.
 */
public interface Publisher {

    /**
     * Публикация сообщения в брокер.
     */
    void publish();

}

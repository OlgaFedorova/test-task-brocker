package ofedorova.entity;

/**
 * Информация о подписчике.
 */
public class Subscriber {

    /**
     * Адрес подписчика.
     */
    private String address;
    /**
     * Точка, на которой принимается сообщение.
     */
    private String endpoint;

    public Subscriber(String address, String endpoint) {
        this.address = address;
        this.endpoint = endpoint;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

}

package ofedorova.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(endpoint, that.endpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, endpoint);
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "address='" + address + '\'' +
                ", endpoint='" + endpoint + '\'' +
                '}';
    }
}

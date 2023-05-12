package tf;

import com.sun.glass.ui.Size;

public class Client {

    private String type;
    private String clients;

    private Integer size;
    private Boolean isAon;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClients() {
        return clients;
    }

    public void setClients(String clients) {
        this.clients = clients;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getAon() {
        return isAon;
    }

    public void setAon(Boolean aon) {
        isAon = aon;
    }
}

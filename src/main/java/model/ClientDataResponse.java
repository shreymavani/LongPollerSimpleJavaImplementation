package model;

public class ClientDataResponse {
    private String id;
    private String data;
    public ClientDataResponse(String id, String data){
        this.data = data;
        this.id = id;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

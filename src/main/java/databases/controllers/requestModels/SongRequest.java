package databases.controllers.requestModels;

public class SongRequest {

    private String name;

    public SongRequest(){}

    public SongRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package cr.ac.ucr.paraiso.autumnmobile.models;

import java.io.Serializable;

public class Observation implements Serializable{
    private int id;
    private String content;

    public Observation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

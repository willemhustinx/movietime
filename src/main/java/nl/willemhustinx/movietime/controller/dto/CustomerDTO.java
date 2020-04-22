package nl.willemhustinx.movietime.controller.dto;

import java.util.HashMap;
import java.util.Map;

public class CustomerDTO {

    private long id;

    private String name;

    private Map<String, String> interests = new HashMap<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getInterests() {
        return interests;
    }

    public void setInterests(Map<String, String> interests) {
        this.interests = interests;
    }
}

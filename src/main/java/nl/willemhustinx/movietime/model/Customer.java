package nl.willemhustinx.movietime.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Customer {

    @Id
    private long id;

    private String name;

    @ElementCollection
    @JoinTable(name = "INTERESTS", joinColumns = @JoinColumn(name = "CUSTOMER_ID"))
    @MapKeyColumn(name = "KEY")
    @Column(name = "VALUE")
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

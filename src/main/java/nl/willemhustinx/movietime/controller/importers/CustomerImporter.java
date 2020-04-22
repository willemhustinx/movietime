package nl.willemhustinx.movietime.controller.importers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nl.willemhustinx.movietime.model.Customer;
import nl.willemhustinx.movietime.repository.CustomerRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CustomerImporter {

    private static final Logger LOG
            = Logger.getLogger(String.valueOf(CustomerImporter.class));
    private final CustomerRepository repository;

    @Autowired
    public CustomerImporter(CustomerRepository customerRepository) {
        this.repository = customerRepository;
        this.init();
    }

    public void init() {
        LOG.info("Start importing customers");

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        JSONParser jsonParser = new JSONParser();
        FileReader reader;

        try {
            reader = new FileReader(new File(Objects.requireNonNull(classLoader.getResource("profiles.json")).getPath()));

            Object obj = jsonParser.parse(reader);
            JSONArray customerlist = (JSONArray) obj;

            customerlist.forEach(customer -> parseAndCreateCustomerObject((JSONObject) customer));

            LOG.info("Completed importing customers");
        } catch (FileNotFoundException e) {
            LOG.log(Level.SEVERE, "File not Found!", e);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "IO Exception!", e);
        } catch (ParseException e) {
            LOG.log(Level.SEVERE, "Parse Exception!", e);
        }

        LOG.info("End importing customers");
    }

    private void parseAndCreateCustomerObject(JSONObject customerJSON) {
        Customer customer = new Customer();
        customer.setId((Long) customerJSON.get("customer_id"));
        customer.setName((String) customerJSON.get("name"));

        JSONArray interestsList = (JSONArray) customerJSON.get("interests");

        customer.setInterests(parseInterestObject((JSONObject) interestsList.get(0)));
        repository.save(customer);
    }

    private Map<String, String> parseInterestObject(JSONObject interests) {
        Gson gson = new Gson();
        return gson.fromJson(interests.toString(), new TypeToken<Map<String, String>>() {
        }.getType());
    }
}

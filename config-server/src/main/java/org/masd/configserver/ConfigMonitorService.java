package org.masd.configserver;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigMonitorService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ConfigPropertyDto> getAllProperties() {
        // Assuming configs are stored in a collection called 'configurations'
        List<org.bson.Document> docs = mongoTemplate.find(new Query(), org.bson.Document.class, "configurations");

        return docs.stream()
                .map(doc -> new ConfigPropertyDto(
                        doc.getString("key"),
                        doc.getString("value"),
                        doc.getString("environment"),
                        doc.getString("source")
                ))
                .collect(Collectors.toList());
    }
}

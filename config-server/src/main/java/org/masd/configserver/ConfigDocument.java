package org.masd.configserver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@Document(collection = "configurations")
public class ConfigDocument {

    @Id
    private String id;
    private String application;
    private String profile;
    private String label;
    private Map<String, Object> properties;
}

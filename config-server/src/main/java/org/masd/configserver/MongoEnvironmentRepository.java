package org.masd.configserver;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MongoEnvironmentRepository implements EnvironmentRepository {

    private final ConfigRepository configRepository;

    public MongoEnvironmentRepository(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public Environment findOne(String application, String profile, String label) {
        Environment environment = new Environment(application, profile);
        configRepository.findByApplicationAndProfileAndLabel(application, profile, label)
                .ifPresent(config -> {
                    Properties props = new Properties();
                    props.putAll(config.getProperties());
                    environment.add(new PropertySource("mongo", props));
                });
        return environment;
    }
}

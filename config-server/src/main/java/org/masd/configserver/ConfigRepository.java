package org.masd.configserver;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ConfigRepository extends MongoRepository<ConfigDocument, String> {
    Optional<ConfigDocument> findByApplicationAndProfileAndLabel(String app, String profile, String label);
}

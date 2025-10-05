package org.masd.configserver;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfigPropertyDto {
    private String key;
    private String value;
    private String environment;
    private String source; // e.g., mongo or file
}

package com.slotMachine.SlotMachine.config;


import com.slotMachine.SlotMachine.models.Mission;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Getter
@Setter
@Configuration
@PropertySource("classpath:missions.json")
@ConfigurationProperties(prefix = "mission-config")
public class MissionConfig {
    private List<Mission> missions;
    private int repeatedIndex;
}

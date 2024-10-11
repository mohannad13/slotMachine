package com.slotMachine.SlotMachine.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.slotMachine.SlotMachine.models.Mission;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class MissionLoader {

    private List<Mission> missions;
    private int repeatedIndex;

    @PostConstruct
    public void loadMissions() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/missions.json");

        if (inputStream == null) {
            throw new IOException("Unable to find missions.json in resources");
        }

        MissionConfig missionConfig = objectMapper.readValue(inputStream, MissionConfig.class);
        this.missions = missionConfig.getMissions();
        this.repeatedIndex = missionConfig.getRepeatedIndex();
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public int getRepeatedIndex() {
        return repeatedIndex;
    }
}

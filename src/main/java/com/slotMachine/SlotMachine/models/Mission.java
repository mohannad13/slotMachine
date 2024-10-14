package com.slotMachine.SlotMachine.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class Mission {
    private int pointsGoal;
    private List<Reward> rewards;

    @JsonCreator
    public Mission(@JsonProperty("pointsGoal") int pointsGoal, @JsonProperty("rewards") List<Reward> rewards) {
        this.pointsGoal = pointsGoal;
        this.rewards = rewards;
    }
}

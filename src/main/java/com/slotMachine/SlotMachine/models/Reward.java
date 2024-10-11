package com.slotMachine.SlotMachine.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;


@Value
public class Reward {
    String name;
    int value;

    @JsonCreator
    public Reward(@JsonProperty("name") String name, @JsonProperty("value") int value) {
        this.name = name;
        this.value = value;
    }

}

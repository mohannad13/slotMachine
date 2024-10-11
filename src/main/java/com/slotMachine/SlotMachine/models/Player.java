package com.slotMachine.SlotMachine.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@RedisHash("Player")
public class Player {
    @Id
    private String id;
    private int spinsBalance;
    private int pointsBalance;
    private List<Mission> completedMissions;
}

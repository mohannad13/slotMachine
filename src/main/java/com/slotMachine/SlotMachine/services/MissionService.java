package com.slotMachine.SlotMachine.services;

import com.slotMachine.SlotMachine.config.MissionLoader;
import com.slotMachine.SlotMachine.models.Mission;
import com.slotMachine.SlotMachine.models.Player;
import com.slotMachine.SlotMachine.models.Reward;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    private final MissionLoader missionLoader;

    public MissionService(MissionLoader missionLoader) {
        this.missionLoader = missionLoader;
    }

    public List<Mission> getMissions() {
        return missionLoader.getMissions();
    }

    public void checkAndApplyRewards(Player player) {
        List<Mission> missions = missionLoader.getMissions();
        if (missions == null || missions.isEmpty()) {
            throw new IllegalStateException("Missions configuration is not loaded properly!");
        }

        for (Mission mission : missions) {
            if (player.getPointsBalance() >= mission.getPointsGoal()) {
                applyRewards(player, mission.getRewards());
            }
        }

        // Handle mission reset based on repeatedIndex
        if (player.getPointsBalance() >= missions.get(missions.size() - 1).getPointsGoal()) {
            resetPlayerMissions(player);
        }
    }

    private void applyRewards(Player player, List<Reward> rewards) {
        for (Reward reward : rewards) {
            if ("spins".equals(reward.getName())) {
                player.setSpinsBalance(player.getSpinsBalance() + reward.getValue());
            } else if ("coins".equals(reward.getName())) {
                player.setPointsBalance(player.getPointsBalance() + reward.getValue());
            }
        }
    }

    private void resetPlayerMissions(Player player) {
        player.setPointsBalance(missionLoader.getMissions().get(missionLoader.getRepeatedIndex() - 1).getPointsGoal());
    }
}

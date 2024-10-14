package com.slotMachine.SlotMachine.services;


import com.slotMachine.SlotMachine.models.Player;
import com.slotMachine.SlotMachine.models.SpinResult;
import org.springframework.stereotype.Service;

@Service
public class SlotMachineService {

    private final MissionService missionService;

    public SlotMachineService(MissionService missionService) {
        this.missionService = missionService;
    }

    public SpinResult spin(Player player) {
        if (player.getSpinsBalance() > 0) {
            player.setSpinsBalance(player.getSpinsBalance() - 1);
            SpinResult result = new SpinResult();

            if (result.isJackpot()) {
                int points = result.getDigits().get(0) * 3;
                player.setPointsBalance(player.getPointsBalance() + points);
            }

            missionService.checkAndApplyRewards(player);

            return result;
        } else {
            throw new IllegalArgumentException("No spins left for the player!");
        }
    }
}

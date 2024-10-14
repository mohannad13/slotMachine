package com.slotMachine.SlotMachine.controllers;

import com.slotMachine.SlotMachine.models.Mission;
import com.slotMachine.SlotMachine.models.Player;
import com.slotMachine.SlotMachine.models.SpinResult;
import com.slotMachine.SlotMachine.services.MissionService;
import com.slotMachine.SlotMachine.services.PlayerService;
import com.slotMachine.SlotMachine.services.SlotMachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/slot-machine")
public class SlotMachineController {

    private final SlotMachineService slotMachineService;
    private final PlayerService playerService;
    private final MissionService missionService;

    public SlotMachineController(SlotMachineService slotMachineService, PlayerService playerService, MissionService missionService) {
        this.slotMachineService = slotMachineService;
        this.playerService = playerService;
        this.missionService = missionService;
    }

    @PostMapping("/spin/{playerId}")
    public SpinResult spin(@PathVariable String playerId) {
        Player player = playerService.getPlayer(playerId);
        if (player == null) {
            throw new IllegalArgumentException("Player not found");
        }
        return slotMachineService.spin(player);
    }

    @PostMapping("/create-player")
    public Player createPlayer(@RequestParam String playerId, @RequestParam int spins, @RequestParam int points) {
        playerService.createPlayer(playerId, spins, points);
        return playerService.getPlayer(playerId);
    }

    @GetMapping("/missions")
    public List<Mission> getMissions() {
        return missionService.getMissions();
    }
}

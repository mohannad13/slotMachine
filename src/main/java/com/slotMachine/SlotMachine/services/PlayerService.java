package com.slotMachine.SlotMachine.services;


import com.slotMachine.SlotMachine.models.Player;
import com.slotMachine.SlotMachine.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayer(String playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public void createPlayer(String playerId, int spinsBalance, int pointsBalance) {
        Player player = Player.builder()
                .id(playerId)
                .spinsBalance(spinsBalance)
                .pointsBalance(pointsBalance)
                .build();
        savePlayer(player);
    }
}

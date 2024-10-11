package com.slotMachine.SlotMachine.repositories;


import com.slotMachine.SlotMachine.models.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, String> {
    // You can add custom queries here if needed
}


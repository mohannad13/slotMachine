package com.slotMachine.SlotMachine;

import com.slotMachine.SlotMachine.models.Mission;
import com.slotMachine.SlotMachine.models.Player;
import com.slotMachine.SlotMachine.models.Reward;
import com.slotMachine.SlotMachine.services.MissionService;
import com.slotMachine.SlotMachine.services.PlayerService;
import com.slotMachine.SlotMachine.services.SlotMachineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SlotMachineServiceTest {

    @Mock
    private MissionService missionService;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private SlotMachineService slotMachineService;

    private Player testPlayer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        testPlayer = Player.builder().id("player1").spinsBalance(5).pointsBalance(0).build();
    }

    @Test
    public void testSpinReducesSpinBalance() {
        slotMachineService.spin(testPlayer);
        assertEquals(4, testPlayer.getSpinsBalance());
    }

    @Test
    public void testSpinWithJackpotIncreasesPoints() {
        doAnswer(invocation -> {
            testPlayer.setPointsBalance(testPlayer.getPointsBalance() + 9); // assume a jackpot with three 3's
            return null;
        }).when(missionService).checkAndApplyRewards(testPlayer);

        slotMachineService.spin(testPlayer);

        assertEquals(9, testPlayer.getPointsBalance());
    }

    @Test
    public void testSpinWithoutSpinsThrowsError() {
        testPlayer.setSpinsBalance(0);
        assertThrows(IllegalArgumentException.class, () -> {
            slotMachineService.spin(testPlayer);
        });
    }


    @Test
    public void testPlayerNotFound() {
        when(playerService.getPlayer("nonExistentPlayer")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            slotMachineService.spin(Player.builder().id("nonExistentPlayer").spinsBalance(0).pointsBalance(0).build());
        });
    }

    @Test
    public void testSpinWithNoMissionRewards() {
        when(missionService.getMissions()).thenReturn(new ArrayList<>());

        slotMachineService.spin(testPlayer);

        assertEquals(4, testPlayer.getSpinsBalance());
        assertEquals(0, testPlayer.getPointsBalance());
    }

}

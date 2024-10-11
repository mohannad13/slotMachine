package com.slotMachine.SlotMachine.models;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Getter
@ToString
public class SpinResult {
    private List<Integer> digits;

    public SpinResult() {
        Random random = new Random();
        this.digits = Arrays.asList(random.nextInt(10), random.nextInt(10), random.nextInt(10));
    }

    public boolean isJackpot() {
        return digits.get(0).equals(digits.get(1)) && digits.get(1).equals(digits.get(2));
    }
}

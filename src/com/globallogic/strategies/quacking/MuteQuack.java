package com.globallogic.strategies.quacking;

import com.globallogic.behaviors.QuackBehavior;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println(" << Silence >> ");
    }
}

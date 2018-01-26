package com.globallogic.concrete;

import com.globallogic.abstractions.Duck;
import com.globallogic.strategies.flying.FlyWithWings;
import com.globallogic.strategies.quacking.Quack;

public class MallardDuck extends Duck {

    public MallardDuck(){
        quackBehavior = new Quack();
        flyBehaviour = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I am a real Mallard Duck ");
    }
}

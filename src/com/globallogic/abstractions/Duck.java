package com.globallogic.abstractions;

import com.globallogic.behaviors.FlyBehaviour;
import com.globallogic.behaviors.QuackBehavior;

public abstract class Duck {

    public QuackBehavior quackBehavior;
    public FlyBehaviour flyBehaviour;

    public Duck(){
    }

    public abstract void display();

    public void performFly(){
        flyBehaviour.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public void swim(){
        System.out.println("All ducks float, even decoys!");
    }

}

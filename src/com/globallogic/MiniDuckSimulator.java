package com.globallogic;

import com.globallogic.abstractions.Duck;
import com.globallogic.concrete.MallardDuck;

public class MiniDuckSimulator {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();
    }
}

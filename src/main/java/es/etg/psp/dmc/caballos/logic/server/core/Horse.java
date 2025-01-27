package es.etg.psp.dmc.caballos.logic.server.core;

import java.util.Random;

public class Horse {
    
    private final int MAX = 10;
    private final int MIN = 1;

    private final String name;
    private int distance;

    public Horse(String name) {
        this.name = name;
        this.distance = 0;
    }

    public synchronized int move(){
        setDistance(this.distance + getRandom());
        return getDistance();
    }

    private int getRandom(){
        Random random = new Random();
        return MIN + random.nextInt(MAX - 1);
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        if (this.distance < 100) this.distance = distance;
    }
    
}

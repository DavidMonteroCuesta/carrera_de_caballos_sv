package es.etg.psp.dmc.caballos.logic.server.util;

public class Condition {
    private static Condition instance;
    private boolean condition = true;
    private int counter = 0;

    private Condition() {}

    public static Condition getInstance() {
        if (instance == null) instance = new Condition();
        return instance;
    }

    public synchronized void count() throws Exception{
        add();
        if (getCounter() < 4) wait();
        else notifyAll();
    }
    
    public boolean isCondition() {
        return condition;
    }

    public void setCondition() {
        setCondition(false);
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public int getCounter() {
        return counter;
    }

    public void add() {
        this.counter++;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}


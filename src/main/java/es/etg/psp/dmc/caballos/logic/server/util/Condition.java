package es.etg.psp.dmc.caballos.logic.server.util;

public class Condition {
    private static Condition instance;
    private boolean condition = true;

    private Condition() {}

    public static Condition getInstance() {
        if (instance == null) instance = new Condition();
        return instance;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition() {
        this.condition = !condition;
    }
}


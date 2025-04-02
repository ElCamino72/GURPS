package org.example;

public interface StatBlock {
    String getName();

    int getStrength();

    int getDexterity();

    int getIntelligence();

    int getHealth();

    int getTotalHitPoints();

    int getCurrentHitPoints();

    void setCurrentHitPoints(int currentHitPoints);

    int getPerception();

    int getWill();

    int getTotalFatiguePoints();

    int getCurrentFatiguePoints();

    void setCurrentFatiguePoints(int currentFatiguePoints);

    double getBasicSpeed();

    int getBasicMove();

    default double getBasicLift() {
        final double basicLift = getStrength() * getStrength() / 5.0;
        return basicLift < 10 ? basicLift : Math.round(basicLift);
    }

    default int getDodge() {
        return (int) (3 + Math.floor(getBasicSpeed()));
    }
}

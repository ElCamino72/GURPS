package org.example;

import lombok.Builder;
import lombok.Getter;

public final class CharacterSheet implements StatBlock {
    @Getter
    private final String name;

    // Primary attributes
    private int strengthPoints;
    private int dexterityPoints;
    private int intelligencePoints;
    private int healthPoints;

    // Secondary characteristics
    private int hitPointsPoints;
    private int perceptionPoints;
    private int willPoints;
    private int fatiguePointsPoints;
    private int basicSpeedPoints;
    private int basicMovePoints;

    @Getter
    private int currentHitPoints;
    @Getter
    private int currentFatiguePoints;

    @Builder
    private CharacterSheet(
            final String name,
            final int strength,
            final int dexterity,
            final int intelligence,
            final int health,
            final int totalHitPoints,
            final int perception,
            final int will,
            final int totalFatiguePoints,
            final double basicSpeed,
            final int basicMove,
            final int currentHitPoints,
            final int currentFatiguePoints
    ) {
        this.name = name;
        setStrength(strength);
        setDexterity(dexterity);
        setIntelligence(intelligence);
        setHealth(health);
        setTotalHitPoints(totalHitPoints);
        setPerception(perception);
        setWill(will);
        setTotalFatiguePoints(totalFatiguePoints);
        setBasicSpeed(basicSpeed);
        setBasicMove(basicMove);
        setCurrentHitPoints(currentHitPoints);
        setCurrentFatiguePoints(currentFatiguePoints);
    }

    public static CharacterSheetBuilder builder() {
        return new CharacterSheetBuilder();
    }

    public void setStrength(final int strength) {
        this.strengthPoints = 10 * (strength - 10); // base level 10, cost per level +/- 10 points
    }

    @Override
    public int getStrength() {
        return this.strengthPoints / 10 + 10;
    }

    public void setDexterity(final int dexterity) {
        this.dexterityPoints = 20 * (dexterity - 10); // base level 10, cost per level +/- 20 points
    }

    @Override
    public int getDexterity() {
        return this.dexterityPoints / 20 + 10;
    }

    public void setIntelligence(final int intelligence) {
        this.intelligencePoints = 20 * (intelligence - 10); // base level 10, cost per level +/- 20 points
    }

    @Override
    public int getIntelligence() {
        return this.intelligencePoints / 20 + 10;
    }

    public void setHealth(final int health) {
        this.healthPoints = 10 * (health - 10); // base level 10, cost per level +/- 10 points
    }

    @Override
    public int getHealth() {
        return this.healthPoints / 10 + 10;
    }
    
    public void setTotalHitPoints(final int hitPoints) {
        this.hitPointsPoints = 2 * (hitPoints - getStrength()); // base level equals strength, cost per level +/- 2 points
    }
    
    @Override
    public int getTotalHitPoints() {
        return this.hitPointsPoints / 2 + getStrength();
    }

    @Override
    public void setCurrentHitPoints(final int currentHitPoints) {
        this.currentHitPoints = Math.min(currentHitPoints, getTotalHitPoints());
    }

    public void setPerception(final int perception) {
        this.perceptionPoints = 5 * (perception - getIntelligence()); // base level equals intelligence, cost per level +/- 5 points
    }

    @Override
    public int getPerception() {
        return this.perceptionPoints / 5 + getIntelligence();
    }

    public void setWill(final int will) {
        this.willPoints = 5 * (will - getIntelligence()); // base level equals intelligence, cost per level +/- 5 points
    }

    @Override
    public int getWill() {
        return this.willPoints / 5 + getIntelligence();
    }

    public void setTotalFatiguePoints(final int fatiguePoints) {
        this.fatiguePointsPoints = 3 * (fatiguePoints - getHealth()); // base level equals health, cost per level +/- 3 points
    }
    
    @Override
    public int getTotalFatiguePoints() {
        return this.fatiguePointsPoints / 3 + getHealth();
    }

    @Override
    public void setCurrentFatiguePoints(final int currentFatiguePoints) {
        this.currentFatiguePoints = Math.min(currentFatiguePoints, getTotalFatiguePoints());
    }

    public void setBasicSpeed(final double basicSpeed) {
        this.basicSpeedPoints = (int) (20 * (basicSpeed - (getDexterity() + getHealth()) / 4.0)); // base level equals dexterity + health / 4, cost per 0.25 +/- 5 points
    }

    @Override
    public double getBasicSpeed() {
        return this.basicSpeedPoints / 20.0 + (getDexterity() + getHealth()) / 4.0;
    }

    public void setBasicMove(final int basicMove) {
        this.basicMovePoints = (int) (5 * (basicMove - Math.floor(getBasicSpeed()))); // base level equals basic speed rounded down, cost per level +/- 5 points
    }

    @Override
    public int getBasicMove() {
        return (int) (this.basicMovePoints / 5.0 + Math.floor(getBasicSpeed()));
    }

    @Override
    public String toString() {
        return """
                Name: %s
                Strength: %d
                Dexterity: %d
                Intelligence: %d
                Health: %d
                HitPoints: %d/%d
                Perception: %d
                Will: %d
                Fatigue Points: %d/%d
                BasicSpeed: %.2f
                BasicMove: %d
                BasicLift: %.1f
                Dodge: %d
                """.formatted(
                        getName(),
                        getStrength(),
                        getDexterity(),
                        getIntelligence(),
                        getHealth(),
                        getCurrentHitPoints(), getTotalHitPoints(),
                        getPerception(),
                        getWill(),
                        getCurrentFatiguePoints(), getTotalFatiguePoints(),
                        getBasicSpeed(),
                        getBasicMove(),
                        getBasicLift(),
                        getDodge()
                );
    }

    public static class CharacterSheetBuilder {
        private String name;

        // Primary attributes
        private int strength = 10;
        private int dexterity = 10;
        private int intelligence = 10;
        private int health = 10;

        // Secondary characteristics
        private Integer totalHitPoints = null;
        private Integer perception = null;
        private Integer will = null;
        private Integer totalFatiguePoints = null;
        private Double basicSpeed = null;
        private Integer basicMove = null;
        private Integer currentHitPoints = null;
        private Integer currentFatiguePoints = null;

        public CharacterSheet build() {
            final double basicSpeed1 = basicSpeed == null ? (dexterity + health) / 4.0 : basicSpeed;
            final int totalHitPoints1 = totalHitPoints == null ? strength : totalHitPoints;
            final int totalFatiguePoints1 = totalFatiguePoints == null ? health : totalFatiguePoints;

            return new CharacterSheet(
                    name,
                    strength,
                    dexterity,
                    intelligence,
                    health,
                    totalHitPoints1,
                    perception == null ? intelligence : perception,
                    will == null ? intelligence : will,
                    totalFatiguePoints1,
                    basicSpeed1,
                    basicMove == null ? (int) Math.floor(basicSpeed1) : basicMove,
                    currentHitPoints == null ? totalHitPoints1 : currentHitPoints,
                    currentFatiguePoints == null ? totalFatiguePoints1 : currentFatiguePoints
            );
        }
    }
}

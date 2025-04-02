package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public final class CharacterSheet implements StatBlock {
    @NonNull
    private final String name;

    // Primary attributes
    private int strength;
    private int dexterity;
    private int intelligence;
    private int health;

    // Secondary characteristics
    private int hitPointsBonus;
    private int perceptionBonus;
    private int willBonus;
    private int fatiguePointsBonus;
    private double basicSpeedBonus;
    private int basicMoveBonus;

    private int currentHitPoints;
    private int currentFatiguePoints;

    @Builder
    private CharacterSheet(
            @NonNull final String name,
            final Integer strength,
            final Integer dexterity,
            final Integer intelligence,
            final Integer health,
            final Integer totalHitPoints,
            final Integer perception,
            final Integer will,
            final Integer totalFatiguePoints,
            final Double basicSpeed,
            final Integer basicMove,
            final Integer currentHitPoints,
            final Integer currentFatiguePoints
    ) {
        this.name = name;
        this.strength = strength == null ? 10 : strength;
        this.dexterity = dexterity == null ? 10 : dexterity;
        this.intelligence = intelligence == null ? 10 : intelligence;
        this.health = health == null ? 10 : health;
        this.hitPointsBonus = totalHitPoints == null ? 0 : totalHitPoints - getStrength();
        this.perceptionBonus = perception == null ? 0 : perception - getIntelligence();
        this.willBonus = will == null ? 0 : will - getIntelligence();
        this.fatiguePointsBonus = totalFatiguePoints == null ? 0 : totalFatiguePoints - getHealth();
        this.basicSpeedBonus = basicSpeed == null ? 0 : basicSpeed - ((getDexterity() + getHealth()) / 4.0);
        this.basicMoveBonus = basicMove == null ? 0 : basicMove - (int) Math.floor(getBasicSpeed());
        this.currentHitPoints = currentHitPoints == null ? getTotalHitPoints() : currentHitPoints;
        this.currentFatiguePoints = currentFatiguePoints == null ? getTotalFatiguePoints() : currentFatiguePoints;
    }

    @Override
    public int getTotalHitPoints() {
        return this.hitPointsBonus + getStrength();
    }

    @Override
    public void setCurrentHitPoints(final int currentHitPoints) {
        this.currentHitPoints = Math.min(currentHitPoints, getTotalHitPoints());
    }

    @Override
    public int getPerception() {
        return this.perceptionBonus + getIntelligence();
    }

    @Override
    public int getWill() {
        return this.willBonus + getIntelligence();
    }

    @Override
    public int getTotalFatiguePoints() {
        return this.fatiguePointsBonus + getHealth();
    }

    @Override
    public void setCurrentFatiguePoints(final int currentFatiguePoints) {
        this.currentFatiguePoints = Math.min(currentFatiguePoints, getTotalFatiguePoints());
    }

    @Override
    public double getBasicSpeed() {
        return this.basicSpeedBonus + (getDexterity() + getHealth()) / 4.0;
    }

    @Override
    public int getBasicMove() {
        return (int) (this.basicMoveBonus + Math.floor(getBasicSpeed()));
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
}

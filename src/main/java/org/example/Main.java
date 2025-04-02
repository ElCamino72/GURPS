package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        final CharacterSheet characterSheet = CharacterSheet.builder()
                .name("Bob the Mage")
                .strength(9)
                .dexterity(11)
                .intelligence(13)
                .health(11)
                .totalHitPoints(10)
                .perception(10)
                .totalFatiguePoints(13)
                .build();

        System.out.println(characterSheet);
        System.out.println("Bob the Mage levels up Strength");
        characterSheet.setStrength(10);
        System.out.println(characterSheet);
    }
}

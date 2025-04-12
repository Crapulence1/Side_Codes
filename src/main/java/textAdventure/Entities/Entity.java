package textAdventure.Entities;

import textAdventure.Affinity;
import textAdventure.Armors.Armor;
import textAdventure.Entities.Enemys.Enemy;
import textAdventure.Locations.Coordinate;
import textAdventure.Spells.Spell;
import textAdventure.StatusEffect;
import textAdventure.Weapons.Weapon;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class Entity {
    private String name;
    private int health; // current hp
    private int maxHealth; // max hp
    private int vitality; // stat scaling max hp
    private int strength; // stat scaling weapon damage
    private int dexterity; // stat scaling weapon damage
    private int defense; // stat scaling defense (incoming damage)
    private int speed; // stat scaling speed (turn order)
    private int mana; // current mana
    private int maxMana; // max mana
    private int arcana; // stat scaling spell damage
    private int knowledge; // stat scaling max mana
    private Coordinate position;
    private ArrayList<Affinity> resistances = new ArrayList<>();
    private ArrayList<Affinity> weaknesses = new ArrayList<>();
    private ArrayList<Spell> equippedSpells = new ArrayList<>();
    private boolean isBurn = false;       // Fire - Damage over time
    private boolean isParalyze = false;   // Lightning - Temporarily paralyzed
    private boolean isDrench = false;     // Water - Reduces the target's attack
    private boolean isConfuse = false;    // Air - Actions are randomized
    private boolean isTremble = false;    // Earth - Reduces defense
    private boolean isSlow = false;       // Ice - Reduces target's speed
    private boolean isGrounded = false;   // Earth status effect
    private boolean isHeated = false;     // Fire status effect
    private boolean isCoated = false;     // Water status effect
    private boolean isLifted = false;     // Air status effect
    private boolean isElectrified = false; // Lightning status effect
    private int burnDuration = 0; // Counter for burn duration

    public Entity(String name, int health, int maxHealth,  int mana, int maxMana, int vitality, int strength, int dexterity, int defense, int speed, int arcana, int knowledge, Coordinate position) {
        this.name=name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.vitality = vitality;
        this.strength = strength;
        this.dexterity = dexterity;
        this.defense = defense;
        this.speed = speed;
        this.mana = mana;
        this.maxMana = maxMana;
        this.arcana = arcana;
        this.knowledge = knowledge;
        this.position = position; // Default starting position

    }

    // Getters

    public String getName() {
        return name;
    }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getVitality() { return vitality; }
    public int getStrength() { return strength; }
    public int getDexterity() { return dexterity; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public int getMana() { return mana; }
    public int getMaxMana() { return maxMana; }
    public int getArcana() { return arcana; }
    public int getKnowledge() { return knowledge; }
    public Coordinate getPosition() { return position; }
    public ArrayList<Affinity> getResistances() {
        return resistances;
    }
    public ArrayList<Affinity> getWeaknesses() {
        return weaknesses;
    }
    public ArrayList<Spell> getEquippedSpells(){
    return equippedSpells;
    }

    // Setters
    public void setHealth(int health) { this.health = health; }
    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
    public void setVitality(int vitality) { this.vitality = vitality; }
    public void setStrength(int strength) { this.strength = strength; }
    public void setDexterity(int dexterity) { this.dexterity = dexterity; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setMana(int mana) { this.mana = mana; }
    public void setMaxMana(int maxMana) { this.maxMana = maxMana; }
    public void setArcana(int arcana) { this.arcana = arcana; }
    public void setKnowledge(int knowledge) { this.knowledge = knowledge; }
    public void setPosition(Coordinate position) { this.position = position; }


    // Increment methods
    public void increaseVitality() { this.vitality++; }
    public void increaseStrength() { this.strength++; }
    public void increaseDexterity() { this.dexterity++; }
    public void increaseDefense() { this.defense++; }
    public void increaseSpeed() { this.speed++; }
    public void increaseArcana() { this.arcana++; }
    public void increaseKnowledge() { this.knowledge++; }

        // Additional helper methods


    public void loseHealth(int num){
        health -= num;
    }

    public boolean isWeak(Affinity affinity){
        return weaknesses.contains(affinity);
    }

    public boolean isResistant(Affinity affinity){
        return resistances.contains(affinity);
    }

    public void inflictStatus(Affinity affinity) throws InterruptedException {
        String effect = affinity.getName();
        switch (affinity.getType()) {
            case fire: isBurn = true; break;
            case lightning: isParalyze = true; break;
            case water: isDrench = true; break;
            case air: isConfuse = true; break;
            case earth: isTremble = true; break;
            case ice: isSlow = true; break;
        }
        System.out.println(name + " is now affected by " + effect + "!");
        Thread.sleep(1000);
    }

    public void addResistance(Affinity resistance){
        resistances.add(resistance);
    }

    public void removeResistance(Affinity resistance){
        resistances.remove(resistance);
    }

    public void addWeakness(Affinity resistance){
        resistances.add(resistance);
    }

    public void removeWeakness(Affinity weakness){
        weaknesses.remove(weakness);
    }

    public int calculateDamageTaken(int baseDamage, int defense, Affinity affinity, Armor armor) { //scales damage down depending on defense
        double k = 60.0; // Scaling constant
        int dam = (int) (baseDamage / (1 + ((defense + armor.getDefense() / k))));
        if(isWeak(affinity)){
            dam = (int) (dam * 1.5);
        }
        if(isResistant(affinity)){
            dam = (int) (dam * .5);
        }
        return dam;
    }

    public void learnSpell(Spell spell){
        if(equippedSpells.size() < 4){
            equippedSpells.add(spell);
        }
        else {
            System.out.println("You can only have 4 spells equipped");
        }

    }

    public void removeSpell(Spell spell) throws InterruptedException {
        if(equippedSpells.contains(spell)) {
            equippedSpells.remove(spell);
        } else {
            System.out.println("That spell isn't equipped");
            Thread.sleep(1000);
        }
    }



    public int getStatScalingBonus(int stat) { //returns scaling addition
        if (stat <= 20) {
            return (int) (stat * 1.0); // Full scaling
        } else if (stat <= 50) {
            return (int) (20 + Math.log(stat - 19) * 10); // Mid scaling
        } else {
            return (int) (40 + Math.log(stat - 49) * 5); // Late scaling
        }
    }

    public abstract void takeTurn(Player player) throws InterruptedException;
    public abstract void takeTurn(ArrayList<Enemy> enemies) throws InterruptedException;

    public int growthFormula(int capacity, int stat){ //logisitc growth formula for health and mana
        double growthRate = 0.05;
        return (int) ((capacity)/(1 + Math.exp(-growthRate * stat)));
    }

    public void growHealth(){ //grows health
        int capacity = 500;
        setMaxHealth(growthFormula(capacity, getVitality()));
    }

    public void growMana(){// groows mana
        int capacity = 350;
        setMaxMana(growthFormula(capacity, getKnowledge()));
    }


    public void printStats(){
        System.out.println("Vitality: " + vitality);
        System.out.println("Strength: " + strength);
        System.out.println("Dexterity: " + dexterity);
        System.out.println("Defense: " + defense);
        System.out.println("Speed: " + speed);
        System.out.println("Arcana: " + arcana);
        System.out.println("Knowledge: " + knowledge);
    }


    public boolean isBurn() {
        return isBurn;
    }

    public boolean isParalyze() {
        return isParalyze;
    }

    public boolean isDrench() {
        return isDrench;
    }

    public boolean isConfuse() {
        return isConfuse;
    }

    public boolean isTremble() {
        return isTremble;
    }

    public boolean isSlow() {
        return isSlow;
    }

    public boolean isGrounded() {
        return isGrounded;
    }

    public boolean isHeated() {
        return isHeated;
    }

    public boolean isCoated() {
        return isCoated;
    }

    public boolean isLifted() {
        return isLifted;
    }

    public boolean isElectrified() {
        return isElectrified;
    }

    // Burn duration methods
    public void incrementBurnDuration() {
        if (isBurn) {
            burnDuration = (burnDuration + 1) % 3;
            if(burnDuration == 0){
                resetBurnStatus();
            }
        }
    }

    public int getBurnDuration() {
        return burnDuration;
    }

    private void resetBurnStatus() {
        isBurn = false;
        burnDuration = 0;
    }



//    public void printWeapon(){
//        if(equippedWeapon == null){
//            System.out.println("does not exist");
//        } else {
//            System.out.println(equippedWeapon.getName() + " " + equippedWeapon.getDamage() + " " + equippedWeapon.getAffinity());
//        }
//
//    }

    //Scaling methods




}

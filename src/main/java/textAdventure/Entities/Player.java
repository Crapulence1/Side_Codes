package textAdventure.Entities;

import textAdventure.Armors.Armor;
import textAdventure.Entities.Enemys.Enemy;
import textAdventure.Locations.*;
import textAdventure.Spells.Spell;
import textAdventure.StatusEffect;
import textAdventure.Weapons.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Entity{
    private int xp;
    private int thresholdXp;
    private int level;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    double baseGrowth = 1.1;

    public Player(){
        super("Player", 50, 50, 40, 40, 5, 5, 5, 5, 5, 5, new Coordinate(0, 0));
        level = 1;
        xp = 0;
        thresholdXp = growthXp(level);
    }



    public Weapon getEquippedWeapon() { return equippedWeapon; }

    public Armor getEquippedArmor() { return equippedArmor; }

    public void setEquippedWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public void setEquippedArmor(Armor armor) {
        this.equippedArmor = armor;
    }

    public void equipSpell(Spell spell) throws InterruptedException {
        if(getEquippedSpells().size() > 4){
            System.out.println("You can only have 4 spells equipped");
            Thread.sleep(1000);
        } else {
            getEquippedSpells().add(spell);
        }
    }



    public void damage(Weapon weapon, int strength, String name) throws InterruptedException { //damages player based off weapon and knowledge scaling
        int strengthDmg = getStatScalingBonus(strength);
        int initDam = (int) ((0.90 + Math.random() * 0.20) * weapon.getDamage()) + strengthDmg; //allows for a 10% variance in damage
        int damage = calculateDamageTaken(initDam, getDefense(), weapon.getAffinity(), getEquippedArmor());
        System.out.println(name + " swings at you!");
        Thread.sleep(1000);
        System.out.println("You took " + damage + " damage!");
        Thread.sleep(1000);
        setHealth(getHealth() - damage);
        if(getHealth() <= 0){
            gameover();
        }
    }

    public void damage(Spell spell, int knowledge) throws InterruptedException { //damages player based on spell and knowledge scaling
        int knowDmg = getStatScalingBonus(knowledge);
        int initDam = (int) (spell.getDamage() * (0.90 + Math.random() * 0.20)) + knowDmg;
        int damage = calculateDamageTaken(initDam, getDefense(), spell.getAffinity(), getEquippedArmor());
        Thread.sleep(1000);
        System.out.println("You took " + damage + " damage!");
        Thread.sleep(1000);
        setHealth(getHealth() - damage);
        if((int) (Math.random() * 100) + 1 <= spell.getChance()){
            inflictStatus(spell.getAffinity());
        }
        if(getHealth() <= 0){
            gameover();
        }
    }

    public void addXp(int num){ //adds xp to player
        xp += (int) (num * (0.95 + Math.random() * 0.10));
        if(xp >= thresholdXp){
            levelUp();
        }
    }

    public int growthXp(int level){ //raises the max xp requirement
        int base = 12;
        return (int) (base * (level + Math.pow(level, 2)) * baseGrowth);
    }

    public void levelUp(){ //levels up player and adjusts every stat
        int x = 5;
        level++;
        Scanner in = new Scanner(System.in);
        System.out.println("You leveled up!");
        while(x > 0) {
            printHealth();
            printMana();
            System.out.println("You have " + x + " stat points to assign");
            printStats();
            System.out.println("Which stat do you want to increase?");
            String input = in.nextLine().toLowerCase();
            switch(input){
                case "1", "vitality":
                    increaseVitality();
                    growHealth();
                case "2", "strength":
                    increaseStrength();
                case "3", "defense":
                    increaseDefense();
                case "4", "speed":
                    increaseSpeed();
                case "5", "arcana":
                    increaseArcana();
                case "6", "knowledge":
                    increaseKnowledge();
                    growMana();
            }
            x--;
        }
        xp = 0;
        level++;
        thresholdXp = growthXp(level);

    }



    public boolean move(Direction direction, Location location) { //moves to next room
        Coordinate newPosition = getPosition().move(direction);
        if (location.isRoomAvailable(newPosition)) {
            setPosition(newPosition);
            System.out.println("Moved " + direction + " to " + getPosition());
            return true;
        } else {
            System.out.println("Can't move " + direction + "! No room there.");
            return false;
        }
    }


    public Spell findSpellByName(String name){
        for(Spell spell : getEquippedSpells()){
            if(spell.getName().toLowerCase().equals(name)){
                return spell;
            }
        }
        System.out.println("Please choose a valid spell");
        return null;
    }

    public int getXp() {
        return xp;
    }

    public int getThresholdXp() {
        return thresholdXp;
    }

    public int getLevel() {
        return level;
    }


    public void gameover(){ //game over logic
        System.out.println("You died! Get fucking better at the game! Loser.");
        System.exit(0);
    }

    @Override
    public void takeTurn(Player player) {
    }

    public void printEquippedSpells(){ //prints each spell with its cost in mana
        for(Spell spell: getEquippedSpells()){
            System.out.println(spell.getName() + " " + spell.getDamage() + " Mp");
        }
    }

    @Override
    public void takeTurn(ArrayList<Enemy> enemies) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        int target = 0;
        System.out.println("It's your turn!");
        System.out.println("What would you like to do? \n");
        System.out.println("1. Swing");
        System.out.println("2. Cast spell");
        System.out.println("3. Nothing");
        String input = in.nextLine().toLowerCase().trim();
        switch(input){
            case "1", "swing":
                System.out.println("\nAt who?");
                printOptions(enemies);
                target = in.nextInt();
                while (target <= 0 || target > enemies.size()) {
                    System.out.println("Invalid choice!");
                    target = in.nextInt();
                }
                enemies.get(target - 1).damage(getEquippedWeapon(), getStrength());
                break;
            case "2", "cast", "cast spell", "spell":
                System.out.println("Which spell?");
                printEquippedSpells();
                int choice = in.nextInt();
                while(choice <= 0 || choice > getEquippedSpells().size()){
                    System.out.println("Invalid choice!");
                    choice = in.nextInt();
                }
                Spell casting = getEquippedSpells().get(choice - 1);
                System.out.println("\nAt who?");
                printOptions(enemies);
                target = in.nextInt();
                while (target <= 0 || target > enemies.size()) {
                    System.out.println("Invalid choice!");
                    target = in.nextInt();
                }
                enemies.get(target - 1).damage(casting, getKnowledge());
                setMana(getMana() - casting.getCost());
                break;

            case "3", "nothing":
                System.out.println("You've skipped your turn");
                break;
        }
    }

    public void printOptions(ArrayList<Enemy> enemies){
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println(i + 1 + ": " + enemies.get(i).getName());
        }
    }

    public void printHealth(){
        System.out.print("Hp: " + getHealth() + "/" + getMaxHealth());
    }

    public void printWeapon(){
        System.out.println(getEquippedWeapon());

    }

    public void printMana(){
        System.out.println("Mana: " + getMana() + "/" + getMaxMana());
    }
}

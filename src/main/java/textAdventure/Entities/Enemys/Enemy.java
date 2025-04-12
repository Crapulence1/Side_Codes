package textAdventure.Entities.Enemys;

import textAdventure.Affinity;
import textAdventure.Armors.Armor;
import textAdventure.EffectType;
import textAdventure.Entities.Entity;
import textAdventure.Entities.Player;
import textAdventure.Locations.Coordinate;
import textAdventure.Loot;
import textAdventure.Spells.Spell;
import textAdventure.StatusEffect;
import textAdventure.Weapons.Weapon;

import java.util.ArrayList;

public class Enemy extends Entity {
    private Loot loot;
    private Weapon weapon;
    private Armor armor;
    private int xp;
    private ArrayList<Spell> equippedSpells = new ArrayList<>();

    public Enemy(String name, int health, int maxHealth,  int mana, int maxMana, int vitality, int strength, int dexterity, int defense, int speed, int arcana, int knowledge, Coordinate position, Loot loot, Weapon weapon, Armor armor, int xp){
        super(name, health, maxHealth, mana, maxMana, vitality, strength, dexterity, defense, speed, arcana, knowledge, position);
        this.loot=loot;
        this.weapon=weapon;
        this.armor=armor;
        this.xp=xp;
    }




    public void damage(int num){
        int dam = defenseCalc(num);
        loseHealth(dam);
        System.out.println("You've attacked " + getName() + " for " + dam + " damage!");

    }

    @Override
    public void takeTurn(Player player) throws InterruptedException {
        if(isBurn()){
            int dam = (int) (getMaxHealth() * .05) + 1;
            System.out.println(getName() + " took " + dam + " damage from Burn!");
            setHealth(getHealth() - dam);
            Thread.sleep(1000);
        }
        if(!getEquippedSpells().isEmpty()) { //checks if spell list is empty
            int rand = (int) (Math.random() * 2) + 1;
            switch (rand) {
                case 1: //swing with weapon
                    attack(player);
                    break;
                case 2: //wants to cast spell
                    castSpell(player);
                    break;
            }
        } else {
            attack(player); //attacks if spell list is empty
        }
        incrementBurnDuration();
    }

    @Override
    public void takeTurn(ArrayList<Enemy> enemies) {
    }

    public void castSpell(Player player) throws InterruptedException { //randomly chooses spell
        int size = getEquippedSpells().size();
        int rand = (int) (Math.random() * size);
        player.damage(getEquippedSpells().get(rand), getKnowledge());
    }

    public void attack(Player player) throws InterruptedException {
        player.damage(getWeapon(), getStrength(), getName());
    }

    public void randomAction(){
        int rand1 = (int) (Math.random() * 4) + 1;
        switch (rand1){
            case 1:
                break; //does nothing for turn
            case 2:

            case 3:
            case 4:
        }
    }

    public void damage(Spell spell, int knowledge) throws InterruptedException { //damages player based off weapon and knowledge scaling
        int strengthDmg = getStatScalingBonus(knowledge);
        int initDam = (int) ((0.90 + Math.random() * 0.20) * spell.getDamage()) + strengthDmg; //allows for a 10% variance in damage
        int damage = calculateDamageTaken(initDam, getDefense(), spell.getAffinity(), getArmor());
        System.out.println("You did " + damage + " damage to " + getName() + "!");
        Thread.sleep(1000);
        int rand = (int) ((Math.random() * 100) + 1);
        if(rand <= spell.getChance()){
            inflictStatus(spell.getAffinity());
        }
        setHealth(getHealth() - damage);
    }

    public void damage(Weapon weapon, int strength) throws InterruptedException { //damages player based off weapon and knowledge scaling
        int strengthDmg = getStatScalingBonus(strength);
        int initDam = (int) ((0.90 + Math.random() * 0.20) * weapon.getDamage()) + strengthDmg; //allows for a 10% variance in damage
        int damage = calculateDamageTaken(initDam, getDefense(), weapon.getAffinity(), getArmor());
        System.out.println("You did " + damage + " damage to " + getName() + "!");
        Thread.sleep(1000);
        setHealth(getHealth() - damage);
    }

    public int defenseCalc(double num){
        int totalDef;
        int def = getDefense();
        if(getArmor() != null){
            totalDef = def + getArmor().getDefense();
        } else {
            totalDef = def;
        }
        int s = 50;
        return (int) ((num)/(1+((double) totalDef /s)));
    }


    public int getXp(){
        return this.xp;
    }
    public Weapon getWeapon(){
        return this.weapon;
    }
    public Armor getArmor(){
        return this.armor;
    }
    public void setWeapon(Weapon qubert){
        this.weapon = qubert;
    }
    public void setArmor(Armor Alan){
        this.armor = Alan;
    }

    public ArrayList<Spell> getEquippedSpells() {
        return equippedSpells;
    }

    public void setEquippedSpells(ArrayList<Spell> equippedSpells) {
        this.equippedSpells = equippedSpells;
    }

    public Loot getLoot() {
        return loot;
    }

    public String printHealth(){
        return "Hp: " + getHealth() + "/" + getMaxHealth();
    }

    public void printMana(){
        System.out.println("Mana: " + getMana() + "/" + getMaxMana());
    }
}

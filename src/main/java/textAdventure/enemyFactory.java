package textAdventure;

import textAdventure.Armors.Armor;
import textAdventure.Entities.Enemys.Enemy;
import textAdventure.Entities.Player;
import textAdventure.Locations.Coordinate;
import textAdventure.Weapons.Weapon;


import java.util.ArrayList;
import java.util.Random;

public class enemyFactory {
    private int lvl;
    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private static final Random rand = new Random();
    //change later for species of enemy and class (mage, tank, berserk, blah blah radaradarada)
    public enemyFactory(int lvl){
        this.lvl=lvl;
    }
    public ArrayList<Enemy> getEnemies(int size, Coordinate coordinate) throws Exception {
        ArrayList<Enemy> enemies = new ArrayList<>();
        int count = 1;
        while(count < size + 1){
            int[] statBlock = randomStats();
            makeHealth(statBlock[0]); //creates max health and health based off vitality
            makeMana(statBlock[5]); //creates max mana and mana based off knowledge
            Enemy enemy = new Enemy("Goblin " + count, health, maxHealth, mana, maxMana, statBlock[0], statBlock[1], statBlock[2], statBlock[3], statBlock[4], statBlock[5], coordinate, null, null, randomArmor(), randomXp());
            enemy.setWeapon(randomWeapon());
            enemies.add(enemy);
            count++; //change loot to something proper later
        }
        return enemies;
    }

    public int growthFormula(int capacity, int stat){ //logistic growth formula for health and mana
        double growthRate = 0.05;
        return (int) ((capacity)/(1 + Math.exp(-growthRate * stat))) - 250; // -250 to translate down
    }

    public void makeHealth(int vital){ //grows health
        int capacity = 500;
        maxHealth = growthFormula(capacity, vital);
        health = maxHealth;
    }

    public void makeMana(int know){// grows mana
        int capacity = 350;
        maxMana = (growthFormula(capacity, know));
        mana = maxMana;
    }

    public int randomXp(){
        int baseXp = lvl * 30; //30 xp at lvl 1

        // Apply ±10% variance
        double variance = 0.2; // 20%
        int minXp = (int) Math.floor(baseXp * (1 - variance));
        int maxXp = (int) Math.ceil(baseXp * (1 + variance));
        return rand.nextInt(maxXp - minXp + 1) + minXp;
    }

    public int[] randomStats(){
        int[] stats = new int[6];
        // {Vitality, Strength, Defense, Speed, Arcana, Knowledge}
        int points = 30 + (5 * lvl);
        int maxPoints = points;
        while(points > 0) {
            int rand = (int) (Math.random() * 6); // roll new random each time
            while(stats[rand] > maxPoints/5){ //rerolls the stat if it's greater than a certain amount
                rand = (int) (Math.random() * 6);
            }
            stats[rand]++;
            points--;

        }
        points--;
        return stats;
    }

    public Armor randomArmor(){
        String name = "";
        String type = "";
        int weaponRand = (int) (Math.random() * 4 ) + 1;
        switch(weaponRand){
            case 1:
                name = "Chainmail";
                type = "Slash";
                break;
            case 2:
                name = "Plate";
                type = "Pierce";
                break;
            case 3:
                name = "Padded";
                type = "Bludgeon";
                break;
            case 4:
                name = "Scale";
                type = "Slash";
                break;
        }
        int baseDef = lvl * 5;

        // Apply ±10% variance
        double variance = 0.1; // 10%
        int minDamage = (int) Math.floor(baseDef * (1 - variance));
        int maxDamage = (int) Math.ceil(baseDef * (1 + variance));
        int def = rand.nextInt(maxDamage - minDamage + 1) + minDamage;
        return new Armor(name, def, type);
    }

    public Weapon randomWeapon() throws Exception { //creates a random weapon
        String name = "";
        int weaponRand = (int) (Math.random() * 4 ) + 1;
        Affinity type = null;
        switch (weaponRand) {
            case 1:
                name = "Sword";
                type = new Affinity("Slash", Type.slash);
                break;
            case 2:
                name = "Dagger";
                type = new Affinity("Pierce", Type.pierce);
                break;
            case 3:
                name = "Axe";
                type = new Affinity("Slash", Type.slash);
                break;
            case 4:
                name = "Hammer";
                type = new Affinity("Bludgeon", Type.bludgeon);
                break;
            default:
                throw new Exception("Somethings wrong");
        }
        int baseDamage = lvl * 5;

        // Apply ±10% variance
        double variance = 0.1; // 10%
        int minDamage = (int) Math.floor(baseDamage * (1 - variance));
        int maxDamage = (int) Math.ceil(baseDamage * (1 + variance));
        int damage = rand.nextInt(maxDamage - minDamage + 1) + minDamage;
        return new Weapon(name, damage, type);
    }


}

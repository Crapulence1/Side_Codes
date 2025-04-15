package textAdventure;

import textAdventure.Entities.Enemys.Enemy;
import textAdventure.Entities.Entity;
import textAdventure.Entities.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static textAdventure.MergeSort.Sort;
import static textAdventure.MergeSort.print;

public class Battle {
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Entity> turnOrder = new ArrayList<>();
    private ArrayList<Loot> loot = new ArrayList<>();
    private ArrayList<String> enemyHealth = new ArrayList<>();
    private ArrayList<String> enemyNames = new ArrayList<>();

    public Battle(Player player, ArrayList<Enemy> enemies){
        this.player=player;
        this.enemies=enemies;
        makeTurnOrder();
    }





    public void makeTurnOrder(){
        turnOrder.clear();
        turnOrder.addAll(enemies);
        turnOrder.add(player);
        turnOrder.sort(Comparator.comparingInt(Entity::getSpeed).reversed()); // Sorts by speed descending
    }

    public void printEnemyInfo(){
        for(Enemy enemy: enemies){
            System.out.print(enemy.getName() + " | "); //prints all names
        }
        System.out.println(); //new line
        for(Enemy enemy: enemies){ //prints all health
            System.out.print(enemy.printHealth() + " | ");
        }
        System.out.println();

    }



    public void printPlayerInfo(){
        System.out.print("You: ");
        player.printHealth();
        System.out.print(" | ");
        player.printMana();

    }

    public void startBattle() throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("A battle has started!");
        int turn = 0;
        while(player.getHealth() > 0 && !enemies.isEmpty()){
            System.out.println(); // new line
            Entity entity = turnOrder.get(turn);
            if(entity instanceof Enemy && entity.getHealth() <= 0) { //if the entity is an enemy and has health less than equal to 0
                System.out.println(entity.getName() + " has been defeated!");
                turnOrder.remove(entity); //removes from turn order
                enemies.remove(entity); //removes from enemy list
                lootEnemy((Enemy) entity);
                turn--;
                Thread.sleep(1000);
            }
            printEnemyInfo();
            printPlayerInfo();
            Thread.sleep(1000);
            if(entity instanceof Enemy && entity.getHealth() > 0) {
                System.out.println("It's " + entity.getName() + "'s turn!");
                Thread.sleep(1000);
                entity.takeTurn(player); //entity's turn
            }
            if(entity instanceof Player){
                player.takeTurn(enemies);
            }
            if(enemies.isEmpty()){
                System.out.println("You have won!");
            }
            turn = (turn+1) % turnOrder.size();
        }
    }

    public void lootEnemy(Enemy enemy){
        if(enemy.getLoot() != null){
            loot.add(enemy.getLoot());
        }
    }

    public void printLoot(){

    }
}

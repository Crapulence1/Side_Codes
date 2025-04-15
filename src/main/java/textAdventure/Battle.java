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
    int turn = 0;
    public void startBattle() throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("A battle has started!");

        while(player.getHealth() > 0 && !enemies.isEmpty()){
            System.out.println(); // new line
            Entity entity = turnOrder.get(turn);
            cleanDeadEnemies();
            printEnemyInfo();
            printPlayerInfo();
            Thread.sleep(1000);
            if(entity instanceof Enemy && entity.getHealth() > 0) {
                System.out.println("It's " + entity.getName() + "'s turn!");
                Thread.sleep(1000);
                entity.takeTurn(player); //entity's turn
                cleanDeadEnemies();
            }
            if(entity instanceof Player){
                player.takeTurn(enemies);
                cleanDeadEnemies();
            }
            if(enemies.isEmpty()){
                System.out.println("You have won!");
            }
            turn = (turn+1) % turnOrder.size();
        }
    }

    public void cleanDeadEnemies() throws InterruptedException {
        for (Entity entity : turnOrder) {
            if (entity instanceof Enemy && entity.getHealth() <= 0) {
                System.out.println(entity.getName() + " has been defeated!");
                turnOrder.remove(entity);
                enemies.remove(entity);
                lootEnemy((Enemy) entity); // handle loot
                turn--;
                Thread.sleep(1000);
            }
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

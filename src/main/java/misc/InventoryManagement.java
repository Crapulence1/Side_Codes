package misc;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManagement {
    public static void main(String[] args){
        ArrayList<Item> Inventory = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Please choose a command \n 1. New Item \n 2. Change Cost \n 3. Change Stock \n 4. View Items \n If you would like to quit, enter q at any point");
            String command = in.nextLine();
            if(command.equalsIgnoreCase("q")){
                System.out.println("Goodbye!");
                System.exit(0);
            }
            switch (command.toLowerCase()){
                //adding a new item
                case "new item":
                case "1":
                    while(true) {
                        try {
                            System.out.println("Please input the name, cost, and amount of stock of the item (separate each with a comma and space)");
                            String input = in.nextLine();
                            if(input.equalsIgnoreCase("q")){
                                System.out.println("Goodbye!");
                                System.exit(0);
                            }
                            String[] newItem = input.split(", ", 3);
                            double cost = Double.parseDouble(newItem[1]);
                            int stock = Integer.parseInt(newItem[2]);
                            Inventory.add(new Item(newItem[0], cost, stock));
                            System.out.println(newItem[0] + " has been added");
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please remember to separate each part with a comma and space");
                        }
                    }
                    break;
                    //changing cost
                case "change cost":
                case "2":
                    if(Inventory.isEmpty()){
                        System.out.println("There are no items currently!");
                        break;
                    }
                    while(true) {
                        printCost(Inventory);
                        System.out.println("Which item?");
                        String costName = in.nextLine();
                        if (costName.equalsIgnoreCase("q")) {
                            System.out.println("Goodbye!");
                            System.exit(0);
                        }
                        //come back to check if the name is valid                                                       <--- URGENT
                        if (findItemByName(Inventory, costName) == null) {
                            System.out.println("There is no item by that name! Please choose an existing item.");
                            continue;
                        }
                        else {
                            System.out.println(findItemByName(Inventory, costName).getName() + " $" + findItemByName(Inventory, costName).getCost());
                            System.out.println("What is the new price?");
                            double newPrice = in.nextDouble();
                            in.nextLine();
                            findItemByName(Inventory, costName).changeCost(newPrice);
                            System.out.println("The new price of " + findItemByName(Inventory, costName).getName() + " is $" + newPrice);
                            break;
                        }
                    }
                    break;
                case "change stock":
                case "3":
                    while(true) {
                        if (Inventory.isEmpty()) {
                            System.out.println("There are no items currently!");
                            break;
                        }
                        printStock(Inventory);
                        System.out.println("Which item?");
                        String stockName = in.nextLine();
                        if (stockName.equalsIgnoreCase("q")) {
                            System.out.println("Goodbye!");
                            System.exit(0);
                        }
                        //come back to check if the name is valid                                                       <--- URGENT
                        if (findItemByName(Inventory, stockName) == null) {
                            System.out.println("There is no item by that name! Please choose an existing item.");
                            continue;
                        }
                        if (stockName.equalsIgnoreCase("q")) {
                            System.out.println("Goodbye!");
                            System.exit(0);
                        }
                        //come back to check if the name is valid                                                       <--- URGENT
                        System.out.println(findItemByName(Inventory, stockName).getName() + " " + findItemByName(Inventory, stockName).getStock());
                        System.out.println("What is the new stock?");
                        String newStock = in.nextLine();
                        if (newStock.equalsIgnoreCase("q")) {
                            System.out.println("Goodbye!");
                            System.exit(0);
                        }
                        int newStockNum = Integer.parseInt(newStock);
                        findItemByName(Inventory, stockName).changeStock(newStockNum);
                        System.out.println("The new stock of " + findItemByName(Inventory, stockName).getName() + " is " + newStock);
                        break;
                    }
                    break;
                case "view items":
                case "4":
                    if(Inventory.isEmpty()){
                        System.out.println("There are no items currently!");
                        break;
                    }
                    printAll(Inventory);
                    break;
            }
        }
    }
    public static Item findItemByName(ArrayList<Item> Inventory, String name){
        for(Item item : Inventory){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }
        return null;
    }
    public static void printCost(ArrayList<Item> Inventory){
        for(Item item : Inventory){
            System.out.println(item.getName() + " $" + item.getCost());
        }
    }
    public static void printStock(ArrayList<Item> Inventory){
        for(Item item : Inventory){
            System.out.println(item.getName() + " $" + item.getStock());
        }
    }
    public static void printAll(ArrayList<Item> Inventory){
        for(Item item : Inventory){
            System.out.println(item.getName() + " $" + item.getCost() + " " + item.getStock());
        }
    }
}
class Item{
    private String name;
    private double cost;
    private int stock;
    public Item(String name, double cost, int stock){
        this.name = name;
        this.cost = cost;
        this.stock = stock;
    }
    public String getName(){
        return this.name;
    }
    public double getCost(){
        return this.cost;
    }
    public int getStock(){
        return this.stock;
    }
    public void changeCost(double cost){
        this.cost=cost;
    }
    public void changeStock(int stock){
        this.stock=stock;
    }

}

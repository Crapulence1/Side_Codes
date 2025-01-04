package misc;

import java.util.*;
import java.util.ArrayList;
public class BankProj {
    public static void main(String[] args){
        ArrayList <Person> accounts = new ArrayList<Person>();
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String[] parts = in.nextLine().split("\\s+", 3); // Split into at most 2 parts
            if (parts.length == 2) {
                String command = parts[0];
                String action = parts[1];
                switch (command) {
                    case "new":
                        accounts.add(new Person (action, 0));
                        System.out.println("Account " + action + " has been created");
                        break;
                    case "show":
                        if (action.equals("accounts")){
                            printAccounts(accounts);
                        } else {
                            System.out.println(findPersonByName(accounts, action).getAmount());
                        }
                }
            }
            if(parts.length==3){
                String command = parts[0];
                String action = parts[1];
                double amount = Double.parseDouble(parts[2]);
                switch (command){
                    case "deposit":
                        findPersonByName(accounts, action).Deposit(amount);
                        System.out.println(amount + " has been deposited into " + action + "'s account");
                        break;
                    case "withdraw":
                        System.out.println(amount + " has been withdrawn from " + action + "'s account");
                        findPersonByName(accounts, action).Withdraw(amount);
                        break;
                }
            }
        }
    }
    public static Person findPersonByName(ArrayList<Person> accounts, String name) {
        for (Person person : accounts) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person; // Found the person
            }
        }
        return null; // Person not found
    }
    public static void printAccounts(ArrayList<Person> accounts){
        for (Person person : accounts){
            System.out.println(person.getName() + " " +  person.getAmount());
        }
    }
}



class Person{
    private String name;
    private double amount;
    public Person(String name, double amount){
        this.name = name;
        this.amount = amount;
    }
    public double getAmount(){
        return this.amount;
    }
    public String getName(){
        return this.name;
    }
    public void Deposit(double s){
        this.amount+=s;
    }
    public void Withdraw(double s) { this.amount-=s; }

}

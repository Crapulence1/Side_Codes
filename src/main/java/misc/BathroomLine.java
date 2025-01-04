package misc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BathroomLine {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Queue<String> queue = new LinkedList<>();
        while(true){
            System.out.println("Please choose a command: \n 1. Add person to to queue \n 2. Remove person at the end of queue \n 3. Show queue \n 4. Clear queue");
            String command = in.nextLine();
            switch(command.toLowerCase()){
                case "add person to queue":
                case "1":
                    System.out.println("Please input the name of the person");
                    String name = in.nextLine();
                    queue.add(name);
                    System.out.println(name + " has been added to queue");
                    break;
                case "remove person at the end of queue":
                case "2":
                    System.out.println(queue.remove() + " has entered the bathroom");
                    break;
                case "show queue":
                case "3":
                    System.out.println(queue);
                    break;
                case "clear queue":
                case "4":
                    queue.clear();
                    System.out.println("Everyone has been kicked from the queue");
                    break;
            }
        }
    }
}

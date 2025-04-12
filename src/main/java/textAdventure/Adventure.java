//package textAdventure;
//
//import textAdventure.Entities.Enemys.Enemy;
//import textAdventure.Entities.Enemys.Goblin;
//import textAdventure.Entities.Player;
//import textAdventure.Locations.*;
//import textAdventure.Weapons.*;
//
//import java.util.Scanner;
//
//public class Adventure {
//   static Player player = new Player();
//   static Scanner in = new Scanner(System.in);
//   public static void main(String[] args){
//      Location forest = createForest();
//      player.setEquippedWeapon(new Sword("Shortsword", 2, null));
//      while (true){
//
//         try {
//            if(forest.getRoom(player.getPosition()).containsEnemy()){
//
//               startBattle(player, forest.getRoom(player.getPosition()).getEnemy());
//            }
//            System.out.println("Move: North, South, East, West");
//            String input = in.nextLine().toLowerCase().trim();
//
//            switch (input){
//               case "north", "south", "east", "west":
//                  Direction direction = Direction.valueOf(input);
//                  player.move(direction, forest);
//            }
//         } catch (IllegalArgumentException e) {
//            System.out.println("Invalid! Please input a proper command");
//         }
//      }
//   }
//
//
//   public static Location createForest(){
//      Location forest = new Location("Forest");
//      forest.addRoom(new Room("FUCK", new Coordinate(0, 0)));
//      forest.addRoom(new Room("TROLOLOLOLOLOLOL", new Coordinate(0, 1), new Goblin(player.getLevel(), new Sword("Adamantine Sword", 3), null)));
//      return forest;
//   }
//
//   public static void startBattle(Player player, Enemy enemy){
//      while (player.getHealth() > 0 || enemy.getHealth() > 0){
//         System.out.println("Player HP: " + player.getHealth());
//         System.out.println("Enemy HP: " + enemy.getHealth());
//         System.out.println("Swing or Spell or Nothing?");
//         String input = in.nextLine().toLowerCase();
//         switch(input){
//            case "swing":
//               enemy.damage(player.getEquippedWeapon());
//               break;
//            case "spell":
//               System.out.println("Which spell?");
//               player.printEquippedSpells();
//               String spellInput = in.nextLine().trim();
//               while(player.findSpellByName(spellInput) == null){
//                  spellInput = in.nextLine();
//               }
//               enemy.damage(player.findSpellByName(spellInput));
//            case "nothing":
//               break;
//            default:
//               System.out.println("Please type a valid command");
//               break;
//         }
//         player.damage(enemy.getWeapon());
//
//      }
//      if (enemy.getHealth() <= 0){
//         player.addXp(enemy.getXp());
//      }
//
//   }
//}
//

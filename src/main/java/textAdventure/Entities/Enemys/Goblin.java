//package textAdventure.Entities.Enemys;
//
//import textAdventure.Armors.Armor;
//import textAdventure.Weapons.*;
//
//import java.util.ArrayList;
//
//public class Goblin extends Enemy{
//    int playerLevel;
//    public Goblin(int playerLevel, Weapon weapon, Armor armor){
//        super(5 + playerLevel * 3, 5 + playerLevel * 2, weapon, armor, 2 + playerLevel * 4, null, null);
//        setWeakness(weaknesses());
//        setResistance(resistances());
//        this.playerLevel = playerLevel;
//    }
//
//    public ArrayList<String> weaknesses(){
//        ArrayList<String> weaknesses = new ArrayList<>();
//        weaknesses.add("Fire");
//        weaknesses.add("Bludgeoning");
//        return weaknesses;
//    }
//
//    public ArrayList<String> resistances(){
//        ArrayList<String> resistances = new ArrayList<>();
//        resistances.add("Slash");
//        return resistances;
//    }
//
//
////    public Weapon addWeapon(){
////        int x = (int) (Math.random() * 5) + 1;
////        switch(x){
////            case 1:
////            case 2:
////            case 3:
////            case 4:
////            case 5:
////        }
////    }
//}

package textAdventure.Spells;

import textAdventure.Affinity;
import textAdventure.EffectType;

public class Spell {
    private String name;
    private String description;
    private int damage;
    private Affinity affinity;
    private int chance;
    private int cost;
    public Spell(String name, String description, int damage, Affinity affinity, int chance, int cost){
        this.name=name;
        this.description=description;
        this.damage=damage;
        this.affinity=affinity;
        this.chance=chance;
        this.cost=cost;
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDamage(){
        return damage;
    }

    public Affinity getAffinity(){
        return affinity;
    }

    public int getChance(){
        return chance;
    }

    public int getCost(){
        return cost;
    }

//    public EffectType getEffect(){
//        return switch (getAffinity().getName().toLowerCase()) {
//            case "fire" -> EffectType.Burn;
//            case "ice" -> EffectType.Slow;
//            case "lightning" -> EffectType.Paralyze;
//            case "air" -> EffectType.Confuse;
//            case "water" -> EffectType.Drench;
//            default -> null;
//        };
//    }

    public String getEffectName(){
        return switch (getAffinity().getName().toLowerCase()) {
            case "fire" -> "Burn";
            case "ice" -> "Slow";
            case "lightning" -> "Paralyze";
            case "air" -> "Confuse";
            case "water" -> "Drench";
            default -> null;
        };
    }



}

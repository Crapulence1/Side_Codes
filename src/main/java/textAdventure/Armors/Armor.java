package textAdventure.Armors;

import textAdventure.Loot;

public class Armor extends Loot {
    private int Defense;
    private String Type;

    public Armor(String Name, int Defense, String Type){ //defends against this type
        super(Name);
        this.Defense=Defense;
        this.Type=Type;
    }

    public int getDefense(){
        return this.Defense;
    }
    public String getType(){
        return  this.Type;
    }
}

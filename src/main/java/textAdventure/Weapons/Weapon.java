package textAdventure.Weapons;

import textAdventure.Affinity;
import textAdventure.Loot;

public class Weapon extends Loot {
    private double Damage;
    private Affinity Type;
    public Weapon(String Name, int Damage, Affinity Type){
        super(Name);
        this.Damage = Damage;
        this.Type = Type;
    }

    public double getDamage(){
        return this.Damage;
    }
    public Affinity getAffinity(){
        return  this.Type;
    }
    public void setDamage(double damage){
        this.Damage = damage;
    }
    public void setType(Affinity type){
        this.Type = type;
    }

    public String toString(){
        return getName() + " " + getDamage() + " " + getAffinity();
    }
}

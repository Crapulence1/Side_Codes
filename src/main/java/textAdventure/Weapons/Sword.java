package textAdventure.Weapons;

import textAdventure.Affinity;
import textAdventure.Type;

public class Sword extends Weapon{
    private int Damage;
    private  String Name;
    private String Type;
    private String Element;
    public Sword(String Name, int Damage){
        super(Name, Damage, new Affinity("Slash", textAdventure.Type.slash));
    }



}

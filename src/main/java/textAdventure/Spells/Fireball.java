package textAdventure.Spells;

import textAdventure.Affinity;
import textAdventure.Type;

public class Fireball extends Spell{
    public Fireball(){
        super("Fireball", "A flaming ball that has a 30% chance to burn an enemy", 10, new Affinity("Burn", Type.fire), 100, 6);
    }
}

package textAdventure.Spells;

import textAdventure.Affinity;
import textAdventure.Type;

public class BlazingVortex extends Spell{
    public BlazingVortex(){
        super("Blazing Vortex", "A swirling vortex that surrounds enemies, slowly damaging them", 10, new Affinity("Burn", Type.fire), 100, 6);
    }
}

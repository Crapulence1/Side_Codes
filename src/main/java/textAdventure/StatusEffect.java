package textAdventure;


public class StatusEffect{
    private String name;
    private int duration;
    private int damagePerTurn;
    private EffectType type;
    public StatusEffect(String name, int duration, int damagePerTurn, EffectType type){
        this.name = name;
        this.duration = duration;
        this.damagePerTurn = damagePerTurn;
        this.type = type;
    }

    public boolean isActive() {
        return duration > 0;
    }

    public int applyEffect(){
        if (duration > 0){
            duration--;
            return damagePerTurn;
        }
        return 0;
    }

    public int getDamagePerTurn() {
        return damagePerTurn;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public EffectType getType() {
        return type;
    }
}


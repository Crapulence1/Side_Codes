package textAdventure.Consumables;

public class Potion {
    private String name;
    private Buff buff;
    public Potion(String name, Buff buff){
        this.name = name;
        this.buff=buff;
    }

    public Buff getBuff() {
        return buff;
    }

    public String getName() {
        return name;
    }
}

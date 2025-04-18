package textAdventure;

public class Affinity {
    private String name;
    private Type type;
    public Affinity(String name, Type type){
        this.name=name;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setType(Type type) {
        this.type = type;
    }

    public String toString(){
        return getName() + " " + getType();
    }
}

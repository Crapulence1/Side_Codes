package textAdventure.Consumables;

public enum Buff {
    heal ("You feel your wounds heal.", "Restores HP"), //heals x hp
    mana ("You feel your energy return.", "Restores Mana"), //restores x mana
    defend ("You feel sturdy", "Reduces incoming damage"), // reduce incoming damage
    strength ("You feel powerful", "Increases outgoing damage"), //increase outgoing damage
    haste ("You feel lighter", "Allows two attacks in one turn"), //attack twice
    luck ("Fortune smiles at you", "Increase chance of spell effects"), //increase chance fpr effect from spell
    thaw ("Your body thaws", "Removes the slow effect"), //stop slow effect
    unstun ("You regain you're focus", "Removes the stun effect"), //fucking unstuns fuckass
    cool ("Your body heats back up", "Removes the burn effect"); //removes burn effect

    private String use;
    private String description;
    Buff(String use, String description) {
        this.use=use;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public String getUse() {
        return use;
    }
}

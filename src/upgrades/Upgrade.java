package edu.stuy.starlorn.upgrades;

/*
 * A generic upgrade. Modifies aspects of a ship
 */
public class Upgrade {
    protected String _name, _description;

    public Upgrade() {
        _name = "Unnamed Upgrade";
        _description = "Upgrade!";
    }

    /*
     * Get the name of the upgrade
     */
    public String getName() {
        return _name;
    }
    /*
     * Get a description to pop up when the upgrade is gathered.
     * Should be quick, and maybe funny
     * If this is an empty string, don't show anything at all
     */
    public String getDescription() {
        return _description;
    }
    
    public String getSpriteName() {
        return "upgrade/generic";
    }
}

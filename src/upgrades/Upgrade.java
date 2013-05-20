package edu.stuy.starlorn.upgrades;

/*
 * A generic upgrade. Modifies aspects of a ship
 */
public class Upgrade {
    private String _description;

    public Upgrade() {
        _description = "Upgrade!";
    }

    /*
     * Get a description to pop up when the upgrade is gathered.
     * Should be quick, and maybe funny
     */
    public String getDescription() {
        return _description;
    }
}

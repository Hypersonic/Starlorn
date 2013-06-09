package edu.stuy.starlorn.upgrades;

/*
 * A generic upgrade. Modifies aspects of a ship
 */
public class Upgrade {
    protected String _name, _description;
    protected boolean _ownedByPlayer;

    public Upgrade() {
        _name = "Unnamed Upgrade";
        _description = "Upgrade!";
        _ownedByPlayer = false;
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

    public boolean getOwnedByPlayer() {
        return _ownedByPlayer;
    }

    public void setOwnedByPlayer(boolean owned) {
        _ownedByPlayer = owned;
    }

    public Upgrade clone() {
        return new Upgrade();
    }
    
    public String getSpriteName() {
        return "upgrade/generic";
    }
}

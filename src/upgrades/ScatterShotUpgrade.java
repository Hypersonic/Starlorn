package edu.stuy.starlorn.upgrades;

public class ScatterShotUpgrade extends GunUpgrade {

    public ScatterShotUpgrade() {
        super();
        _description = "Scattershot!";
    }
    
    @Override
    public int getNumShots() {
        return 4;
    }

    @Override
    public double getAimAngle() {
        // Random angle going forwards
        return (Math.PI * Math.random()) - (Math.PI / 2);
    }

    @Override
    public int getShotSpeed(int shotspeed) {
        // Random speed, either 1, 2, or 3
        return (int) (Math.random() * 3) + shotspeed;
    }
}

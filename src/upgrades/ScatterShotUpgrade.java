package edu.stuy.starlorn.upgrades;

public class ScatterShotUpgrade extends GunUpgrade {
    
    @Override
    public int getNumShots() {
        // Shoot between 1 and 5 shots, inclusive
        return (int) (Math.random() * 5) + 1;
    }

    @Override
    public double getAimAngle() {
        // Random angle going forwards
        return (Math.PI * Math.random()) - (Math.PI / 2);
    }

    @Override
    public int getShotSpeed() {
        // Random speed, either 1, 2, or 3
        return (int) (Math.random() * 3) + 1;
    }
}

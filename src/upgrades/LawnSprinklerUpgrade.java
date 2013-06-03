package edu.stuy.starlorn.upgrades;

public class LawnSprinklerUpgrade extends GunUpgrade {
  
    private double _direction;

    public LawnSprinklerUpgrade() {
        super();
        _name = ".¸¸.·´¯`·.¸.Lawn Sprinkler!!!.¸¸.·´¯`·.¸.";
        _description = "wat r u srs";
        
    }
    
    @Override
    public int getNumShots() {
        return 1;
    }

    @Override
    public double getAimAngle() {
        if (_direction >= 2){
          _direction = 0;
        }
        else 
          _direction += 0.1;
        // Random angle going forwards
        return (Math.PI * (1 - _direction));
    }

    @Override
    public int getShotSpeed(int shotspeed) {
        // Random speed, either 1, 2, or 3
        return (int) 5 * shotspeed;
    }

    @Override
    public Upgrade clone() {
        return new LawnSprinklerUpgrade();
    }
}

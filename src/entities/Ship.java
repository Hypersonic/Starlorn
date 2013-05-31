package edu.stuy.starlorn.entities;

import edu.stuy.starlorn.upgrades.GunUpgrade;

import java.util.LinkedList;

public class Ship extends Entity {
    protected LinkedList<GunUpgrade> _gunupgrades;
    protected int _baseDamage, _baseShotSpeed, _health, _maxHealth, _cooldownTimer, _cooldown, _cooldownRate, _movementSpeed;
    protected double _baseAim;
    protected boolean _shootRequested;
    //protected Texture _texture;

    public Ship() {
        super();
        _gunupgrades = new LinkedList<GunUpgrade>();
        _baseDamage = 1;
        _baseShotSpeed = 1;
        _maxHealth = 10;
        _health = _maxHealth;
        _baseAim = 0; //Aim up by default
        _cooldown = 10;
        _cooldownTimer = 0;
        _cooldownRate = 1;
        _movementSpeed = 1;
        _shootRequested = false;
        /*
		try {
			_texture = TextureLoader.getTexture("PNG",
					ResourceLoader.getResourceAsStream("res/spaceship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        */
    }

	public void draw() {
	}

    public void addUpgrade(GunUpgrade upgrade) {
        _gunupgrades.add(upgrade);
    }

    public boolean isHit(Bullet b) {
        return (b.getX() + b.getXvel() + b.getWidth() > _xcor + _xvel &&
                b.getX() + b.getXvel() < _xcor + _width + _xvel &&
                b.getY() + b.getYvel() + b.getHeight() > _ycor + _yvel &&
                b.getY() + b.getYvel() < _ycor + _height + _yvel);
    }

	/*
	 * Create the shots based on the available GunUpgrades
	 */
	public void shoot() {
		GunUpgrade topShot = _gunupgrades.get(0);
		int damage = _baseDamage;
		int shotSpeed = _baseShotSpeed;
		for (GunUpgrade up : _gunupgrades) {
			if (up.getNumShots() > topShot.getNumShots())
				topShot = up;
			damage = up.getDamage(damage);
			shotSpeed = up.getShotSpeed(shotSpeed);
		}
		// Create new shots, based on dem vars
		int numShots = topShot.getNumShots();
		for (int i = 0; i < numShots; i++) {
			Bullet b = new Bullet(_baseAim + topShot.getAimAngle(), damage,
					shotSpeed);
            b.setXY(_xcor + topShot.getXOffset(), _ycor + 10);
			b.setWorld(this.getWorld());
		}
	}
    @Override
    public void step() {
        //Only cooldown if we're below the rate, otherwise the ship hasn't tried to shoot
        if (_cooldownTimer <= 0 && _shootRequested) {
            this.shoot();
            _cooldownTimer = _cooldown;
        } else {
            _cooldownTimer -= _cooldownRate;
        }
        super.step();
    }

    public void setBaseDamage(int damage) {
        _baseDamage = damage;
    }

    public int getBaseDamage() {
        return _baseDamage;
    }

    public void setBaseShotSpeed(int speed) {
        _baseShotSpeed = speed;
    }

    public int getBaseShotSpeed() {
        return _baseShotSpeed;
    }

    public void setMaxHealth(int health) {
        _maxHealth = health;
    }

    public int getMaxHealth() {
        return _maxHealth;
    }

    public void setCooldown(int cooldown) {
        _cooldown = cooldown;
    }

    public int getCooldown() {
        return _cooldown;
    }

    public void setCooldownRate(int rate) {
        _cooldownRate = rate;
    }

    public int getCooldownRate() {
        return _cooldownRate;
    }

    public void setMovementSpeed(int speed) {
        _movementSpeed = speed;
    }

    public int getMovementSpeed() {
        return _movementSpeed;
    }
}

package edu.stuy.starlorn.entities;

import edu.stuy.starlorn.upgrades.GunUpgrade;

import java.io.IOException;
import java.util.LinkedList;

import org.lwjgl.input.Keyboard;

import org.lwjgl.opengl.GL11;

public class Ship extends Entity {
    protected LinkedList<GunUpgrade> _gunupgrades;
    protected int _baseDamage, _baseShotSpeed, _health, _cooldownTimer, _cooldown, _cooldownRate, _movementSpeed;
    protected double _baseAim;
    protected boolean _shootRequested;
    //protected Texture _texture;

    public Ship() {
        super();
        _gunupgrades = new LinkedList<GunUpgrade>();
        _baseDamage = 1;
        _baseShotSpeed = 1;
        _health = 10;
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
		render();
	}

	public void render() {
		int x = 1;
		int y = 2;
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)){
			y = 1;
			_ycor--;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			x = 2;
			_ycor++;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			y = 0;
			_ycor++;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			x = 0;
			_xcor--;
		}
		
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
}

package edu.stuy.starlorn.entities;

import edu.stuy.starlorn.upgrades.GunUpgrade;

import java.io.IOException;
import java.util.LinkedList;

import org.lwjgl.input.Keyboard;

import org.lwjgl.opengl.GL11;

public class Ship extends Entity {
    protected LinkedList<GunUpgrade> _gunupgrades;
    protected int _baseDamage, _baseShotSpeed, _health, _fullCooldown, _cooldown, _cooldownRate, _movementSpeed;
    protected double _baseAim;
    //protected Texture _texture;

    public Ship() {
        super();
        _gunupgrades = new LinkedList<GunUpgrade>();
        _baseDamage = 1;
        _baseShotSpeed = 1;
        _health = 10;
        _baseAim = 0; //Aim up by default
        _cooldown = 10;
        _fullCooldown = _cooldown+1;
        _cooldownRate = 1;
        _movementSpeed = 1;
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

    /*
	public void ihopethisworks(int tx, int ty) {
		Color.white.bind();

		_texture.bind();

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(tx * 0.078125, ty * 0.078125);
		GL11.glVertex2d(_xcor, y);
		GL11.glTexCoord2d((tx + 1) * 0.078125, ty * 0.078125);
		GL11.glVertex2d(_xcor + 64, _ycor);
		GL11.glTexCoord2d((tx + 1) * 0.078125, (ty + 1) * 0.078125);
		GL11.glVertex2d(_xcor + 64, _ycor + 64);
		GL11.glTexCoord2d(tx * 0.078125, (ty + 1) * 0.078125);
		GL11.glVertex2d(_xcor, _ycor + 64);
		GL11.glEnd();
	}
    */

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
			b.setWorld(this.getWorld());
		}
	}
    @Override
    public void step() {
        //Only cooldown if we're below the rate, otherwise the ship hasn't tried to shoot
        if (_fullCooldown <= _cooldown) {
            if (_fullCooldown < 0) {
                this.shoot();
                _fullCooldown = _cooldown+1;
            } else {
                _fullCooldown -= _cooldownRate;
            }
        }
        super.step();
    }

}

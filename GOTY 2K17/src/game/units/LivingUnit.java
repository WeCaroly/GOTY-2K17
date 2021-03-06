package game.units;

import java.awt.image.BufferedImage;

import game.TileWorld;


public abstract class LivingUnit extends Unit {
	private final int ATTACK_SPEED = 1; // attack rate adjustments to all living units
	
	private int maxHealth;
	private int health;
	private int attackSpeed;

	protected String name;
	
	protected LivingUnit(BufferedImage image, TileWorld world, int maxHealth, int horizontalFrameNum, int verticalFrameNum) {
		super(image, world, horizontalFrameNum, verticalFrameNum);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.setAttackSpeed(ATTACK_SPEED);
	}

	public int getHealth() {
		return health;
	}

	protected void setHealth(int health) {
		this.health = health;
	}
	
	protected int getAttackSpeed() {
		return attackSpeed;
	}
	
	protected void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
}

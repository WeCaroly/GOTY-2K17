package game.units;

import java.awt.image.BufferedImage;


public class LivingUnit extends Unit {
	private final int ATTACK_SPEED = 1; // attack rate adjustments to all living units
	
	private int maxHealth;
	private int health;
	private int attackSpeed;

	protected LivingUnit(BufferedImage image, int maxHealth) {
		super(image);
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
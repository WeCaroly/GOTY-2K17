package game.units;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.TileWorld;
import game.sprites.BoundingSprite;

public abstract class Unit extends BoundingSprite {
	
	private TileWorld world;
	private MovementTask movementTask;
	
	private int damagePerHit;
	
	private ArrayList<BufferedImage> attackAnimation;
		
	protected Unit(BufferedImage image, TileWorld world, int horizontalFrameNum, int verticalFrameNum) {
		super(image, horizontalFrameNum, verticalFrameNum);
		this.world = world;
		attackAnimation = new ArrayList<BufferedImage>();
		this.createAttackAnimation();
	}

	public int getDamagePerHit() {
		return damagePerHit;
	}
	
	public void setDamagePerHit(int damage) {
		damagePerHit = damage;
	}
	
	public TileWorld getWorld() {
		return world;
	}
	
	public void setWorld(TileWorld world) {
		this.world = world;
	}

	public MovementTask getMovementTask() {
		return movementTask;
	}

	public void setMovementTask(MovementTask movementTask) {
		this.movementTask = movementTask;
	}

	public void createAttackAnimation(){
		int FS = this.getFrameSize();
		if (FS == 0) {
			return;
		}
		int horizontalFrameNum = this.getHorizontalFrameNum();
		int verticalFrameNum = this.getVerticalFrameNum();
		for (int i = 0; i < verticalFrameNum; i++) {
			for (int j = 0; j < horizontalFrameNum; j++) {
				attackAnimation.add(this.getSpriteSheet().getSubimage(j * FS, i * FS, FS, FS));
			}
		}
	}
	
	public ArrayList<BufferedImage> getAttackAnimation(){
		return attackAnimation;
	}
	
	public abstract String getName();
}

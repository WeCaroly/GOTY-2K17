package game.units;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.TileWorld;
import game.sprites.BoundingSprite;

public abstract class Unit extends BoundingSprite {
	
	private TileWorld world;
	
	private int damagePerHit;
	
	private ArrayList<BufferedImage> attackAnimation;
		
	protected Unit(BufferedImage image, TileWorld world, int horizontalFrameNum, int verticalFrameNum) {
		super(image, horizontalFrameNum, verticalFrameNum);
		attackAnimation = new ArrayList<BufferedImage>();
		this.createAnimation();
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
	
	public void createAnimation(){
		int FS = this.getFrameSize();
		int horizontalFrameNum = this.getHorizontalFrameNum();
		int verticalFrameNum = this.getVerticalFrameNum();
		for(int i = 0; i < verticalFrameNum; i++){
			for(int j = 0; j < horizontalFrameNum; j++){
				attackAnimation.add(this.getSpriteSheet().getSubimage(i * FS, j * FS, FS, FS));
			}
		}
	}
	
	public ArrayList<BufferedImage> getAttackAnimation(){
		return attackAnimation;
	}
}

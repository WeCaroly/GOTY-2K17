package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.maps.Map2;
import game.sprites.BoundingSprite;
import game.sprites.SpellSprite;
import game.units.Unit;
import game.units.monsters.Zombie;
import game.util.Matrix3x3f;
import game.util.SimpleFramework;
import game.vectors.Vector2f;
import game.vectors.VectorObject;


public class TileFramework extends SimpleFramework {

	public static final int TILES_X = 30;
	public static final int TILES_Y = 20;
	
	private static final float APP_WORLD_WIDTH = 6f;
	private static final float APP_WORLD_HEIGHT = 4f;

	public static final float TILE_SIZE_X = APP_WORLD_WIDTH / (float) TILES_X;
	public static final float TILE_SIZE_Y = APP_WORLD_HEIGHT / (float) TILES_Y;
	
	private BoundingSprite actor;
	private List<BoundingSprite> boundingSprites;
	private List<SpellSprite> spellSprites;
	
	boolean displayBounds;
	
	private TileWorld tileWorld;

	public TileFramework() {
		appWidth = 1440;
		appHeight = 960;
		appWorldWidth = APP_WORLD_WIDTH;
		appWorldHeight = APP_WORLD_HEIGHT;
		appSleep = 0L;
		appTitle = "Sprite Game";
		appBackground = Color.DARK_GRAY;
		setResizable(false);
	}

	@Override
	protected void initialize() {
		super.initialize();
		tileWorld = new TileWorld(new Map2(), TILES_X, TILES_Y, TILE_SIZE_X, TILE_SIZE_Y, APP_WORLD_WIDTH, APP_WORLD_HEIGHT);
		
		boundingSprites = new ArrayList<>();
		spellSprites = new ArrayList<>();
		
		// Actor
		actor = new Zombie();
		actor.setLocation(new Vector2f(0, 0));
	}
	
	@Override
	protected void processInput(float delta) {
		super.processInput(delta);
		// Movement
		boolean walking = false;
		if (keyboard.keyDown(KeyEvent.VK_W)) {
			actor.move(new Vector2f(0F, 0.2F), boundingSprites, delta);
//			actor.setDirection(Direction.NORTH);
			walking = true;
		} else if (keyboard.keyDown(KeyEvent.VK_S)) {
			actor.move(new Vector2f(0F, -0.2F), boundingSprites, delta);
//			actor.setDirection(Direction.SOUTH);
			walking = true;
		} else if (keyboard.keyDown(KeyEvent.VK_A)) {
			actor.move(new Vector2f(-0.2F, 0F), boundingSprites, delta);
//			actor.setDirection(Direction.WEST);
			walking = true;
		} else if (keyboard.keyDown(KeyEvent.VK_D)) {
			actor.move(new Vector2f(0.2F, 0F), boundingSprites, delta);
//			actor.setDirection(Direction.EAST);
			walking = true;
		}
		
		// Reset
		if (keyboard.keyDownOnce(KeyEvent.VK_R)) {
//			actor.setDead(false);
			actor.setLocation(new Vector2f());
			actor.setRotation(0F);
//			actor.setDirection(Direction.SOUTH);
		}
		
		// Spawn Spell
		if (keyboard.keyDownOnce(KeyEvent.VK_SPACE)) {
			SpellSprite spell = new SpellSprite();
			spell.setLocation(new Vector2f(-1.1F, 0F));
			spellSprites.add(spell);
		}
		
		// Display Bounds
		if (keyboard.keyDownOnce(KeyEvent.VK_B)) {
			displayBounds = !displayBounds;
		}
//		actor.setWalking(walking);
//		actor.updateAnimations(delta);
	}

	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
		
		// Move spells
		for (int i = spellSprites.size() - 1; i >= 0; i--) {
			SpellSprite sprite = spellSprites.get(i);
			sprite.move(new Vector2f(0.4F, 0), new ArrayList<>(), delta);
			if (sprite.getLocation().x > 1.5F
					|| sprite.getLocation().y > 1.5F
					|| sprite.getLocation().y < -0.5F) {
				spellSprites.remove(i);
			}
			sprite.setRotation(sprite.getRotation() + 4F * delta);
			if (sprite.hasAnyBoundViolations(Arrays.asList(actor))) {
				spellSprites.remove(i);
//				actor.setDead(true);
			}
		}
	}

	@Override
	protected void render(Graphics g) {
		super.render(g);
		Graphics2D g2d = (Graphics2D) g;
		Matrix3x3f view = getViewportTransform();
		
		// Actor
		actor.setView(view);
		actor.draw(g2d);
		if (displayBounds) {
			renderBounds(actor, g2d);
		}

		// Objects
		for (BoundingSprite sprite : boundingSprites) {
			sprite.setView(view);
			sprite.draw(g2d);
			if (displayBounds) {
				renderBounds(sprite, g2d);
			}
		}
		
		// Spells
		for (SpellSprite sprite : spellSprites) {
			sprite.setView(view);
			sprite.draw(g2d);
			if (displayBounds) {
				renderBounds(sprite, g2d);
			}
		}
	}
	
	public void renderTiles(Graphics2D g2d) {
		Matrix3x3f view = getViewportTransform();
		for (Tile[] tiles : tileWorld.getTiles()) {
			for (Tile tile : tiles) {
				for (Unit unit : tile.getUnits()) {
					unit.setView(view);
					unit.draw(g2d);
				}
			}
		}
	}
	
	public void renderBounds(BoundingSprite sprite, Graphics2D g) {
		g.setColor(Color.RED);
		if (sprite.getOuterBound() != null) {
			sprite.getOuterBound().render(g);
		}
		if (sprite.getInnerBounds() != null) {
			g.setColor(Color.BLUE);
			for (VectorObject innerBound : sprite.getInnerBounds()) {
				innerBound.render(g);
			}
		}
	}
	
	public void renderGrid(Graphics2D g) {
		g.setColor(Color.RED);
		for (int y = 0; y < 674; y += 48) {
			g.drawLine(0, y, appWidth, y);
		}
		for (int x = 0; x < appWidth; x += 48) {
			g.drawLine(x, 0, x, 673);
		}
	}
	
	public Point convertTileLocationToPoint(TileLocation loc) {
		return new Point(loc.getX() * 48, loc.getY() * 48);
	}
	
	public TileWorld getWorld() {
		return tileWorld;
	}
	
}
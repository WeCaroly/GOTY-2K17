package edu.unomaha.nhippen.sprite.sprites;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

import edu.unomaha.nhippen.sprite.game.TileFramework;
import edu.unomaha.nhippen.sprite.vectors.AxisAlignedBoundingBox;
import edu.unomaha.nhippen.sprite.vectors.Vector2f;

public class TreeSprite extends BoundingSprite {

	private static BufferedImage BASE_IMAGE;
	
	static {
		try {
			URL url = TreeSprite.class.getResource("/sprites/Tiles.png");
			BufferedImage spriteSheet = ImageIO.read(url);
			BASE_IMAGE = spriteSheet.getSubimage(48, 0, 48, 96);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public TreeSprite() {
		super(BASE_IMAGE);
		setOuterBound(new AxisAlignedBoundingBox(
				new Vector2f(-TileFramework.TILE_SIZE_X / 4F, -TileFramework.TILE_SIZE_Y / 2),
				new Vector2f(TileFramework.TILE_SIZE_X / 4F, -TileFramework.TILE_SIZE_Y)));
	}

}

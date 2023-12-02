package tile;

import java.awt.image.BufferedImage;

/**
 * Clase base para la creacion de casillas
 */
public class Tile {
	
	private int size;

	public BufferedImage image;
	public boolean collision = false;
	
	int tileSize() {
		return size;
	}
	
}

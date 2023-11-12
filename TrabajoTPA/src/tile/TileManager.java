package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.PanelDeJuego;

public class TileManager {

	PanelDeJuego gp;
	Tile [] tile;
	int mapTileNum [] [];
	
	public TileManager (PanelDeJuego gp) {
		
		this.gp = gp;
		tile = new Tile [10];
		mapTileNum = new int [gp.columnasPantalla] [gp.filasPantalla];
		
		getTileImage ();
		loadMap();
		
	}
	
	public void getTileImage () {
		
		try {
			
			tile [0] = new Tile ();
			tile [0].image = ImageIO.read(getClass ().getResourceAsStream("/tiles/grass.png"));
			
			tile [1] = new Tile ();
			tile [1].image = ImageIO.read(getClass ().getResourceAsStream("/tiles/wall.png"));
			
			tile [2] = new Tile ();
			tile [2].image = ImageIO.read(getClass ().getResourceAsStream("/tiles/water.png"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap () {
		
		try {
			
			InputStream is = getClass ().getResourceAsStream("/maps/map01.txt");
			BufferedReader br = new BufferedReader (new InputStreamReader (is));
			
			int col = 0;
			int row = 0;
			
			//Este bucle while lee el archivo, lo almacena como strings y luego lo transforma en numeros para poder representarlo posteriormente almacenandolo en la matriz mapTileNum
			while (col < gp.columnasPantalla && row < gp.filasPantalla) {
				
				String line = br.readLine ();
				
				while (col < gp.columnasPantalla) {
					
					String numbers [] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum [col] [row] = num;
					col++;
				}
				if (col == gp.columnasPantalla) {
					col = 0;
					row ++;
				}
			}
			br.close ();
			
		}catch (Exception e) {
			
		}
		
	}
	
	public void draw (Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while (col < gp.columnasPantalla && row < gp.filasPantalla) {
			
			int tileNum = mapTileNum [col] [row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tamFinalCasilla, gp.tamFinalCasilla, null);
			col ++;
			x += gp.tamFinalCasilla;
			
			if (col == gp.columnasPantalla) {
				col = 0;
				x = 0;
				row ++;
				y += gp.tamFinalCasilla;
			}
			
		}
		
	}
	
}

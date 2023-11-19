package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.PanelDeJuego;

/**
 * Clase que gestiona las casillas que se representan en pantalla
 */
public class TileManager {

	PanelDeJuego gp;
	public Tile [] tile;
	public int mapTileNum [] [];
	
	public TileManager (PanelDeJuego gp) {
		
		this.gp = gp;
		tile = new Tile [10];
		mapTileNum = new int [gp.maxColMundo] [gp.maxRowMundo];
		
		getTileImage ();
		loadMap ("/maps/wildernesslevel1.txt");
	}
	
	/**
	 * Funcion que permite leer los .png de los sprites y almacenarlos en un array para su futuro uso
	 * Tambien se asigna true a las casillas que van a tener colision
	 */
	public void getTileImage () {
		
		try {
			
			tile [0] = new Tile ();
			tile [0].image = ImageIO.read(getClass ().getResourceAsStream("/tiles/grass.png"));
			
			tile [1] = new Tile ();
			tile [1].image = ImageIO.read(getClass ().getResourceAsStream("/tiles/wall.png"));
			tile [1].collision = true;
			
			tile [2] = new Tile ();
			tile [2].image = ImageIO.read(getClass ().getResourceAsStream("/tiles/water.png"));
			tile [2].collision = true;
			
			tile [3] = new Tile ();
			tile [3].image = ImageIO.read(getClass ().getResourceAsStream("/tiles/sand.png"));
			
			tile [4] = new Tile ();
			tile [4].image = ImageIO.read(getClass ().getResourceAsStream("/tiles/tree.png"));
			tile [4].collision = true;
			
			tile [5] = new Tile ();
			tile [5].image = ImageIO.read(getClass ().getResourceAsStream("/tiles/earth.png"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Funcion que utiliza un string (filePath) para leer un mapa almacenado en un txt y almacenar en un array el sprite de casilla que corresponda con el numero que coincida entre el txt y el array de imagenes
	 * @param filePath
	 */
	public void loadMap (String filePath) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader (new InputStreamReader (is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxColMundo && row < gp.maxRowMundo) {
				
				String line = br.readLine();
				
				while (col < gp.maxColMundo) {
					
					String numbers [] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum [col] [row] = num;
					col ++;
					
				}
				if (col == gp.maxColMundo) {
					col = 0;
					row++;
				}
			}
			br.close ();
		}catch (Exception e) {
			
		}
		
	}
	
	/**
	 * Dibuja el mapa en pantalla
	 * El if contenido en el bucle while sirve para que el mapa solo se dibuje si esta en el rango de visualizacion de la ventana para optimizar el proceso de dibujado
	 * @param g2
	 */
	public void draw (Graphics2D g2) {
		
		int columnasMundo = 0;
		int filasMundo = 0;
		
		while (columnasMundo < gp.maxColMundo && filasMundo < gp.maxRowMundo) {
			
			int tileNum = mapTileNum [columnasMundo] [filasMundo];
			
			//MudnoX e Y es la posicion del jugador en el mapa
			int mundoX = columnasMundo * gp.tamFinalCasilla;
			int mundoY = filasMundo * gp.tamFinalCasilla;
			
			//Posicion de las coordenadas 0 0 en relacion con la posicion del jugador
			int pantallaX = mundoX - gp.player.mundoX + gp.player.pantallaX;
			int pantallaY = mundoY - gp.player.mundoY + gp.player.pantallaY;
			
			if (mundoX + gp.tamFinalCasilla> gp.player.mundoX - gp.player.pantallaX && mundoX - gp.tamFinalCasilla < gp.player.mundoX + gp.player.pantallaX && mundoY + gp.tamFinalCasilla > gp.player.mundoY - gp.player.pantallaY && mundoY - gp.tamFinalCasilla < gp.player.mundoY + gp.player.pantallaY) {
				
				g2.drawImage(tile [tileNum].image, pantallaX, pantallaY, gp.tamFinalCasilla, gp.tamFinalCasilla, null);
				
			}
			
			columnasMundo ++;
			
			if (columnasMundo == gp.maxColMundo) {
				columnasMundo = 0;
				filasMundo ++;
			}
			
		}
		
	}
	
}

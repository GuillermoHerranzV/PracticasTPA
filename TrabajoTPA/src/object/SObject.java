package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.PanelDeJuego;

/**
 * Clase de la que heredan el resto de objetos
 */
public class SObject {

	public BufferedImage image;
	public String nombre;
	public boolean colision = false;
	public int mundoX, mundoY;
	
	/**
	 * Dibuja los objetos en el mundo
	 * @param g2
	 * @param gp
	 */
	public void draw (Graphics2D g2, PanelDeJuego gp) {
		
		int pantallaX = mundoX - gp.player.mundoX + gp.player.pantallaX;
		int pantallaY = mundoY - gp.player.mundoY + gp.player.pantallaY;
		
		if (mundoX + gp.tamFinalCasilla> gp.player.mundoX - gp.player.pantallaX && mundoX - gp.tamFinalCasilla < gp.player.mundoX + gp.player.pantallaX && mundoY + gp.tamFinalCasilla > gp.player.mundoY - gp.player.pantallaY && mundoY - gp.tamFinalCasilla < gp.player.mundoY + gp.player.pantallaY) {
			
			g2.drawImage(image, pantallaX, pantallaY, gp.tamFinalCasilla, gp.tamFinalCasilla, null);
			
		}
		
	}
	
}

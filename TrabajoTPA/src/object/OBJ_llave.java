package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.PanelDeJuego;

/**
 * Clase llave que hereda de Entity
 */
public class OBJ_llave extends Entity{
	
	public OBJ_llave (PanelDeJuego gp) {
		super (gp);	
		
		nombre = "Llave";
		down1 = setup ("/objects/key");
		
	}
	public void setAction() {}
	public void speak() {}
}

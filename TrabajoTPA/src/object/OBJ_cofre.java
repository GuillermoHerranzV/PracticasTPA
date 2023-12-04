package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.PanelDeJuego;

/**
 * Clase cofre que hereda de Entity
 */
public class OBJ_cofre extends Entity{
	
	public OBJ_cofre (PanelDeJuego gp) {
		super (gp);	
		
		nombre = "Cofre";
		down1 = setup ("/objects/chest");
		
	}
	public void setAction() {}
	public void speak() {}
}

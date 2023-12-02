package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.PanelDeJuego;

/**
 * Clase cofre que hereda de SObject
 */
public class OBJ_cofre extends Entity{
	
	public OBJ_cofre (PanelDeJuego gp) {
		super (gp);	
		
		nombre = "Cofre";
		down1 = setup ("/objects/chest");
		
	}

}

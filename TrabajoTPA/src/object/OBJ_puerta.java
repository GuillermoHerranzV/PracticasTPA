package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.PanelDeJuego;

/**
 * Clase puerta que hereda de Entity
 */
public class OBJ_puerta extends Entity{
	
	public OBJ_puerta (PanelDeJuego gp) {
		super (gp);	
		
		nombre = "Puerta";
		down1 = setup ("/objects/door");
		colision = true;
		
		areaSolida.x = 0;
		areaSolida.y = 16;
		areaSolida.width = 48;
		areaSolida.height = 32;
		areaSolidaDefaultX = areaSolida.x;
		areaSolidaDefaultY = areaSolida.y;
	}
	public void setAction() {}
	public void speak() {}
}

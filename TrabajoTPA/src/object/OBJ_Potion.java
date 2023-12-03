package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.PanelDeJuego;

public class OBJ_Potion extends Entity{

	public OBJ_Potion (PanelDeJuego gp) {
		super (gp);	
		
		nombre = "Botas";
		down1 = setup ("/objects/potion");
		
	}
	public void setAction() {}
	public void speak() {}
}

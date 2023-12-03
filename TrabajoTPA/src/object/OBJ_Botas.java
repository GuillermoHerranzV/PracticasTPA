package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.PanelDeJuego;

public class OBJ_Botas extends Entity{

	public OBJ_Botas (PanelDeJuego gp) {
		super (gp);	
		
		nombre = "Botas";
		down1 = setup ("/objects/boots");
		
	}
	public void setAction() {}
	public void speak() {}
}

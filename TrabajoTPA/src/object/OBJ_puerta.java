package object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Clase puerta que hereda de SObject
 */
public class OBJ_puerta extends SObject{

	public OBJ_puerta () {
		
			nombre = "Puerta";
			try {
			
				image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			
			}catch (IOException e) {
			e.printStackTrace();
			}
		
	}
	
}

package object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Clase llave que hereda de SObject
 */
public class OBJ_llave extends SObject{

	public OBJ_llave () {
		
		nombre = "Llave";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

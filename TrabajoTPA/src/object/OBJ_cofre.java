package object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Clase cofre que hereda de SObject
 */
public class OBJ_cofre extends SObject{
	
	public OBJ_cofre () {
		
			nombre = "Cofre";
			try {
			
				image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			
			}catch (IOException e) {
			e.printStackTrace();
			}
		
	}

}

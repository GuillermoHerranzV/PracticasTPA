package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Botas extends SObject{

public OBJ_Botas () {
		
		nombre = "Botas";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

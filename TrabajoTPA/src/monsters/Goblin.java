package monsters;

import java.util.Random;

import entity.Entity;
import main.PanelDeJuego;

public class Goblin extends Entity implements Monster {
	
	public Goblin (PanelDeJuego gp) {
    	
    	super(gp);    	
    	nombre = "Goblin";
    	speed = 1;
        maxhp = 250;
        hp = maxhp;
        dmg = 40;
        
        areaSolida.x = 3;
        areaSolida.y = 18;
        areaSolida.width = 42;
        areaSolida.height = 30;
        areaSolidaDefaultX = areaSolida.x;
        areaSolidaDefaultY = areaSolida.y;
        
        getImagen ();
        
    }

	public void getImagen () {
    	
    	up1 = setup ("/monsters/goblin_2");
    	up2 = setup ("/monsters/goblin_2");
    	down1 = setup ("/monsters/goblin_2");
    	down2 = setup ("/monsters/goblin_2");
    	left1 = setup ("/monsters/goblin_2");
    	left2 = setup ("/monsters/goblin_2");
    	right1 = setup ("/monsters/goblin_2");
    	right2 = setup ("/monsters/goblin_2");
    	
    }
	
	public void setAction () {
    	
    	/*contadorAccion++;
    	
    	if(contadorAccion == 120) {
    	
    	Random random = new Random();
    	int i = random.nextInt(100)+1; //Coge un numero del 1 al 100
    	
    	if(i <= 25) {
    		
    		direction = "up";
    	}
    	
    	if(i > 25 && i <= 50) {
    		direction = "down";
    	}
    	
    	if(i > 50 && i <= 75) {
    		direction = "left";
    	}
    	
    	if(i > 75 && i <= 100) {
    		direction = "right";
    	}
    	
    	contadorAccion = 0;
    	}*/
    	
    }
	public void speak() {}
	
}

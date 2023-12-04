package monsters;

import entity.Entity;
import main.PanelDeJuego;

/**
 * CLASE Boss QUE HEREDA DE Entity E IMPLEMENTA Monster
 */
public class Boss extends Entity implements Monster{
	
	//CONSTRUCTOR
	public Boss (PanelDeJuego gp) {
    	
    	super(gp);    	
    	nombre = "Demonio";
    	speed = 1;
        maxhp = 500;
        hp = maxhp;
        dmg = 50;
        direction = "estatico";
        
        areaSolida.x = 3;
        areaSolida.y = 18;
        areaSolida.width = 42;
        areaSolida.height = 30;
        areaSolidaDefaultX = areaSolida.x;
        areaSolidaDefaultY = areaSolida.y;
        
        getImagen ();
        
    }
	
	/**
	 * COLOCA LA IMAGEN
	 */
	public void getImagen () {
    	
    	estatico1 = setup ("/monsters/Demon_1");
    	estatico2 = setup ("/monsters/Demon_2");
    	down1 = setup ("/monsters/Demon_2");
    	
    }
	
	/**
	 * HACE QUE SE MUEVA ALEATORIAMENTE, SE PUEDE DESCOMENTAR PARA QUE SE MUEVA
	 */
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

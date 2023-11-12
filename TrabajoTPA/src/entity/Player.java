package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.Controles;
import main.PanelDeJuego;

/**
 * Clase jugador que hereda de Entity y amplia funcionalidades como las de interactuar...
 */
public class Player extends Entity{
    private double exp;
    private List<String> items;
    
    PanelDeJuego gp;
    Controles key;

    public Player(PanelDeJuego gp, Controles key) {
    	
    	this.gp = gp;
    	this.key = key;
    	
    	setDefaultValues ();
    	getPlayerImage();
    	
        items = new ArrayList<String>();
        exp = 0.0;
    }
    
    public void setDefaultValues () {
    	
    	x = 100;
    	y = 100;
    	speed = 4;
    	direction = "down";
    	
    }
    
    public void getPlayerImage () {
    	
    	try {
    		
    		up1 = ImageIO.read (getClass().getResourceAsStream("/player/boy_up_1.png"));
    		up2 = ImageIO.read (getClass().getResourceAsStream("/player/boy_up_2.png"));
    		down1 = ImageIO.read (getClass().getResourceAsStream("/player/boy_down_1.png"));
    		down2 = ImageIO.read (getClass().getResourceAsStream("/player/boy_down_2.png"));
    		left1 = ImageIO.read (getClass().getResourceAsStream("/player/boy_left_1.png"));
    		left2 = ImageIO.read (getClass().getResourceAsStream("/player/boy_left_2.png"));
    		right1 = ImageIO.read (getClass().getResourceAsStream("/player/boy_right_1.png"));
    		right2 = ImageIO.read (getClass().getResourceAsStream("/player/boy_right_2.png"));
    		
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void update () {
    	
    	if (key.arriba == true || key.abajo == true || key.izq == true || key.der == true) {
    		
    		if (key.arriba == true) {
    			direction = "up";
    			y -= speed;
    			
    		}else if (key.abajo == true) {
    			direction = "down";
    			y += speed;
    			
    		}else if (key.izq == true) {
    			direction = "left";
    			x -= speed;
    			
    		}else if (key.der == true) {
    			direction = "right";
    			x += speed;
    			
    		}
        	
        	spriteCounter ++;
        	if (spriteCounter > 15) {
        		if (spriteNum == 1) {
        			spriteNum = 2;
        		}else if (spriteNum == 2) {
        			spriteNum = 1;
        		}
        		spriteCounter = 0;
        	}
    		
    	}
    	
    }
    
    public void draw (Graphics2D g2) {
    	
    	//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tamFinalCasilla, gp.tamFinalCasilla);
    	
    	BufferedImage image = null;
    	
    	//Actualiza el sprite segun la direccion en la que nos movemos
    	switch (direction) {
    	
    	case "up":
    		if (spriteNum == 1) {
    			image = up1;
    		}
    		if (spriteNum == 2) {
    			image = up2;
    		}
    		break;
    	case "down":
    		if (spriteNum == 1) {
    			image = down1;
    		}
    		if (spriteNum == 2) {
    			image = down2;
    		}
    		break;
    	case "left":
    		if (spriteNum == 1) {
    			image = left1;
    		}
    		if (spriteNum == 2) {
    			image = left2;
    		}
    		break;
    	case "right":
    		if (spriteNum == 1) {
    			image = right1;
    		}
    		if (spriteNum == 2) {
    			image = right2;
    		}
    		break;
    	
    	}
    	
    	//Dibuja el sprite
    	g2.drawImage(image, x, y, gp.tamFinalCasilla, gp.tamFinalCasilla, null);
    	
    }
    
    public double getExp() {
        return exp;
    }

    /**
     * Funcion para saber los objetos que lleva el jugador
     * @return retorna la lista de objetos
     */
    public List<String> getItems() {
        return items;
    }

    /**
     * Funcion para sumar objetos al inventario del jugador
     * @param item, es el item recogido
     */
    public void setItems(String item) {
        items.add(item);
    }
    
    public void interact() {
        
    }
    
    public void flee() {
        
    }
    
    public void useItem() {
        
    }
    
    public void gainXP() {
        
    }
    
    /**
     * Funcion para indicar la subida de nivel
     */
    public void levelUp() {
        exp = ++exp;
        System.out.println("Congratulations! You have leveled up! Your new level is: " + this.getExp());
    }
    
}

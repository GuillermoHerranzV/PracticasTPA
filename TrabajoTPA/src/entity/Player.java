package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
    
    public final int pantallaX;
    public final int pantallaY;

    public Player(PanelDeJuego gp, Controles key) {
    	
    	this.gp = gp;
    	this.key = key;
    	
    	//Posicion donde se dibuja el sprite del jugador
    	pantallaX = gp.anchoVentana /2 - (gp.tamFinalCasilla/2);
    	pantallaY = gp.altoVentana /2 - (gp.tamFinalCasilla/2);
    	
    	areaSolida = new Rectangle ();
    	areaSolida.x = 16;
    	areaSolida.y = 32;
    	areaSolida.width = 16;
    	areaSolida.height = 16;
    	
    	setDefaultValues ();
    	getPlayerImage();
    	
        items = new ArrayList<String>();
        exp = 0.0;
    }
    
    public void setDefaultValues () {
    	
    	mundoX = gp.tamFinalCasilla * 23;
    	mundoY = gp.tamFinalCasilla * 21;
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
    		
    		if (key.arriba == true) {direction = "up";
    		}else if (key.abajo == true) {direction = "down";
    		}else if (key.izq == true) {direction = "left";
    		}else if (key.der == true) {direction = "right";}
    		
    		//Comprobar colision del jugador
    		colisionOn = false;
    		gp.cChecker.comprobarCasilla (this);
    		
    		//Si no hay colision el jugador se puede mover
    		if (colisionOn == false) {
    			
    			switch (direction) {
    			
    			case "up":
    				mundoY -= speed;
    				break;
    			case "down":
    				mundoY += speed;
    				break;
    			case "left":
    				mundoX -= speed;
    				break;
    			case "right":
    				mundoX += speed;
    				break;
    			
    			}
    			
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
    	g2.drawImage(image, pantallaX, pantallaY, gp.tamFinalCasilla, gp.tamFinalCasilla, null);
    	
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

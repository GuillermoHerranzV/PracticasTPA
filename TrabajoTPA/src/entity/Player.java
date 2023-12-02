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
import main.UtilityTool;

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
    
    public int tieneLlave = 0;
    public int monstruoIndex;

    public Player(PanelDeJuego gp, Controles key) {
    	
    	super(gp);
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
    	areaSolidaDefaultX = areaSolida.x;
    	areaSolidaDefaultY = areaSolida.y;
    	
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
    	
    	//Estado del jugador
    	maxhp  = 100;
    	hp = maxhp;
    	
    }
    
    public void getPlayerImage () {
    	
    	up1 = setup("boy_up_1");
    	up2 = setup("boy_up_2");
    	down1 = setup("boy_down_1");
    	down2 = setup("boy_down_2");
    	left1 = setup("boy_left_1");
    	left2 = setup("boy_left_2");
    	right1 = setup("boy_right_1");
    	right2 = setup("boy_right_2");

    	
    }
    
    public BufferedImage setup(String imageName) {
    	
    	UtilityTool uTool = new UtilityTool();
    	BufferedImage scaledImage = null;
    	
    	try {
    		
    		scaledImage = ImageIO.read (getClass().getResourceAsStream("/player/" + imageName + ".png"));
    		scaledImage = uTool.scaleImage(scaledImage, gp.tamFinalCasilla, gp.tamFinalCasilla);
    		
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	return scaledImage;
    }

    public void update () {
    	
    	if (key.arriba == true || key.abajo == true || key.izq == true || key.der == true || key.enterPressed == true) {
    		
    		if (key.arriba == true) {direction = "up";
    		}else if (key.abajo == true) {direction = "down";
    		}else if (key.izq == true) {direction = "left";
    		}else if (key.der == true) {direction = "right";}
    		
    		//Comprobar colision del jugador
    		colisionOn = false;
    		gp.cChecker.comprobarCasilla (this);
    		
    		//Comprobar colision con objetos
    		int indexObj = gp.cChecker.comprobarObjeto(this, true);
    		cogerObjeto (indexObj);
    		
    		//COMPROBAR COLISION DE NPC
    		int npcIndex = gp.cChecker.comprobarEntidad(this, gp.npc);
    		interactNPC(npcIndex);
    		
    		//COMPROBAR COLISION ENEMIGO
    		monstruoIndex = gp.cChecker.comprobarEntidad(this, gp.monstruos);
    		contactMonster (monstruoIndex);
    		
    		//Si no hay colision el jugador se puede mover
    		if (colisionOn == false && key.enterPressed == false) {
    			
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
        	
    		gp.key.enterPressed = false;
    		
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
    
    public void cogerObjeto (int index) {
    	
    	if (index != 999) {
    		
    		String nombreObj = gp.objetos[index].nombre;
    		
    		switch (nombreObj) {
    		
    		case "Llave":
    			gp.efectoSonido(1);
    			tieneLlave ++;
    			gp.objetos[index] = null;
    			gp.ui.mostrarMensaje("Llave obtenida");
    			break;
    			
    		case "Puerta":
    			if (tieneLlave > 0) {
    				gp.efectoSonido(3);
    				gp.objetos[index] = null;
    				tieneLlave --;
    				gp.ui.mostrarMensaje("Puerta abierta");
    			}else {
    				gp.ui.mostrarMensaje("No tienes mas llaves");
    			}
    			break;
    			
    		case "Botas":
    			gp.efectoSonido(2);
    			speed += 1;
    			gp.objetos[index] = null;
    			gp.ui.mostrarMensaje("Ahora eres mas rapido!!!");
    			break;
    		case "Cofre":
    			gp.ui.juegoTerminado = true;
    			gp.stopMusic();
    			gp.efectoSonido(4);
    			break;
    			
    		}
    		
    	}
    	
    }
    
    public void interactNPC(int i) {
		
		if(i != 999) {
			if (gp.key.enterPressed == true) {
				gp.gameState = gp.dialogState;
				gp.npc[i].speak();
			}
		}
		gp.key.enterPressed = false;
	}
    
    public void interactMonster (int i) {
    	
    	if(i != 999) {
			if (gp.key.enterPressed == true) {
				gp.gameState = gp.dialogState;
				gp.monstruos[i].speak();
			}
		}
		gp.key.enterPressed = false;
    	
    }
    
    public void contactMonster (int i) {
    
    	if (i != 999) {
    		
    		//hp -= 1;
    		gp.gameState = gp.combatState;
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
    	g2.drawImage(image, pantallaX, pantallaY, null);
    	
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
    
    /**
     * Funcion para indicar la subida de nivel
     */
    public void levelUp() {
        exp = ++exp;
        System.out.println("Congratulations! You have leveled up! Your new level is: " + this.getExp());
    }
    
}

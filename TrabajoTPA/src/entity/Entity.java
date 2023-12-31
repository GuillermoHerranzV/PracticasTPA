package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.PanelDeJuego;
import main.UtilityTool;

/**
 * Clase base para todas las entidades del juego
 */
public abstract class Entity {
	
	//ESTADISTICAS DEL  PERSONAJE
    public int maxhp;
    public int hp;
    public int mana = 100;
    public int maxmana;
    public int maxdmg;
    public int dmg;
    PanelDeJuego gp;
    //POSICIONES DEL PERSONAJE
    public int mundoX, mundoY;
    public int speed;
    //ACCIONES
    public int contadorAccion;
    public String dialogues [] = new String [20];
    int dialogIndex = 0;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    //AREA SOLIDA DE COLISION E IMAGENES
    public int areaSolidaDefaultX, areaSolidaDefaultY;
    public Rectangle areaSolida = new Rectangle(0, 0, 48, 48);
    public boolean colisionOn = false;
    
    public BufferedImage image;
	public String nombre;
	public boolean colision = false;
    
    //UTILIZADO PARA PODER GUARDAR LAS IMAGENES DEL JUEGO
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, estatico1, estatico2;
    public String direction = "down";
    
    /**
     * CONSTRUCTOR
     * @param gp
     */
    public Entity(PanelDeJuego gp) {
    	this.gp = gp;
    }
    
    /**
     * OBTENER EL MAXIMO DE VIDA
     * @return
     */
    public int getmaxHp() {
    	return maxhp;
    }
    
    //METODO PARA HABLAR QUE IMPLEMENTAN LAS CLASES QUE LO USAN
    public abstract void speak ();
    //METODO PARA REALIZAR UNA ACCION QUE IMPLEMENTAN LAS CLASES QUE LO USAN
    public abstract void setAction();
    
    /**
     * METODO UPDATE QUE COMPRUEBA LAS COLISIONES DE OBJETOS Y ENEMIGOS, ACTUALIZA LA POSICION SI SE PUEDE Y DIBUJA EL SPRITE SEGUN SEA NECESARIO
     */
    public void update() {
    	
    	setAction();
    	colisionOn = false;
    	gp.cChecker.comprobarCasilla(this);
    	gp.cChecker.comprobarObjeto(this, false);
    	gp.cChecker.comprobarEntidad(this, gp.npc);
    	gp.cChecker.comprobarEntidad(this, gp.monstruos);
    	gp.cChecker.comprobarJugador(this);
    	
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
    
    /**
     * METODO DRAW QUE DIBUJA TODO LO RELACIONADO CON LAS ENTIDADES
     * @param g2
     */
    public void draw(Graphics2D g2) {
    	
    	BufferedImage image = null;

    	
		int pantallaX = mundoX - gp.player.mundoX + gp.player.pantallaX;
		int pantallaY = mundoY - gp.player.mundoY + gp.player.pantallaY;
		
		if (mundoX + gp.tamFinalCasilla> gp.player.mundoX - gp.player.pantallaX && mundoX - gp.tamFinalCasilla < gp.player.mundoX + gp.player.pantallaX && mundoY + gp.tamFinalCasilla > gp.player.mundoY - gp.player.pantallaY && mundoY - gp.tamFinalCasilla < gp.player.mundoY + gp.player.pantallaY) {
			
			g2.drawImage(image, pantallaX, pantallaY, gp.tamFinalCasilla, gp.tamFinalCasilla, null);
				    	
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
	    	case "estatico":
	    		if (spriteNum == 1) {
	    			image = estatico1;
	    		}
	    		if (spriteNum == 2) {
	    			image = estatico2;
	    		}
	    		break;
	    	
	    	}
	    	
	    	//Dibuja el sprite
	    	g2.drawImage(image, pantallaX, pantallaY, null);
		}
    }
    
    /**
     * METODO SETUP QUE DEVUELVE UNA IMAGEN
     * UTILIZADO PARA ASIGNAR LAS IMAGENES A CADA ENTIDAD
     * @param imageName
     * @return scaledImage de tipo BufferedImage
     */
    public BufferedImage setup(String imageName) {
    	
    	UtilityTool uTool = new UtilityTool();
    	BufferedImage scaledImage = null;
    	
    	try {
    		
    		scaledImage = ImageIO.read (getClass().getResourceAsStream(imageName + ".png"));
    		scaledImage = uTool.scaleImage(scaledImage, gp.tamFinalCasilla, gp.tamFinalCasilla);
    		
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	return scaledImage;
    }
    
    /**
     * METODO SPECIALATTACK QUE DEFINE UN ATAQUE ESPECIAL
     */
    public void specialAttack () {
    	
		if (getHp () < getmaxHp()/2) {
			setHp(getHp() + maxhp/2);
		}else {
			setHp(maxhp);
		}
    	
	}
    
    public int getHp() {
        return hp;
    }
    
    public void setHp(int health) {
        this.hp = health;
    }
    
    public int getDmg() {
        return dmg;
    }
    
    public void setDmg(int damage) {
        this.dmg = damage;
    }
    
    public void setX(int X) {
        this.mundoX = X;
    }
    public void setY(int Y) {
        this.mundoY = Y;
    }
    public void setSpeed(int Speed) {
        this.speed = Speed;
    }
    
    /**
     * FUNCION PARA EL ATAQUE, IMPLEMENTADA EN LAS SUBCLASES
     * 
     */
    public void attack() {

    }
    
}


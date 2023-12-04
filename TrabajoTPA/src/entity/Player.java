package entity;
import java.awt.Color;
import java.awt.Font;
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
import main.Combate;

/**
 * Clase jugador que hereda de Entity y amplia funcionalidades como las de interactuar...
 */
public class Player extends Entity{
    //INSTANCIAS NECESARIAS DE OTRAS CLASES
    PanelDeJuego gp;
    Controles key;
    //CARACTERISTICAS DEL JUGADOR
    String name;
    public int enemigosDerrotados = 0;
    //POSICION DEL JUGADOR EN PANTALLA
    public final int pantallaX;
    public final int pantallaY;
    
    public int tieneLlave = 0;
    public int monstruoIndex;
    
    int dmg;

    
    /**
     * CONSTRUCTOR DE NUESTRO PERSONAJE
     * @param gp
     * @param key
     */
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
    	
       	setDefaultValues();
    }
    
    /**
     * VALORES DE ATRIBUTOS POR DEFECTO
     */
    public void setDefaultValues () {
    	
    	mundoX = gp.tamFinalCasilla * 23;
    	mundoY = gp.tamFinalCasilla * 21;
    	speed = 4;
    	direction = "down";
    	setmaxHp(100);
    	setHp(maxhp);
    	maxmana = 100;
    	setMana(maxmana);
    	maxdmg = 35;
    	setDmg(maxdmg);
    }
    
    /**
     * POSICION DEL PERSONAJE AL INICIAR
     */
    public void setDefaultPositions () {
    	mundoX = gp.tamFinalCasilla * 23;
    	mundoY = gp.tamFinalCasilla * 21;
    	direction = "down";
    }
    
    
    /**
     * COLOCA LAS IMAGENES DE NUESTROS PERSONAJES EN FUNCION DE CUAL SE ELIJA
     * @param i
     */
    public void getPlayerImage (int i) {
    	
    	if (i == 0) {
    		up1 = setup("sword_up_1");
        	up2 = setup("sword_up_2");
        	down1 = setup("sword_down_1");
        	down2 = setup("sword_down_2");
        	left1 = setup("sword_left_1");
        	left2 = setup("sword_left_2");
        	right1 = setup("sword_right_1");
        	right2 = setup("sword_right_2");
    	}else if (i == 1) {
    		up1 = setup("bat_up_1");
        	up2 = setup("bat_up_2");
        	down1 = setup("bat_down_1");
        	down2 = setup("bat_down_2");
        	left1 = setup("bat_left_1");
        	left2 = setup("bat_left_2");
        	right1 = setup("bat_right_1");
        	right2 = setup("bat_right_2");
    	}else if (i == 2) {
    		up1 = setup("ghostup");
        	up2 = setup("ghostup2");
        	down1 = setup("ghostdown");
        	down2 = setup("ghostdown2");
        	left1 = setup("ghostlft");
        	left2 = setup("ghostlft2");
        	right1 = setup("ghostrght");
        	right2 = setup("ghostrght2");
    	}
    	
    }
    
    /**
     * UTILIZADO POR EL METODO GETPLAYERIMAGE PARA COLOCAR LA IMAGEN
     */
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

    /**
     * ACTUALIZA EL PERSONAJE EN PANTALLA SEGUN SI PUEDE CAMBIAR SU ESTADO Y COMPRUEBA LAS COLISIONES
     */
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
    
    
    /**
     * METODO QUE NOS AYUDA A CONFIGURAR A LA HORA DE COGER LOS OBJETOS DEL MAPA
     * @param index
     */
    public void cogerObjeto (int index) {
    	
    	if (index != 999) {
    		
    		String nombreObj = gp.objetos[index].nombre;
    		
    		switch (nombreObj) {
    		
    		case "Llave":
    			gp.efectoSonido(1);
    			tieneLlave ++;
    			gp.objetos[index] = null;
    			System.out.println("Has obtenido una llave!");
    			gp.ui.mostrarMensaje("Llave obtenida");
    			break;
    			
    		case "Puerta":
    			if (tieneLlave > 0) {
    				gp.efectoSonido(3);
    				gp.objetos[index] = null;
    				tieneLlave --;
    				gp.ui.mostrarMensaje("Puerta abierta");
        			System.out.println("Has abierto una puerta!");
    			}else {
    				gp.ui.mostrarMensaje("No tienes mas llaves");
        			System.out.println("No tienes mas llaves!");
    			}
    			break;
    			
    		case "Botas":
    			gp.efectoSonido(2);
    			speed += 1;
    			setHp(maxhp);
    			gp.objetos[index] = null;
    			gp.ui.mostrarMensaje("Ahora eres mas rapido!!!");
    			System.out.println("Wow! Has aumentado tu velocidad!");
    			break;
    		case "Cofre":
    			gp.ui.juegoTerminado = true;
    			gp.stopMusic();
    			gp.efectoSonido(4);
    			gp.gameState = gp.victoriaState;
    			break;
    			
    		}
    		
    	}
    	
    }
    
    /**
     * METODO PARA DIALOGAR CON EL NPC DEL INICIO
     * @param i
     */
    public void interactNPC(int i) {
		
		if(i != 999) {
			if (gp.key.enterPressed == true) {
				gp.gameState = gp.dialogState;
				gp.npc[i].speak();
			}
		}
		gp.key.enterPressed = false;
	}
    
    /**
     * METODO PARA INICIAR COMBATE AL ENTRAR EN CONTACTO CON UN MONSTRUO
     * @param i
     */
    public void contactMonster (int i) {
    
    	if (i != 999) {
    		
    		//hp -= 1;
    		if (i >= 0 && i < 3) {
    			gp.gameState = gp.combatState;
    		}else if (i == 3 && enemigosDerrotados == 3) {
    			gp.gameState = gp.combatState;
    		}
    		
    	}
    	
    }
    
    /**
     * DIBUJA EL PERSONAJE
     */
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
     
    /**
     * METODO PARA EL ATAQUE PRINCIPAL DEL PERSONAJE
     */
    public void attack() {
    	int aux1 = Controles.getAux();
    	colocarDanios(aux1);
    	
        gp.monstruos[gp.player.monstruoIndex].setHp(gp.monstruos[gp.player.monstruoIndex].getHp() - dmg);
        System.out.println ("Has atacado al enemigo");
    }
    
    /**
     * METODO PARA UTILIZAR EL ITEM EN COMBATE (MAXIMO 3 USOS POR COMBATE)
     * @param usos
     */
    public void useItem(int usos) {
    	gp.monstruos[gp.player.monstruoIndex].setHp(gp.monstruos[gp.player.monstruoIndex].getHp() - 25);
    	setHp(getHp()+25);
        System.out.println("Has usado un objeto");
        if (usos > 0) {
        	usos--;
        }
    }
    
    /**
     * METODO PARA EL ATAQUE ESPECIAL DEL PERSONAJE DEPENDIENDO DE CUAL ELIJAMOS AL PRINCIPIO
     * AUX1 ES EL INDICADOR DE PERSONAJE
     */
    public void specialAttack () {
    	int aux1 = Controles.getAux();
    	colocarDanios(aux1);
    	colocarHPs(aux1);

    	if(aux1 == 0) {
        gp.monstruos[gp.player.monstruoIndex].setHp(gp.monstruos[gp.player.monstruoIndex].getHp() - (getDmg()*2));
        setMana(getMana() - 75);
    	System.out.println("Has usado el ataque especial de daño doble");
    	}
    	else if(aux1 == 1) {
            gp.player.setHp(maxhp);
            setMana(getMana() - 75);
        	System.out.println("Has usado el ataque especial de curarte al máximo");
        	}
    	
    	else if(aux1 == 2) {
            if(gp.player.getHp() < gp.player.maxhp*0.5) {
            gp.player.setHp(maxhp);
        	System.out.println("Has usado el ataque especial de curarte al máximo");
            }
            else if(gp.player.maxhp >= gp.player.maxhp*0.5) {  		
            gp.monstruos[gp.player.monstruoIndex].setHp(gp.monstruos[gp.player.monstruoIndex].getHp() - (getDmg()*2));
        	System.out.println("Has usado el ataque especial de daño doble");
            }
            setMana(getMana() - 75);
        	}
    }
    
    /**
     * METODO PARA COLOCAR EL DMG SEGUN EL PERSONAJE
     * @param aux1
     */
    public void colocarDanios(int aux1) {
    	if(aux1 == 0) {
    		setDmg(70);
    	}
    	else if (aux1 == 1) {
    		setDmg(35);
    	}
    	else if(aux1 == 2) {
    		setDmg(40);
    	}
    }
    
    /**
     * METODO PARA COLOCAR LA VIDA SEGUN EL PERSONAJE
     * @param aux1
     */
    public void colocarHPs(int aux1) {
    	if(aux1 == 0) {
    		maxhp = 200;
    	}
    	else if (aux1 == 1) {
    		maxhp = 400;
    	}
    	else if(aux1 == 2) {
    		maxhp = 300;
    	}
    }
    
    public void setHp(int h) {
    	hp = h;
    }
    
    public int getHp() {
    	return hp;
    }
    
    public void setDmg(int d) {
    	dmg = d;
    }
    
    public int getDmg() {
    	return dmg;
    }
    
    public String getName() {
    	return name;
    }
    
    public int getMana() {
    	return mana;
    }
    
    public void setMana(int a) {
    	mana = a;
    }
    
    public void setmaxHp(int a) {
    	maxhp = a;
    }

	@Override
	public void speak() {
		
	}

	@Override
	public void setAction() {
			
	}
    
}

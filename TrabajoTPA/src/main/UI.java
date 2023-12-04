 package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import object.OBJ_llave;

/**
 * CLASE UI PARA DIBUJAR TODOS LOS MENUS EN PANTALLA
 */
public class UI {

	PanelDeJuego gp;
	Graphics2D g2;
	Font arial_20;
	public boolean mensajeOn = false;
	public String mensaje = "";
	int contadorMensaje = 0;
	public boolean juegoTerminado = false;
	public String currentDialog = "";
	//LOS commandNum SON PARA LA OPCION SEGUN NAVEGAS POR UN MENU
	public int commandNum = 0;
	public int commandNumCombat = 0;
	public int commandNumDerrota = 0;
	public int titleScreenState = 0;
	
	public UI (PanelDeJuego gp) {
		
		this.gp = gp;
		arial_20 = new Font ("Arial", Font.PLAIN, 20);

		
	}
	
	public void mostrarMensaje (String texto) {
		
		mensaje = texto;
		mensajeOn = true;
		
	}
	
	/**
	 * METODO QUE COMPRUEBA QUE MENU DIBUJAR Y DIBUJA ALGUNAS COSAS EN ELLOS
	 * @param g2
	 */
	public void draw (Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_20);
		g2.setColor(Color.white);
		
		//Title state
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		//Play State
		if(gp.gameState == gp.playState) {
			g2.drawString("Vida: " + gp.player.hp + "/" + gp.player.maxhp, 50, 50);
			g2.drawString("Mana: " + gp.player.mana + "/" + gp.player.maxmana, 50, 70);
		}
		
		//Pause State
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		//Dialog State
		if (gp.gameState == gp.dialogState) {
			
			drawDialogScreen ();
			
		}
		
		if (gp.gameState == gp.combatState) {
			drawCombatScreen (gp.player.monstruoIndex);
			g2.drawString("Vida: " + gp.player.hp + "/" + gp.player.maxhp, 50, 50);	
			g2.drawString("Vida: " + gp.monstruos[gp.player.monstruoIndex].getHp() + "/" + gp.monstruos[gp.player.monstruoIndex].maxhp, 550, 50);	
			g2.drawString("Mana: " + gp.player.mana + "/" + gp.player.maxmana, 50, 70);
		}
		
		if(gp.gameState == gp.derrotaState) {
			drawDerrotaScreen();
		}
		
		if (gp.gameState == gp.victoriaState) {
			drawVictoriaScreen();
		}
		
	}
	
	/**
	 * METODO QUE DIBUJA LA PANTALLA DE VICTORIA
	 */
	public void drawVictoriaScreen() {
		g2.setColor(new Color(70, 120, 80));
		g2.fillRect(0, 0, gp.anchoVentana, gp.altoVentana);
		//TITULO
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		String text = "VICTORIA! ENHORABUENA";
		int x = getXforCenteredText(text);
		int y = gp.tamFinalCasilla * 2;
		
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,28F));
		
		text = "SALIR";
		x = getXforCenteredText(text);
		y = y + gp.tamFinalCasilla + 40;
		g2.drawString(text, x, y);
		if(commandNumDerrota == 0) {
			g2.drawString(">", x-gp.tamFinalCasilla, y);
		}
	}
	
	/**
	 * METODO QUE DIBUJA LA PANTALLA DE DERROTA
	 */
	public void drawDerrotaScreen() {
		g2.setColor(new Color(70, 120, 80));
		g2.fillRect(0, 0, gp.anchoVentana, gp.altoVentana);
		//TITULO
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "DERROTA";
		int x = getXforCenteredText(text);
		int y = gp.tamFinalCasilla * 2;
		
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,28F));
		
		text = "EMPEZAR DE NUEVO";
		x = getXforCenteredText(text);
		y = y + gp.tamFinalCasilla + 40;
		g2.drawString(text, x, y);
		if(commandNumDerrota == 0) {
			g2.drawString(">", x-gp.tamFinalCasilla, y);
		}
		
		text = "SALIR";
		x = getXforCenteredText(text);
		y = y + gp.tamFinalCasilla + 40;
		g2.drawString(text, x, y);
		if(commandNumDerrota == 1) {
			g2.drawString(">", x-gp.tamFinalCasilla, y);
		}

	}
	
	/**
	 * METODO QUE DIBUJA LA PANTALLA DE TITULO
	 */
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			
			g2.setColor(new Color(0, 0, 72));
			g2.fillRect(0, 0, gp.anchoVentana, gp.altoVentana);
			//TITULO
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
			String text = "Wilderness";
			int x = getXforCenteredText(text);
			int y = gp.tamFinalCasilla * 2;
			
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			//IMAGEN DEL PESONAJE		
			x = gp.anchoVentana / 2 - (gp.tamFinalCasilla*2)/2;
			y = y + gp.tamFinalCasilla;
			UtilityTool uTool = new UtilityTool();
	    	BufferedImage scaledImage = null;
	    	try {
	    		
	    		scaledImage = ImageIO.read (getClass().getResourceAsStream("/tiles/inicio.jpg"));
	    		scaledImage = uTool.scaleImage(scaledImage, gp.tamFinalCasilla, gp.tamFinalCasilla);
	    		
	    	} catch(IOException e) {
	    		e.printStackTrace();
	    	}
	    	BufferedImage imagen = scaledImage;
			g2.drawImage(imagen, x, y, gp.tamFinalCasilla*2, gp.tamFinalCasilla*2, null);
			
			//MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,28F));
			
			text = "NUEVA PARTIDA";
			x = getXforCenteredText(text);
			y = y + gp.tamFinalCasilla + 40;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tamFinalCasilla, y);
			}
			
			text = "CARGAR PARTIDA";
			x = getXforCenteredText(text);
			y = y + gp.tamFinalCasilla + 40;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tamFinalCasilla, y);
			}
			
			text = "SALIR";
			x = getXforCenteredText(text);
			y = y + gp.tamFinalCasilla + 40;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tamFinalCasilla, y);
			}
		}
		//SEGUNDA PANTALLA DE TITULO (SELECCION DE PERSONAJE)
		else if (titleScreenState == 1) {
			
			
			//SELECCION
			g2.setColor(Color.green);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "SELECCIONA TU PERSONAJE";
			int x = getXforCenteredText(text);
			int y = gp.tamFinalCasilla;		
			g2.drawString(text, x, y);
			
			text = "Espada magica";
			x = getXforCenteredText(text);
			y = y + 100 + gp.tamFinalCasilla;		
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tamFinalCasilla, y);
			}
			
			text = "Murcielago";
			x = getXforCenteredText(text);
			y = y + gp.tamFinalCasilla;		
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tamFinalCasilla, y);
			}
			
			text = "Fantasma";
			x = getXforCenteredText(text);
			y = y + gp.tamFinalCasilla;		
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tamFinalCasilla, y);
			}
			
			text = "Atras";
			x = getXforCenteredText(text);
			y = y + gp.tamFinalCasilla;		
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tamFinalCasilla, y);
			}
			
		}

	}
	
	/**
	 * METODO QUE DIBUJA LA PANTALLA DE PAUSA
	 */
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.altoVentana/2;
		
		g2.drawString(text, x, y);
		
	}
	
	/**
	 * METODO QUE DIBUJA LA PANTALLA DE COMBATE
	 * @param i
	 */
	public void drawCombatScreen (int i) {
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gp.anchoVentana, gp.altoVentana);
		String text = "";
		
		//IMAGEN DEL PESONAJE		
		int x = gp.anchoVentana / 6;
		int y = gp.altoVentana/2 - gp.altoVentana/4;
		g2.drawImage(gp.player.down1, x, y, gp.tamFinalCasilla*2, gp.tamFinalCasilla*2, null);
		
		//IMAGEN DEL ENEMIGO
		x = gp.anchoVentana/4 + gp.anchoVentana/2;
		y = gp.altoVentana/2 - gp.altoVentana/4;
		g2.drawImage(gp.monstruos[i].down1, x, y, gp.tamFinalCasilla*2, gp.tamFinalCasilla*2, null);
		
		//COSAS DEL COMBATE
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(20F));
		
		text = "ATACAR";
		x = gp.anchoVentana/6;
		y = gp.altoVentana/4 * 3;
		g2.drawString(text, x, y);
		if(commandNumCombat == 0) {
			g2.drawString(">", x-gp.tamFinalCasilla, y);
		}
		
		text = "USAR OBJETO";
		x = gp.anchoVentana/5 * 3;
		y = gp.altoVentana/4 * 3;
		g2.drawString(text, x, y);
		if(commandNumCombat == 1) {
			g2.drawString(">", x-gp.tamFinalCasilla, y);
		}
		
		text = "ATAQUE ESPECIAL";
		x = gp.anchoVentana/6;
		y = gp.altoVentana/4 * (3) + gp.altoVentana/20;
		g2.drawString(text, x, y);
		if(commandNumCombat == 2) {
			g2.drawString(">", x-gp.tamFinalCasilla, y);
		}
		
		text = "HUIR";
		x = gp.anchoVentana/5 * 3;
		y = gp.altoVentana/4 * 3 + gp.altoVentana/20;
		g2.drawString(text, x, y);
		if(commandNumCombat == 3) {
			g2.drawString(">", x-gp.tamFinalCasilla, y);
		}
		
	}
	
	/**
	 * METODO QUE DIBUJA LOS DIALOGOS
	 */
	public void drawDialogScreen () {
		
		//Ventana
		int x = gp.tamFinalCasilla *2;
		int y = gp.tamFinalCasilla /2;
		int width = gp.anchoVentana - (gp.tamFinalCasilla *4);
		int height = gp.tamFinalCasilla *5;
		
		drawSubWindow (x, y, width, height);
		
		x += gp.tamFinalCasilla;
		y += gp.tamFinalCasilla;
		
		for (String line : currentDialog.split("/n")) {
			g2.drawString(line, x, y);
			y += 25;
		}
		
	}
	
	/**
	 * METODO QUE DIBUJA EL RECUADRO DE DIALOGO
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawSubWindow (int x, int y, int width, int height) {
		
		//Los 3 primeros numeros son RGB y el cuarto es la opacidad
		Color c = new Color (0, 0, 0, 150);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color (255, 255, 255);
		g2.setColor(c);
		//Linea que rodea el recuadro que imprime g2
		g2.setStroke(new BasicStroke (5));
		g2.drawRoundRect(x +5, y +5, width -10, height -10, 25, 25);
		
	}
	
	/**
	 * METODO PARA CENTRAR TEXTO EN PANTALLA
	 * @param text
	 * @return
	 */
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.anchoVentana/2 - length/2;
		return x;
	}
	
}

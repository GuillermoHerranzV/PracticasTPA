package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_llave;

public class UI {

	PanelDeJuego gp;
	Font arial_20;
	BufferedImage keyImage;
	public boolean mensajeOn = false;
	public String mensaje = "";
	int contadorMensaje = 0;
	public boolean juegoTerminado = false;
	
	public UI (PanelDeJuego gp) {
		
		this.gp = gp;
		arial_20 = new Font ("Arial", Font.PLAIN, 20);
		OBJ_llave llave = new OBJ_llave ();
		keyImage = llave.image;
		
	}
	
	public void mostrarMensaje (String texto) {
		
		mensaje = texto;
		mensajeOn = true;
		
	}
	
	public void draw (Graphics2D g2) {
		
		if (juegoTerminado == true) {
			
			g2.setFont (g2.getFont().deriveFont(50F));
			g2.setColor(Color.yellow);
			
			String texto;
			int tamanioTexto;
			int x;
			int y;
			
			texto = "Felicidades!!!";
			
			tamanioTexto = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
			x = gp.anchoVentana/2 - tamanioTexto/2;
			y = gp.altoVentana/2 + 75;
			
			g2.drawString(texto, x, y);
			
			gp.gameThread = null;
			
		}else {
			
			g2.setFont(arial_20);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tamFinalCasilla/2, gp.tamFinalCasilla/2, gp.tamFinalCasilla, gp.tamFinalCasilla, null);
			g2.drawString("x " + gp.player.tieneLlave, 74, 50);
			
			//Mensajes
			if (mensajeOn == true) {
				
				g2.setFont (g2.getFont().deriveFont(30F));
				g2.drawString(mensaje, gp.tamFinalCasilla/2, gp.tamFinalCasilla*5);
				
				contadorMensaje ++;
				
				if (contadorMensaje > 60) {
					contadorMensaje = 0;
					mensajeOn = false;
				}
				
			}
			
		}
		
	}
	
}

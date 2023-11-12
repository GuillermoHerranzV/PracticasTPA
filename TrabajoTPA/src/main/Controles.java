package main;
import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

/**
 * Clase que nos permitira detectar los controles que utiliza el usuario para luego darles una funcion
 */
public class Controles implements KeyListener{
	
	public boolean arriba, abajo, izq, der;
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Detecta la tecla que se ha pulsado
	 * @param e es la tecla que se pulsa en ese momento
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			arriba = true;
		}
		
		if (code == KeyEvent.VK_S) {
			abajo = true;
		}

		if (code == KeyEvent.VK_A) {
			izq = true;
		}
		
		if (code == KeyEvent.VK_D) {
			der = true;
		}
		
	}

	/**
	 * Detecta cuando la tecla se ha soltado
	 * @param e es la tecla en cuestion
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			arriba = false;
		}
		
		if (code == KeyEvent.VK_S) {
			abajo = false;
		}

		if (code == KeyEvent.VK_A) {
			izq = false;
		}
		
		if (code == KeyEvent.VK_D) {
			der = false;
		}
		
	}

}

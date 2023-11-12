package main;
import javax.swing.JFrame;

/**
 * 
 * Clase que va a generar la ventana y donde se va a llamar al resto de funciones que ejecutaran el juego
 * 
 */

public class Main {
	public static void main (String args []) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana cuando se hace click en el boton de cerrar ("X")
		window.setResizable (false); //Evita que se cambie el tamanio de la ventana
		window.setTitle("Wilderness"); //Pone el nombre de la ventana donde corresponde
		
		PanelDeJuego panelDeJuego = new PanelDeJuego ();
		window.add(panelDeJuego);
		
		window.pack (); //Hace que la ventana se ajuste a sus componentes en este caso el panel de juego
		
		window.setLocationRelativeTo (null);
		window.setVisible(true);
		
		panelDeJuego.startGameThread();
		
	}
}

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/*
 * Con esta clase vamos a definir los tamanios de las cosas que se van a ver en pantalla
 * Hereda de la libreria JPanel
 */

public class PanelDeJuego extends JPanel{

	final int defaultSize = 16; //Una cuadricula de 16x16 pixeles
	final int escalado = 3; //Escala la resolucion del png para que no se vea tan pequenio
	
	final int tamFinalCasilla = defaultSize * escalado;
	//Las 2 variables siguientes definen cuantas cuadriculas se mostraran en cada fila y columna de la ventana (16:9 el aspect ratio promedio a dia de hoy)
	final int columnasPantalla = 16;
	final int filasPantalla = 9;
	
	final int anchoVentana = tamFinalCasilla * columnasPantalla; //768 pixeles de ancho (tamanio de la casilla por el numero de casillas = 48 * 16)
	final int altoVentana = tamFinalCasilla * filasPantalla; //432 de alto (tamanio de la casilla por el numero de casillas = 48 * 9)
	
	public PanelDeJuego () {
		
		this.setPreferredSize(new Dimension (anchoVentana, altoVentana)); //Aplica las dimensiones declaradas antes
		this.setBackground (Color.black); //Hace que el fondo sea negro
		this.setDoubleBuffered(true); //Genera un buffer fuera de pantalla donde se realizan todos los dibujados de la clase y mejora la velocidad de renderizado
		
	}
	
}

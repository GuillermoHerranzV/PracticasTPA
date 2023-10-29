import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * 
 * Con esta clase vamos a definir los tamanios de las cosas que se van a ver en pantalla
 * Hereda de la biblioteca JPanel
 * 
 */

public class PanelDeJuego extends JPanel implements Runnable{

	final int defaultSize = 16; //Una cuadricula de 16x16 pixeles
	final int escalado = 3; //Escala la resolucion del png para que no se vea tan pequenio
	
	final int tamFinalCasilla = defaultSize * escalado;
	//Las 2 variables siguientes definen cuantas cuadriculas se mostraran en cada fila y columna de la ventana (16:9 el aspect ratio promedio a dia de hoy)
	final int columnasPantalla = 16;
	final int filasPantalla = 9;
	
	final int anchoVentana = tamFinalCasilla * columnasPantalla; //768 pixeles de ancho (tamanio de la casilla por el numero de casillas = 48 * 16)
	final int altoVentana = tamFinalCasilla * filasPantalla; //432 de alto (tamanio de la casilla por el numero de casillas = 48 * 9)
	
	//Frames por segundo del juego
	int FPS = 60;
	
	Controles key = new Controles ();
	Thread gameThread;
	
	//Posiciones iniciales del jugador
	int jugadorX = 100;
	int jugadorY = 100;
	//Velocidad inicial del jugador
	int velocidadJugador = 4;
	
	/**
	 *  Inicializa los ajustes de lo que se va a ver en la ventana del juego y como se va a comportar esta
	 */
	public PanelDeJuego () {
		
		this.setPreferredSize(new Dimension (anchoVentana, altoVentana)); //Aplica las dimensiones declaradas antes
		this.setBackground (Color.black); //Hace que el fondo sea negro
		this.setDoubleBuffered(true); //Genera un buffer fuera de pantalla donde se realizan todos los dibujados de la clase y mejora la velocidad de renderizado
		this.addKeyListener(key); //reconoce la tecla pulsada en el panel
		this.setFocusable(true); //recibe el input cuando la ventana esta en primer plano
		
	}
	
	/**
	 * Inicia el thread para que el juego se ejecute de forma ciclica y constante sin parar
	 */
	public void startGameThread () {
		
		gameThread = new Thread (this);
		gameThread.start();
		
	}
	
	/**
	 * Ejecuta el thread para actualizar la informacion del juego como la posicion del jugador/enemigos/npcs
	 * Tiene un contador de FPS que tambien actua como limite
	 */
	@Override
	public void run() {
		
		double intervaloDibujado = 1000000000/FPS; //El intervalo que queremos que utilice para dibujar las cosas en pantalla, en este caso 0,1666 segundos (esta medido en nanosegundos)
		double delta = 0; //Variable que utilizaremos como comprobacion del intervalo
		long lastTime = System.nanoTime(); //Ultimo tiempo del ordenador en el que se actualizo el delta (nanosegundos)
		long currentTime; //Tiempo actual del ordenador (nanosegundos)
		long temporizador = 0;
		int drawCount = 0;
		
		//Mientras el thread sea distinto de null se ejecuta
		while (gameThread != null) {
			
			currentTime = System.nanoTime(); //Reevalua el currentTime
			
			delta += (currentTime - lastTime) / intervaloDibujado; //Actualiza el delta
			
			temporizador += (currentTime - lastTime);
			
			lastTime = currentTime; //Actualiza el lastTime con el currentTime ya que ya se ha evaluado y actualizado el delta
			
			//Si el delta es mayor o igual que 1 se actualiza la informacion de la pantalla y se resetea el delta restandole 1, ademas tambien se le suma 1 al contador de FPS (porque se ha mostrado 1 frame mas)
			if (delta >= 1) {
				//Update info
				update ();
				
				//Dibujar info
				repaint ();
				
				delta--;
				drawCount++;
			}
			
			//Mostramos los FPS y reseteamos el contador de FPS (drawTime) y el temporizador
			if (temporizador >= 1000000000) {
				System.out.println ("Fps: " + drawCount);
				drawCount = 0;
				temporizador = 0;
			}
			
		}
		
	}
	
	/**
	 * Actualiza la posicion del jugador en el escenario mediante la clase Controles que es la que captura el input por teclado y lo traduce a un booleano que indica cual se esta pulsando
	 */
	public void update () {
		
		if (key.arriba == true) {
			
			jugadorY -= velocidadJugador;
			
		}else if (key.abajo == true) {
			
			jugadorY += velocidadJugador;
			
		}else if (key.izq == true) {
			
			jugadorX -= velocidadJugador;
			
		}else if (key.der == true) {
			
			jugadorX += velocidadJugador;
			
		}
		
	}
	
	/**
	 * Se encarga de generar los graficos y de dibujarlos en pantalla
	 * @param g va a ser la variable de graficos, hay que ponerla para que funcione
	 */
	public void paintComponent (Graphics g) {
		
		super.paintComponent (g);
		
		// Amplia la funcionalidad de la clase Graphics
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(jugadorX, jugadorY, tamFinalCasilla, tamFinalCasilla);
		
		//Libera la memoria que se este usando
		g2.dispose();
		
	}
	
}

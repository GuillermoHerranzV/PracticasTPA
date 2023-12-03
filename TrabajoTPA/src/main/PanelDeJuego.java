package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

/**
 * 
 * Con esta clase vamos a definir los tamanios de las cosas que se van a ver en pantalla
 * Hereda de la biblioteca JPanel
 * 
 */

public class PanelDeJuego extends JPanel implements Runnable{

	final int defaultSize = 16; //Una cuadricula de 16x16 pixeles
	final int escalado = 3; //Escala la resolucion del png para que no se vea tan pequenio
	
	public final int tamFinalCasilla = defaultSize * escalado;
	//Las 2 variables siguientes definen cuantas cuadriculas se mostraran en cada fila y columna de la ventana (16:9 el aspect ratio promedio a dia de hoy)
	public final int columnasPantalla = 16;
	public final int filasPantalla = 9;
	
	public final int anchoVentana = tamFinalCasilla * columnasPantalla; //768 pixeles de ancho (tamanio de la casilla por el numero de casillas = 48 * 16)
	public final int altoVentana = tamFinalCasilla * filasPantalla; //432 de alto (tamanio de la casilla por el numero de casillas = 48 * 9)
	
	//Settings del mundo
	public final int maxColMundo = 50;
	public final int maxRowMundo = 50;
	
	//Frames por segundo del juego / FPS
	int FPS = 60;
	
	//Sistema
	TileManager tileM = new TileManager (this);
	public Controles key = new Controles (this);
	Sonido musica = new Sonido ();
	Sonido efectosSonido = new Sonido ();
	public Colisiones cChecker = new Colisiones (this);
	public AssetSetter aSetter = new AssetSetter (this);
	public Combate combate = new Combate (this); 
	
	//UI
	public UI ui = new UI (this);
	
	Thread gameThread;
	
	//Entidad y objetos
	public Player player = new Player (this, key);
	public Entity objetos [] = new Entity [10];
	public Entity npc[] = new Entity[10];
	public Entity monstruos [] = new Entity [20];
	ArrayList <Entity> listaEntidades = new ArrayList <> ();
	
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
	 * Estado del juego(modo pausa etc)
	 */
	
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogState = 3;
	public final int combatState = 4;
	public final int derrotaState = 5;
	public final int victoriaState = 6;
	
	
	/**
	 * Funcion que coloca los objetos en el mapa
	 */
	public void setupGame () {
		
		aSetter.setObj();
		aSetter.setNPC();
		aSetter.setMonstruo();
		
//		playMusic (0);
		
		gameState = titleState;
		
	}
	
	public void retry () {
		gameState = playState;
		player.setDefaultPositions();
		aSetter.setNPC();
		aSetter.setMonstruo();
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
	 * Lo hace llamando a la funcion update de la clase Player
	 */
	public void update () {
				
		if(gameState == playState) {
			//JUGADOR
			player.update();
			//NPC
			for(int i = 0; i < npc.length; i++) {
				
				if(npc[i] != null) {
					
					npc[i].update();
					
				}
			}
			
			for(int i = 0; i < monstruos.length; i++) {
				
				if(monstruos[i] != null) {
					
					monstruos[i].update();
					
				}
			}
		}
		if(gameState == pauseState) {
			// nada
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
		
		if(gameState == titleState) {
			
			ui.draw(g2);
			
		}
		
		else {
			
			//Tile o casilla
			tileM.draw(g2);
			
			//Agregar todas las entidades a la lista
			listaEntidades.add(player);
			
			for (int i = 0; i < npc.length; i++) {
				
				if (npc[i] != null) {
					listaEntidades.add(npc [i]);
				}
				
			}
			
			for (int i = 0; i < objetos.length; i++) {
				
				if (objetos [i] != null) {
					listaEntidades.add(objetos [i]);
				}
				
			}
			
			for (int i = 0; i < monstruos.length; i++) {
				
				if (monstruos [i] != null) {
					listaEntidades.add(monstruos [i]);
				}
				
			}
			
			//Ordenar la lista
			Collections.sort(listaEntidades, new Comparator <Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					
					int resultado = Integer.compare(e1.mundoY, e2.mundoY);
					
					return resultado;
				}
				
			});
			
			//Dibujar las entidades
			for (int i = 0; i < listaEntidades.size(); i++) {
				listaEntidades.get(i).draw(g2);
			}
			
			//Vaciar la lista
			listaEntidades.clear();
			
			//UI
			ui.draw(g2);
			
			//Libera la memoria que se este usando
			g2.dispose();
			
		}
	}
	
	public void playMusic (int i) {
		
		musica.setFile (i);
		musica.play ();
		musica.loop ();
		
	}
	
	public void stopMusic () {
		
		musica.stop();
		
	}
	
	public void efectoSonido (int i) {
		
		efectosSonido.setFile(i);
		efectosSonido.play();
		
	}
	
}

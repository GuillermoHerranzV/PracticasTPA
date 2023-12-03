package main;

/**
 * Interfaz Playable: define los métodos básicos que deben tener las clases que pueden ser reproducidas.
 */
public interface Playable {
	    void setFile(int i);
	    void play();
	    void loop();
	    void stop();
	}

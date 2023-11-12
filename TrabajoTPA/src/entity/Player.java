package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import main.Controles;
import main.PanelDeJuego;

/**
 * Clase jugador que hereda de Entity y amplia funcionalidades como las de interactuar...
 */
public class Player extends Entity{
    private double exp;
    private List<String> items;
    
    PanelDeJuego gp;
    Controles key;

    public Player(PanelDeJuego gp, Controles key) {
    	
    	this.gp = gp;
    	this.key = key;
    	
    	setDefaultValues ();
    	
        items = new ArrayList<String>();
        exp = 0.0;
    }
    
    public void setDefaultValues () {
    	
    	x = 100;
    	y = 100;
    	speed = 4;
    	
    }
    
    public void update () {
    	
    	if (key.arriba == true) {
			
			y -= speed;
			
		}else if (key.abajo == true) {
			
			y += speed;
			
		}else if (key.izq == true) {
			
			x -= speed;
			
		}else if (key.der == true) {
			
			x += speed;
			
		}
    	
    }
    
    public void draw (Graphics2D g2) {
    	
    	g2.setColor(Color.white);
		
		g2.fillRect(x, y, gp.tamFinalCasilla, gp.tamFinalCasilla);
    	
    }
    
    public double getExp() {
        return exp;
    }

    /**
     * Funcion para saber los objetos que lleva el jugador
     * @return retorna la lista de objetos
     */
    public List<String> getItems() {
        return items;
    }

    /**
     * Funcion para sumar objetos al inventario del jugador
     * @param item, es el item recogido
     */
    public void setItems(String item) {
        items.add(item);
    }
    
    public void interact() {
        
    }
    
    public void flee() {
        
    }
    
    public void useItem() {
        
    }
    
    public void gainXP() {
        
    }
    
    /**
     * Funcion para indicar la subida de nivel
     */
    public void levelUp() {
        exp = ++exp;
        System.out.println("Congratulations! You have leveled up! Your new level is: " + this.getExp());
    }
    
}

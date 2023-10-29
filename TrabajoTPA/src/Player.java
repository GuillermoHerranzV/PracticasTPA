import java.util.ArrayList;
import java.util.List;

/**
 * Clase jugador que hereda de Entity y amplia funcionalidades como las de interactuar...
 */
public class Player extends Entity{
    private double exp;
    private List<String> items;

    public Player() {
        items = new ArrayList<String>();
        exp = 0.0;
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

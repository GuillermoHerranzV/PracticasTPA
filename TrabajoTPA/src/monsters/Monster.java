package monsters;

import java.util.Random;

import entity.Entity;
import main.PanelDeJuego;

/**
 * Interfaz Monster que hereda de entity y especifica metodos que utilizaran otros monstruos para su creacion y para realizar ciertas acciones
 */
public interface Monster{
    
    public void getImagen ();
    
    public void setAction ();
    
    public void speak();
}


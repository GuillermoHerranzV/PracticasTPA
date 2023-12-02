package entity;

import main.PanelDeJuego;
import monsters.Monster;

/**
 * Clase Boss que hereda de Monster y amplia funcionalidades
 */
public class Boss extends Monster {
    private int healing;
    PanelDeJuego gp;
    
    public Boss(PanelDeJuego gp) {
    	super(gp);
        setHp(750);
        setDmg(75);
    }
    
    /**
     * Ataque especial del jefe
     */
    public void bossAttack() {
        
    }
}


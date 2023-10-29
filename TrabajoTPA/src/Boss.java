
/**
 * Clase Boss que hereda de Monster y amplia funcionalidades
 */
public class Boss extends Monster {
    private int healing;
    
    public Boss() {
        setHp(750);
        setDmg(75);
    }
    
    /**
     * Ataque especial del jefe
     */
    public void bossAttack() {
        
    }
}


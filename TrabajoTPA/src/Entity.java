
/**
 * Clase base para todas las entidades del juego
 */
public class Entity {
    private int hp;
    private int dmg;
    
    public int getHp() {
        return hp;
    }
    
    public void setHp(int health) {
        this.hp = health;
    }
    
        public int getDmg() {
        return dmg;
    }
    
    public void setDmg(int damage) {
        this.dmg = damage;
    }
    
    /**
     * Funcion que utilizaremos para implementar el ataque
     * @return Retorna un string como mensaje de confirmacion
     */
    public String attack() {
        return ("Entity has attacked and reduced enemy's health by " + this.getDmg());
    }
    
}


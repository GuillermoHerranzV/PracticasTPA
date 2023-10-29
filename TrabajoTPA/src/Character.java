
/**
 * Clase Character que hereda de Player ya que el jugador podra escoger entre distintos personajes
 */
public class Character extends Player {
    private int mana;
    
    public int getMana() {
        return mana;
    }
    
    public void setMana(int mana) {
        this.mana = mana;
    }
    
    public String characterAttack() {
        return ("Your character does: ");
    }
    
}


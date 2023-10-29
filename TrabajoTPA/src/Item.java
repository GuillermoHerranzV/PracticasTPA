import java.util.List;

/**
 * Clase Item para hacer los distintos objetos que el jugador podra usar
 */
public class Item {
	
	private int healing;
	private int dmg;
	
	public int getHealing () {
		return healing;
	}
	
	public int getDmg () {
		return dmg;
	}
	
	/**
	 * Indica si el item usado puede revivir o no
	 * @param items, item seleccionado por el jugador
	 * @return true/false dependiendo de si puede o no realizar la funcion
	 */
	public boolean canRevive(List<String> items) {
        if(items.get(items.size()-1) == "revive") {
        	return true;
        }else {
        	return false;
        }
        
    }
	
	/**
	 * Indica si el item usado puede curar o no
	 * @param items, item seleccionado por el jugador
	 * @return true/false dependiendo de si puede o no realizar la funcion
	 */
	public boolean itemHeal(List<String> items) {
        if(items.get(items.size()-1) == "healing") {
        	return true;
        }else {
        	return false;
        }
	}
	
	/**
	 * Indica si el item usado puede hacer danio o no
	 * @param items, item seleccionado por el jugador
	 * @return true/false dependiendo de si puede o no realizar la funcion
	 */
	public boolean itemDmg (List <String> items) {
		
		if (items.get(items.size()-1) == "damage") {
			return true;
		}else {
			return false;
		}
		
	}

}

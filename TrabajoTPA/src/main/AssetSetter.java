package main;

import object.OBJ_cofre;
import object.OBJ_llave;
import object.OBJ_puerta;

/**
 * Clase que coloca los assets u objetos en el mapa
 */
public class AssetSetter {

	PanelDeJuego gp;
	
	public AssetSetter (PanelDeJuego gp) {
		
		this.gp = gp;
		
	}
	
	/**
	 * Asigna los objetos a una posicion del array y les asigna una posicion en el mapa
	 */
	public void setObj () {
		
		gp.objetos [0] = new OBJ_llave ();
		gp.objetos [0].mundoX = 23 * gp.tamFinalCasilla;
		gp.objetos [0].mundoY = 7 * gp.tamFinalCasilla;
		
		gp.objetos [1] = new OBJ_llave ();
		gp.objetos [1].mundoX = 23 * gp.tamFinalCasilla;
		gp.objetos [1].mundoY = 40 * gp.tamFinalCasilla;
		
		gp.objetos [2] = new OBJ_llave ();
		gp.objetos [2].mundoX = 38 * gp.tamFinalCasilla;
		gp.objetos [2].mundoY = 8* gp.tamFinalCasilla;
		
		gp.objetos [3] = new OBJ_puerta ();
		gp.objetos [3].mundoX = 10 * gp.tamFinalCasilla;
		gp.objetos [3].mundoY = 11 * gp.tamFinalCasilla;
		
		gp.objetos [4] = new OBJ_puerta ();
		gp.objetos [4].mundoX = 8 * gp.tamFinalCasilla;
		gp.objetos [4].mundoY = 28 * gp.tamFinalCasilla;
		
		gp.objetos [5] = new OBJ_puerta ();
		gp.objetos [5].mundoX = 12 * gp.tamFinalCasilla;
		gp.objetos [5].mundoY = 22 * gp.tamFinalCasilla;
		
		gp.objetos [6] = new OBJ_cofre ();
		gp.objetos [6].mundoX = 10 * gp.tamFinalCasilla;
		gp.objetos [6].mundoY = 7 * gp.tamFinalCasilla;
		
	}
	
}

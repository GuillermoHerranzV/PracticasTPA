package main;

import entity.npc;
import monsters.Boss;
import monsters.Elemental;
import monsters.Goblin;
import monsters.Monster;
import monsters.Slime;
import object.OBJ_Potion;
import object.OBJ_cofre;
import object.OBJ_llave;
import object.OBJ_puerta;

/**
 * Clase que coloca los assets u objetos en el mapa
 */
public class AssetSetter {

	PanelDeJuego gp;
	
	//CONSTRUCTOR
	public AssetSetter (PanelDeJuego gp) {
		
		this.gp = gp;
		
	}
	
	/**
	 * Asigna los objetos a una posicion del array y les asigna una posicion en el mapa
	 */
	public void setObj () {
		
		gp.objetos [0] = new OBJ_llave (gp);
		gp.objetos [0].mundoX = 40 * gp.tamFinalCasilla;
		gp.objetos [0].mundoY = 46 * gp.tamFinalCasilla;
		
		gp.objetos [1] = new OBJ_llave (gp);
		gp.objetos [1].mundoX = 23 * gp.tamFinalCasilla;
		gp.objetos [1].mundoY = 40 * gp.tamFinalCasilla;
		
		gp.objetos [2] = new OBJ_llave (gp);
		gp.objetos [2].mundoX = 38 * gp.tamFinalCasilla;
		gp.objetos [2].mundoY = 10* gp.tamFinalCasilla;
		
		gp.objetos [3] = new OBJ_puerta (gp);
		gp.objetos [3].mundoX = 10 * gp.tamFinalCasilla;
		gp.objetos [3].mundoY = 6 * gp.tamFinalCasilla;
		
		gp.objetos [4] = new OBJ_puerta (gp);
		gp.objetos [4].mundoX = 39 * gp.tamFinalCasilla;
		gp.objetos [4].mundoY = 12 * gp.tamFinalCasilla;
		
		gp.objetos [5] = new OBJ_puerta (gp);
		gp.objetos [5].mundoX = 10 * gp.tamFinalCasilla;
		gp.objetos [5].mundoY = 11 * gp.tamFinalCasilla;
		
		gp.objetos [6] = new OBJ_cofre (gp);
		gp.objetos [6].mundoX = 10 * gp.tamFinalCasilla;
		gp.objetos [6].mundoY = 2 * gp.tamFinalCasilla;
		
		gp.objetos [7] = new OBJ_Potion (gp);
		gp.objetos [7].mundoX = 23 * gp.tamFinalCasilla;
		gp.objetos [7].mundoY = 8 * gp.tamFinalCasilla;
		
		
	}
	
	/**
	 * ASIGNA NPCs A UNA POSICION DE UN ARRAY Y LES ASIGNA UNA POSICION EN EL MAPA
	 */
	public void setNPC() {
		
		gp.npc[0] = npc.getInstance(gp);

		gp.npc[0].mundoX = gp.tamFinalCasilla*21;
		gp.npc[0].mundoY = gp.tamFinalCasilla*21;
		
	}
	
	/**
	 * ASIGNA MONSTRUOS A UNA POSICION DE UN ARRAY Y LES ASIGNA UNA POSICION EN EL MAPA
	 */
	public void setMonstruo () {
		
		gp.monstruos[0] = new Slime (gp);
		gp.monstruos[0].mundoX = gp.tamFinalCasilla*35;
		gp.monstruos[0].mundoY = gp.tamFinalCasilla*40;
		
		gp.monstruos[1] = new Goblin (gp);
		gp.monstruos[1].mundoX = gp.tamFinalCasilla*39;
		gp.monstruos[1].mundoY = gp.tamFinalCasilla*13;
		
		gp.monstruos[2] = new Elemental (gp);
		gp.monstruos[2].mundoX = gp.tamFinalCasilla*7;
		gp.monstruos[2].mundoY = gp.tamFinalCasilla*46;
		
		gp.monstruos[3] = new Boss (gp);
		gp.monstruos[3].mundoX = gp.tamFinalCasilla*10;
		gp.monstruos[3].mundoY = gp.tamFinalCasilla*7;
		
	}
	
}

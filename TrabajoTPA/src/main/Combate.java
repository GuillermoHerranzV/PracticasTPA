package main;

import entity.Entity;
import entity.Player;
import monsters.Monster;
import main.PanelDeJuego;
import main.UI;

public class Combate {
	
	PanelDeJuego gp;
	
	public Combate (PanelDeJuego gp) {
		
		this.gp = gp;
		
	}
	
	int turno = 0; //turno
	
	public void pelea () {
		if(gp.gameState == gp.combatState) {
			if(turno == 0) {
				//ATAQUE
				if(gp.ui.commandNumCombat == 0) {
					
					gp.player.attack();
					turno = 1;
				}
				//USAR OBJETO
				else if(gp.ui.commandNumCombat == 1) {
					
					gp.player.useItem();
					turno = 1;
				}
				//ATAQUE ESPECIAL
				else if(gp.ui.commandNumCombat == 2) {
					
					gp.player.specialAttack();
					turno = 1;
					
				}
				//HUIR
				else if(gp.ui.commandNumCombat == 3) {
					gp.gameState = gp.playState;
				}
			}
			if(turno == 1) {
				
				System.out.println("El monstruo te ha hecho danio");
				gp.player.setHp(-10);
				turno = 0;
			}
			
			if (gp.monstruos[gp.player.monstruoIndex].getHp() <= 0) {
				gp.gameState = gp.playState;
				gp.player.enemigosDerrotados ++;
			}else if (gp.player.getHp() <= 0) {
				//Pantalla de derrota
				gp.gameState = gp.playState;
			}
			
		}
	}

}

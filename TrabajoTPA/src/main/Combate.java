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
			if(gp.player.getHp() > 0 &&  gp.monstruos[gp.player.monstruoIndex].getHp() > 0) {
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
					
					if(gp.player.getMana() >= 75) {
					turno = 1;
					gp.player.specialAttack();
					}
					else if(gp.player.getMana() < 75) {
						System.out.println("No tienes mana suficiente!");
						turno = 0;
					}
					
				}
				//HUIR
				else if(gp.ui.commandNumCombat == 3) {
					gp.retry();
					
				}
			}
			if(turno == 1) {
				
				if(gp.monstruos[gp.player.monstruoIndex].getHp() > 0) {
				System.out.println("El monstruo te ha hecho dañoo");
				
				gp.player.setHp(gp.player.getHp() - 50);
				
				//RECUPERAR MANA
				if(gp.player.getMana() <= 75) {
				gp.player.setMana(gp.player.getMana() + 25);
				}
				else if(gp.player.getMana() > 75) {
					gp.player.setMana(gp.player.maxmana);
				}
				turno = 0;
			}
				else if (gp.monstruos[gp.player.monstruoIndex].getHp() <= 0) {
					gp.gameState = gp.playState;
				}
			}
			}
		}
		
		if(gp.monstruos[gp.player.monstruoIndex].getHp() < 0) {
			acabarCombate();
			gp.player.enemigosDerrotados++;
			}
		
		else if(gp.player.getHp() <= 0) {
			gp.gameState = gp.derrotaState;
			}
		
}
	
	public void acabarCombate() {
		gp.monstruos[gp.player.monstruoIndex] = null;
		gp.gameState = gp.playState;
		System.out.println("Has derrotado al enemigo! Felicidades! Continua con la busqueda de objetos");
		gp.player.setMana(gp.player.maxmana);
		//gp.player.setHp(gp.player.maxhp);

	}
	
}

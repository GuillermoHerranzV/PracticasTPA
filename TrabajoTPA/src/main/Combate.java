package main;

import java.util.Random;

import entity.Entity;
import entity.Player;
import monsters.Monster;
import main.PanelDeJuego;
import main.UI;

/**
 * CLASE EMPLEADA PARA EL COMBATE
 */
public class Combate {
	
	PanelDeJuego gp;
	//CONSTRUCTOR
	public Combate (PanelDeJuego gp) {
		
		this.gp = gp;
		
	}
	
	int turno = 0; //TURNO
	int usosItem = 3; //VECES POR COMBATE QUE PUEDES USAR EL ITEM
	/**
	 * METODO PRINCIPAL
	 */
	public void pelea () {
		
		if(gp.gameState == gp.combatState) {
			//COMPROBACION DE QUE TANTO EL MONSTRUO COMO EL JUGADOR ESTEN VIVOS
			if(gp.player.getHp() > 0 &&  gp.monstruos[gp.player.monstruoIndex].getHp() > 0) {
			if(turno == 0) {
				//ATAQUE
				if(gp.ui.commandNumCombat == 0) {
					gp.player.attack();
					turno = 1;
				}
				//USAR OBJETO
				else if(gp.ui.commandNumCombat == 1) {
					gp.player.useItem(usosItem);
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
				//TURNO DEL ENEMIGO
				if(gp.monstruos[gp.player.monstruoIndex].getHp() > 0) {
					//SI NO ES EL BOSS ATACA NORMAL
					if (gp.monstruos[gp.player.monstruoIndex].nombre != "Demonio") {
						System.out.println("El monstruo te ha hecho daño");
						
						gp.player.setHp(gp.player.getHp() - gp.monstruos[gp.player.monstruoIndex].getDmg());
					}else {
						//SI ES EL BOSS TIENE UNA PROBABILIDAD ALEATORIA DE CURARSE PARTE DE LA VIDA
						Random random = new Random();
				    	int i = random.nextInt(100)+1;
				    	if (i <= 5) {
				    		System.out.println("El demonio ha utilizado su habilidad especial!!!");
				    		gp.monstruos[gp.player.monstruoIndex].specialAttack();
				    	}else {
				    		System.out.println("El demonio te ha hecho daño");
							
							gp.player.setHp(gp.player.getHp() - gp.monstruos[gp.player.monstruoIndex].getDmg());
				    	}
					}
				
				//RECUPERAR MANA
					if(gp.player.getMana() <= 75) {
						gp.player.setMana(gp.player.getMana() + 25);
					}
					else if(gp.player.getMana() > 75) {
						gp.player.setMana(gp.player.maxmana);
					}
					turno = 0;
				} else if (gp.monstruos[gp.player.monstruoIndex].getHp() <= 0) {
				gp.gameState = gp.playState;
				}
			}
			}
		}
		
		if(gp.monstruos[gp.player.monstruoIndex].getHp() < 0) {
			acabarCombate();
			}
		else if(gp.monstruos[gp.player.monstruoIndex].nombre == "Elemental") {
			if(gp.monstruos[gp.player.monstruoIndex].getHp() <= 0) {
				acabarCombate();
			}
			else if(gp.player.getHp() <= 0) {
					gp.gameState = gp.derrotaState;
					}
			}
		
		else if(gp.player.getHp() <= 0) {
			gp.gameState = gp.derrotaState;
			}
		

		
}
	/**
	 * METODO PARA RESETEAR EL COMBATE Y ACTUALIZAR LO QUE SE NECESITE AL DERROTAR AL ENEMIGO
	 */
	public void acabarCombate() {
		gp.monstruos[gp.player.monstruoIndex] = null;
		gp.player.enemigosDerrotados++;
		gp.gameState = gp.playState;
		System.out.println("Has derrotado al enemigo! Felicidades! Continua con la busqueda de objetos");
		gp.player.setMana(gp.player.maxmana);
		usosItem = 3;
		turno = 0;
		//gp.player.setHp(gp.player.maxhp);

	}
	
}


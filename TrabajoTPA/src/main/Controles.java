package main;
import java.awt.event.KeyEvent;
import monsters.Monster;
import entity.Player;

import java.awt.event.KeyListener;

/**
 * Clase que nos permitira detectar los controles que utiliza el usuario para luego darles una funcion
 */
public class Controles implements KeyListener{
	
	PanelDeJuego gp;
	boolean w;
	public static int aux = 5;
		
	public boolean arriba, abajo, izq, der, enterPressed;
	
	public Controles(PanelDeJuego gp) {
		
		this.gp = gp;
	}
	
	public static int getAux() {
		return aux;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Detecta la tecla que se ha pulsado
	 * @param e es la tecla que se pulsa en ese momento
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		//Title state
		if(gp.gameState == gp.titleState) {
			if(gp.ui.titleScreenState == 0) {						
				if (code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = 2;
					}
				}
						
				if (code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if(gp.ui.commandNum > 2) {
						gp.ui.commandNum = 0;
					}
				}
						
				if (code == KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum == 0) {
						gp.ui.titleScreenState = 1;
					}
					if(gp.ui.commandNum == 1) {
						//mas tarde añadimos
					}
					if(gp.ui.commandNum == 2) {
						System.exit(0);
					}
				}
						
			}
			else if(gp.ui.titleScreenState == 1) {
				if (code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = 3;
					}
				}
						
				if (code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if(gp.ui.commandNum > 3) {
						gp.ui.commandNum = 0;
					}
				}
						
				if (code == KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum == 0) {
						System.out.println("Ahora eres una espada magica! Mucha suerte con tus enfrentamientos y la busqueda de objetos. Tu habilidad especial hará el doble de daño");
						gp.playMusic(0);
						gp.player.setHp(200);
						gp.player.setmaxHp(200);
						gp.player.setDmg(70);
						gp.player.nombre = "Espada magica";
						aux = 0;
						gp.player.getPlayerImage(gp.ui.commandNum);
						gp.gameState = gp.playState;
					}
					else if(gp.ui.commandNum == 1) {
						System.out.println("Ahora eres un murcielago! Mucha suerte con tus enfrentamientos y la busqueda de objetos. Tu habilidad especial te cura al máximo.");
						gp.playMusic(0);
						gp.player.setHp(400);
						gp.player.setmaxHp(400);
						gp.player.setDmg(35);
						gp.player.nombre = "Tanque";
						aux = 1;
						gp.player.getPlayerImage(gp.ui.commandNum);
						gp.gameState = gp.playState;
					}
					else if(gp.ui.commandNum == 2) {
						System.out.println("Ahora eres un fantasma! Mucha suerte con tus enfrentamientos y la busqueda de objetos. Tu habilidad especial te curara al maximo si tu vida esta en menos de un 25% si no, hará el doble de daño");
						gp.playMusic(0);
						gp.player.setHp(300);
						gp.player.setmaxHp(300);
						gp.player.setDmg(40);
						gp.player.nombre = "Fantasma";
						aux = 2;
						gp.player.getPlayerImage(gp.ui.commandNum);
						gp.gameState = gp.playState;
						
					}
					else if(gp.ui.commandNum == 3) {
						gp.ui.titleScreenState = 0;
					}
				}
			}
		}
				
		//COMBAT STATE
		if (gp.gameState == gp.combatState) {
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNumCombat--;
				if(gp.ui.commandNumCombat < 0) {
					gp.ui.commandNumCombat = 3;
				}
			}
					
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNumCombat++;
				if(gp.ui.commandNumCombat > 3) {
					gp.ui.commandNumCombat = 0;
				}
			}
					
			if (code == KeyEvent.VK_ENTER && gp.combate.turno == 0) {
				if(gp.ui.commandNumCombat == 0) {
					gp.combate.pelea();
					
				}else if(gp.ui.commandNumCombat == 1) {
					gp.combate.pelea();
					
				}else if(gp.ui.commandNumCombat == 2) {
					gp.combate.pelea();
					
				}else if (gp.ui.commandNumCombat == 3) {
					gp.monstruos[gp.player.monstruoIndex] = null;
					gp.aSetter.setMonstruo();
					gp.gameState = gp.playState;
					
				}
			}
					
		}
		
		//DERROTA STATE
		if(gp.gameState == gp.derrotaState) {
			gp.stopMusic();
			gp.ui.drawDerrotaScreen();
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNumDerrota--;
				if(gp.ui.commandNumDerrota < 0) {
					gp.ui.commandNumDerrota = 1;
				}
			}
					
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNumDerrota++;
				if(gp.ui.commandNumDerrota > 1) {
					gp.ui.commandNumDerrota = 0;
				}
			}
					
			if (code == KeyEvent.VK_G && gp.ui.commandNumDerrota == 0) {
				
				gp.gameState = gp.titleState;
				gp.ui.titleScreenState = 0;
				gp.monstruos[gp.player.monstruoIndex].setHp(gp.monstruos[gp.player.monstruoIndex].getmaxHp());
				
				if (gp.gameState == gp.combatState) {
					gp.gameState = gp.playState;
				}
								
			}else if (code == KeyEvent.VK_G && gp.ui.commandNumDerrota == 1) {
				System.exit(0);
			}
		}
		
		//VICTORIA STATE
		if(gp.gameState == gp.victoriaState) {
			gp.ui.drawVictoriaScreen();
			if (code == KeyEvent.VK_ENTER) {
				System.exit(0);
			}
		}
		
		//Play State
		if (gp.gameState == gp.playState) {

			if (code == KeyEvent.VK_W) {
				arriba = true;
			}
			
			if (code == KeyEvent.VK_S) {
				abajo = true;
			}

			if (code == KeyEvent.VK_A) {
				izq = true;
			}
			
			if (code == KeyEvent.VK_D) {
				der = true;
			}
			
			if (code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
			}
			if (code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			
		}
		
		//Pause State
		else if (gp.gameState == gp.pauseState) {
		
			if (code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}
			
		}
		
		//Dialog State
		else if (gp.gameState == gp.dialogState) {
		
			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
	}

	/**
	 * Detecta cuando la tecla se ha soltado
	 * @param e es la tecla en cuestion
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			arriba = false;
		}
		
		if (code == KeyEvent.VK_S) {
			abajo = false;
		}

		if (code == KeyEvent.VK_A) {
			izq = false;
		}
		
		if (code == KeyEvent.VK_D) {
			der = false;
		}
		
	}

}
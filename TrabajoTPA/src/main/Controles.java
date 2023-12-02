package main;
import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

/**
 * Clase que nos permitira detectar los controles que utiliza el usuario para luego darles una funcion
 */
public class Controles implements KeyListener{
	
	PanelDeJuego gp;
		
	public boolean arriba, abajo, izq, der, enterPressed;
	
	public Controles(PanelDeJuego gp) {
		
		this.gp = gp;
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
						System.out.println("Se un caballero");
						gp.playMusic(0);
						gp.gameState = gp.playState;
					}
					else if(gp.ui.commandNum == 1) {
						System.out.println("Se un tanque");
						gp.playMusic(0);
						gp.gameState = gp.playState;
					}
					else if(gp.ui.commandNum == 2) {
						System.out.println("Se un caballero con misterio");
						gp.playMusic(0);
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
			
			if( == 0) {						
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
				
			} else if(gp.ui.titleScreenState == 0) {						
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
				
			} else if(gp.ui.titleScreenState == 0) {						
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
				
			} else if(gp.ui.titleScreenState == 0) {						
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
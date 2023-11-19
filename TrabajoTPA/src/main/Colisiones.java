package main;

import entity.Entity;

/**
 * Clase que detecta si el jugador esta colisionando con un objeto que no puede atravesar
 */
public class Colisiones {
	
	PanelDeJuego gp;
	
	public Colisiones (PanelDeJuego gp) {
		
		this.gp = gp;
		
	}
	
	/**
	 * Funcion que recibe una entidad por parametro de entrada y en funcion de hacia donde se mueve la entidad (en este caso el jugador) comprueba las esquinas de su hitbox para decidir si la casilla hacia donde quiere moverse se puede atravesar o no
	 * @param entity
	 */
	public void comprobarCasilla (Entity entity) {
		
		//Saca donde esta la colision del personaje en cada uno de sus lados
		int entidadIzq = entity.mundoX + entity.areaSolida.x;
		int entidadDer = entity.mundoX + entity.areaSolida.x + entity.areaSolida.width;
		int entidadArr = entity.mundoY + entity.areaSolida.y;
		int entidadAbajo = entity.mundoY + entity.areaSolida.y + entity.areaSolida.height;
		
		int entidadIzqCol = entidadIzq/gp.tamFinalCasilla;
		int entidadDerCol = entidadDer/gp.tamFinalCasilla;
		int entidadArrRow = entidadArr/gp.tamFinalCasilla;
		int entidadAbajoRow = entidadAbajo/gp.tamFinalCasilla;
		
		int tileNum1, tileNum2;
		
		//Switch para cada caso de direccion en que colisiona
		switch (entity.direction) {
		
		case "up":
			
			entidadArrRow = (entidadArr - entity.speed)/gp.tamFinalCasilla;
			tileNum1 = gp.tileM.mapTileNum [entidadIzqCol] [entidadArrRow];
			tileNum2 = gp.tileM.mapTileNum [entidadDerCol] [entidadArrRow];
			if (gp.tileM.tile [tileNum1].collision == true || gp.tileM.tile [tileNum2].collision == true) {
				entity.colisionOn = true;
			}
			break;
		case "down":
			entidadAbajoRow = (entidadAbajo + entity.speed)/gp.tamFinalCasilla;
			tileNum1 = gp.tileM.mapTileNum [entidadIzqCol] [entidadAbajoRow];
			tileNum2 = gp.tileM.mapTileNum [entidadDerCol] [entidadAbajoRow];
			if (gp.tileM.tile [tileNum1].collision == true || gp.tileM.tile [tileNum2].collision == true) {
				entity.colisionOn = true;
			}
			break;
		case "left":
			entidadIzqCol = (entidadIzq - entity.speed)/gp.tamFinalCasilla;
			tileNum1 = gp.tileM.mapTileNum [entidadIzqCol] [entidadArrRow];
			tileNum2 = gp.tileM.mapTileNum [entidadIzqCol] [entidadAbajoRow];
			if (gp.tileM.tile [tileNum1].collision == true || gp.tileM.tile [tileNum2].collision == true) {
				entity.colisionOn = true;
			}
			break;
		case "right":
			entidadDerCol = (entidadDer + entity.speed)/gp.tamFinalCasilla;
			tileNum1 = gp.tileM.mapTileNum [entidadDerCol] [entidadAbajoRow];
			tileNum2 = gp.tileM.mapTileNum [entidadDerCol] [entidadArrRow];
			if (gp.tileM.tile [tileNum1].collision == true || gp.tileM.tile [tileNum2].collision == true) {
				entity.colisionOn = true;
			}
			break;
		
		}
		
	}

}

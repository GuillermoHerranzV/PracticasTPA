package main;

import entity.Entity;

public class Colisiones {
	
	PanelDeJuego gp;
	
	public Colisiones (PanelDeJuego gp) {
		
		this.gp = gp;
		
	}
	
	public void comprobarCasilla (Entity entity) {
		
		int entidadIzq = entity.mundoX + entity.areaSolida.x;
		int entidadDer = entity.mundoX + entity.areaSolida.x + entity.areaSolida.width;
		int entidadArr = entity.mundoY + entity.areaSolida.y;
		int entidadAbajo = entity.mundoY + entity.areaSolida.y + entity.areaSolida.height;
		
		int entidadIzqCol = entidadIzq/gp.tamFinalCasilla;
		int entidadDerCol = entidadDer/gp.tamFinalCasilla;
		int entidadArrRow = entidadArr/gp.tamFinalCasilla;
		int entidadAbajoRow = entidadAbajo/gp.tamFinalCasilla;
		
		int tileNum1, tileNum2;
		
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

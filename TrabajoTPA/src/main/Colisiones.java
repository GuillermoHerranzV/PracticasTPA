package main;

import entity.Entity;

/**
 * Clase que detecta si el jugador esta colisionando con un objeto que no puede atravesar
 */
public class Colisiones {
	
	PanelDeJuego gp;
	//CONSTRUCTOR
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
	
	/**
	 * METODO PARA COMPROBAR LAS COLISIONES CON UNA ENTIDAD
	 * @param entidad
	 * @param target
	 * @return index
	 */
	public int comprobarEntidad(Entity entidad, Entity[] target) {
		
		int index = 999;
		
		for (int i = 0; i < target.length; i++) {
			
			if (target[i] != null) {
				
				//Conseguir la posicion del area solida de la entidad
				entidad.areaSolida.x = entidad.mundoX + entidad.areaSolida.x;
				entidad.areaSolida.y = entidad.mundoY + entidad.areaSolida.y;
				
				//Conseguir la posicion del area solida del objeto
				target[i].areaSolida.x = target[i].mundoX + target[i].areaSolida.x;
				target[i].areaSolida.y = target[i].mundoY + target[i].areaSolida.y;
				
				switch (entidad.direction) {
				case "up": entidad.areaSolida.y -= entidad.speed; break;
				case "down": entidad.areaSolida.y += entidad.speed; break;
				case "left": entidad.areaSolida.x -= entidad.speed; break;
				case "right": entidad.areaSolida.x += entidad.speed; break;
				}
				
				if (entidad.areaSolida.intersects(target[i].areaSolida)) {
					
					if (target[i] != entidad) {
						
						entidad.colisionOn = true;
						index = i;
						
					}
					
				}
				
				entidad.areaSolida.x = entidad.areaSolidaDefaultX;
				entidad.areaSolida.y = entidad.areaSolidaDefaultY;
				target[i].areaSolida.x = target[i].areaSolidaDefaultX;
				target[i].areaSolida.y = target[i].areaSolidaDefaultY;
				
			}
			
		}
		
		return index;
	}
	
	/**
	 * METODO PARA COMPROBAR LAS COLISIONES CON EL JUGADOR HACIA CADA DIRECCION
	 * @param entidad
	 */
	public void comprobarJugador(Entity entidad) {
		
		//Conseguir la posicion del area solida de la entidad
		entidad.areaSolida.x = entidad.mundoX + entidad.areaSolida.x;
		entidad.areaSolida.y = entidad.mundoY + entidad.areaSolida.y;
		
		//Conseguir la posicion del area solida del objeto
		gp.player.areaSolida.x = gp.player.mundoX + gp.player.areaSolida.x;
		gp.player.areaSolida.y = gp.player.mundoY + gp.player.areaSolida.y;
		
		switch (entidad.direction) {
		
		case "up":
			entidad.areaSolida.y -= entidad.speed;
			
			if (entidad.areaSolida.intersects(gp.player.areaSolida)) {
				
				entidad.colisionOn = true;
				
				
			}
			break;
		case "down":
			entidad.areaSolida.y += entidad.speed;
			
			if (entidad.areaSolida.intersects(gp.player.areaSolida)) {
				
				entidad.colisionOn = true;
				
				
			}
			break;
		case "left":
			entidad.areaSolida.x -= entidad.speed;
			
			if (entidad.areaSolida.intersects(gp.player.areaSolida)) {
				
				entidad.colisionOn = true;
				
				
			}
			break;
		case "right":
			entidad.areaSolida.x += entidad.speed;
			
			if (entidad.areaSolida.intersects(gp.player.areaSolida)) {
				
				entidad.colisionOn = true;
								
			}
			break;
		
		}
		
		entidad.areaSolida.x = entidad.areaSolidaDefaultX;
		entidad.areaSolida.y = entidad.areaSolidaDefaultY;
		gp.player.areaSolida.x = gp.player.areaSolidaDefaultX;
		gp.player.areaSolida.y = gp.player.areaSolidaDefaultY;
		
	}
	
	/**
	 * METODO QUE COMPRUEBA SI COLISIONAS CON UN OBJETO PARA PODER COGERLO
	 * @param entidad
	 * @param jugador
	 * @return
	 */
	public int comprobarObjeto (Entity entidad, boolean jugador) {
		
		int index = 999;
		
		for (int i = 0; i < gp.objetos.length; i++) {
			
			if (gp.objetos[i] != null) {
				
				//Conseguir la posicion del area solida de la entidad
				entidad.areaSolida.x = entidad.mundoX + entidad.areaSolida.x;
				entidad.areaSolida.y = entidad.mundoY + entidad.areaSolida.y;
				
				//Conseguir la posicion del area solida del objeto
				gp.objetos[i].areaSolida.x = gp.objetos[i].mundoX + gp.objetos[i].areaSolida.x;
				gp.objetos[i].areaSolida.y = gp.objetos[i].mundoY + gp.objetos[i].areaSolida.y;
				
				switch (entidad.direction) {
				
				case "up": entidad.areaSolida.y -= entidad.speed; break;
				case "down": entidad.areaSolida.y += entidad.speed; break;
				case "left": entidad.areaSolida.x -= entidad.speed; break;
				case "right": entidad.areaSolida.x += entidad.speed; break;
				}
				
				if (entidad.areaSolida.intersects(gp.objetos[i].areaSolida)) {
					
					if (gp.objetos[i].colision == true) {entidad.colisionOn = true;}
					
					if (jugador == true) {index = i;}
					
				}
				
				entidad.areaSolida.x = entidad.areaSolidaDefaultX;
				entidad.areaSolida.y = entidad.areaSolidaDefaultY;
				gp.objetos[i].areaSolida.x = gp.objetos[i].areaSolidaDefaultX;
				gp.objetos[i].areaSolida.y = gp.objetos[i].areaSolidaDefaultY;
				
			}
			
		}
		
		return index;
	}
	
}

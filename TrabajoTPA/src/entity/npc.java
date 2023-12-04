package entity;

import java.util.Random;

import main.PanelDeJuego;

/**
 * CLASE NPC QUE HEREDA DE ENTITY
 */
public class npc extends Entity {
	
	/**
	 * PATRON DE DISEÑO: SINGLETON
	 * La clase tiene un constructor privado y un método estático getInstance que devuelve la única instancia de la clase npc.
	 */
    
	private static npc instance;

    
    /**
     * CONSTRUCTOR PRIVADO
     * @param gp
     */
    private npc(PanelDeJuego gp) {
    	
        super(gp);
        direction = "down";
        speed = 1;
        getImagen();
        setDialog();
    }

	/**
	 * METODO ESTATICO
	 * @param gp
	 * @return instance
	 */
    public static npc getInstance(PanelDeJuego gp) {
        if (instance == null) {
            instance = new npc(gp);
        }
        return instance;
    }
	
    /**
     * METODO GETIMAGEN QUE RECOGE LAS IMAGENES DEL NPC
     */
    public void getImagen () {
    	
    	up1 = setup("/npc/oldman_up_1");
    	up2 = setup("/npc/oldman_up_2");
    	down1 = setup("/npc/oldman_down_1");
    	down2 = setup("/npc/oldman_down_2");
    	left1 = setup("/npc/oldman_left_1");
    	left2 = setup("/npc/oldman_left_2");
    	right1 = setup("/npc/oldman_right_1");
    	right2 = setup("/npc/oldman_right_2");

    }
    
    
    /**
     * METODO SETDIALOG QUE ESTABLECE EL DIALOGO DEL NPC
     */
    public void setDialog () {
    	
    	dialogues [0] = "Hola guerrero.";
    	dialogues [1] = "Tu mision es vencer a todos los enemigos /npara poder matar al demonio que atormenta la region./nHay un elemental en la playa, un/ngoblin en una fortaleza abandonada /ny un slime rodeado de arboles.";
    	dialogues [2] = "Habra llaves que te abriran puertas /npara poder continuar. /nAl lado del lago te he dejado un regalo, pero usalo /ncon sabiduria";
    	dialogues [3] = "Adelante";
    	
    }
    
    
    /**
     * METODO SETACTION QUE DECLARA MOVIMIENTOS ALEATORIOS DEL NPC
     */
    public void setAction() {
    	
    	contadorAccion++;
    	
    	if(contadorAccion == 120) {
    	
    	Random random = new Random();
    	int i = random.nextInt(100)+1; //Coge un numero del 1 al 100
    	
    	if(i <= 25) {
    		
    		direction = "up";
    	}
    	
    	if(i > 25 && i <= 50) {
    		direction = "down";
    	}
    	
    	if(i > 50 && i <= 75) {
    		direction = "left";
    	}
    	
    	if(i > 75 && i <= 100) {
    		direction = "right";
    	}
    	
    	contadorAccion = 0;
    	}
    }
    
    
    /**
     * METODO SPEAK PARA HABLAR CON EL NPC
     */
    public void speak () {
    	if (dialogues[dialogIndex] == null) {
    		dialogIndex = 0;
    	}
    	gp.ui.currentDialog = dialogues[dialogIndex];
    	dialogIndex ++;
    	
    	switch (gp.player.direction){
    		
    	case "up":
    		direction = "down";
    		break;
    	case "down":
    		direction = "up";
    		break;
    	case "left":
    		direction = "right";
    		break;
    	case "right":
    		direction = "left";
    		break;
    		
    	}
    }
    
}
    	

package entity;

import java.util.Random;

import main.PanelDeJuego;

public class npc extends Entity {
	
	public npc(PanelDeJuego gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImagen();
		setDialog ();
	}
	
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
    
    public void setDialog () {
    	
    	dialogues [0] = "Hola";
    	dialogues [1] = "Que tal";
    	dialogues [2] = "Tu mision es /n vencer a todos los enemigos";
    	dialogues [3] = "Adelante";
    	
    }
    
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
    	

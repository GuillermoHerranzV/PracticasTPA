package entity;

import java.awt.image.BufferedImage;

/**
 * Clase base para todas las entidades del juego
 */
public class Entity {
    private int hp;
    private int dmg;
    
    public int x, y;
    public int speed;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    //Utilizado para poder guardar las imagenes del juego
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    
    public int getHp() {
        return hp;
    }
    
    public void setHp(int health) {
        this.hp = health;
    }
    
        public int getDmg() {
        return dmg;
    }
    
    public void setDmg(int damage) {
        this.dmg = damage;
    }
    
    public int getX() {
        return hp;
    }
    public int getY() {
        return hp;
    }
    public int getSpeed() {
        return hp;
    }
    public void setX(int X) {
        this.x = X;
    }
    public void setY(int Y) {
        this.y = Y;
    }
    public void setSpeed(int Speed) {
        this.speed = Speed;
    }
    
    /**
     * Funcion que utilizaremos para implementar el ataque
     * @return Retorna un string como mensaje de confirmacion
     */
    public String attack() {
        return ("Entity has attacked and reduced enemy's health by " + this.getDmg());
    }
    
}


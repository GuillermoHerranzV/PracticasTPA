import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controles implements KeyListener{
	
	public boolean arriba, abajo, izq, der;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
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
		
	}

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


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private boolean[] keyDown = new boolean[4];
    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
        for (int i = 0; i < 4; i++)
            keyDown[i] = false;
    }

    //if key is pressed do these commands
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getID() == ID.Player) {
                if (key == KeyEvent.VK_UP) {
                    temp.setVelY(-5);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_DOWN) {
                    temp.setVelY(5);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    temp.setVelX(5);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_LEFT) {
                    temp.setVelX(-5);
                    keyDown[3] = true;
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    //actions when the keys are released
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getID() == ID.Player) {
                if (key == KeyEvent.VK_UP) {
                    temp.setVelY(0);
                    keyDown[0] = false;
                }
                if (key == KeyEvent.VK_DOWN) {
                    temp.setVelY(0);
                    keyDown[1] = false;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    temp.setVelX(0);
                    keyDown[2] = false;
                }
                if (key == KeyEvent.VK_LEFT) {
                    temp.setVelX(0);
                    keyDown[3] = false;
                }
                //if(!keyDown[0] && !keyDown[1]) temp.setVelY(0);
                //if(!keyDown[2] && !keyDown[3]) temp.setVelX(0);
            }
        }
    }
}

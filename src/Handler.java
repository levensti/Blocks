import java.awt.*;
import java.util.LinkedList;

public class Handler {
    //list of objects using LinkedList
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    //render everything in the list
    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    //add objects to the list
    public void addObject(GameObject object) {
        this.object.add(object);
    }

    //remove objects from the list
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    //clear enemies from the list EXCEPT the player
    public void clearEnemys() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getID() == ID.Player) {
                object.clear();
                if (Game.gameState != Game.STATE.End)
                    addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
            }
        }
    }
}

import java.awt.*;

public class Player extends GameObject {

    Handler handler;

    //basic constructors of player class
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    //bounds to detect intersection
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    //movement per frame
    @Override
    public void tick() {
        x += this.velX;
        y += this.velY;

        x = Game.clamp(x, 0, Game.width - 32);
        y = Game.clamp(y, 0, Game.height - 52);
        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.white, 32, 32, 0.07f, handler));

        collision();
    }

    //reduce health if player collides with the enemies
    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject object = handler.object.get(i);
            if (object.getID() == ID.BasicEnemy || object.getID() == ID.FastEnemy || object.getID() == ID.SmartEnemy || object.getID() == ID.EnemyBoss) {
                if (getBounds().intersects(object.getBounds())) {
                    //collision code
                    HUD.health--;
                }
            }
        }
    }

    //render the player
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, 32, 32);
    }

}

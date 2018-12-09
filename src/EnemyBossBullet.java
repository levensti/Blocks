import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject {

    private Handler handler;
    Random r = new Random();

    //constructors for the bullets shot by the boss
    public EnemyBossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
        this.handler = handler;
    }

    //bounds used to determine intersection
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    //the movement of bullets being shot per frame
    public void tick() {
        x += velX;
        y += velY;

        //if(y <= 0 || y >= Game.height - 32) velY *= -1;
        //if(x <= 0 || x >= Game.width - 16) velX *= -1;

        if (y >= Game.height) handler.removeObject(this);

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.red, 16, 16, 0.03f, handler));
    }

    //painting the bullets
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}

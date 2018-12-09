import java.awt.*;

public class FastEnemy extends GameObject {

    private Handler handler;

    //constructors for fast enemy
    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 2;
        velY = 9;
    }

    //bounds set for intersection detection
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    //movement of fast enemy per frame;
    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.height - 32) velY *= -1;
        if (x <= 0 || x >= Game.width - 16) velX *= -1;

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.cyan, 16, 16, 0.03f, handler));
    }

    //painting the fast enemy
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}

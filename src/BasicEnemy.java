import java.awt.*;

public class BasicEnemy extends GameObject {

    private Handler handler;

    //basic constructor for Basic Enemy
    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velX = 5;
        velY = 5;
        this.handler = handler;
    }

    //for intersection
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    //moving frames of the basic enemy
    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.height - 32) velY *= -1;
        if (x <= 0 || x >= Game.width - 16) velX *= -1;

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.red, 16, 16, 0.03f, handler));
    }

    //rendering basic enemy on screen
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}

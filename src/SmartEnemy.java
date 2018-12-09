import java.awt.*;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    //basic constructors for smart enemy
    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }
    }

    //bounds to detect collision with player
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    //follows the player around with a slower velocity
    //prevent player from sitting in a corner
    public void tick() {
        x += velX;
        y += velY;
        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float dist = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
        velX = (float) ((-1.0 / dist) * diffX);
        velY = (float) ((-1.0 / dist) * diffY);
        if (y <= 0 || y >= Game.height - 32) velY *= -1;
        if (x <= 0 || x >= Game.width - 16) velX *= -1;

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.green, 16, 16, 0.03f, handler));
    }

    //renders the smart enemy
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}

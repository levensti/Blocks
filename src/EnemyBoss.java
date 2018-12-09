import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject {

    private Handler handler;
    private int timer = 80;
    private int timer2 = 50;
    Random r = new Random();

    //ultimate boss of enemies
    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 0;
        velY = 2;
    }
 
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 96, 96);
    }

    //movement of the boss per frame
    public void tick() {
        x += velX;
        y += velY;

        if (timer <= 0) velY = 0;
        else timer--;

        if (timer <= 0) timer2--;
        if (timer2 <= 0) {
            if (velX == 0) velX = 2;
            velX += 0.001f;
            int spawn = r.nextInt(10);
            //shooting bullets
            if (spawn == 0) handler.addObject(new EnemyBossBullet((int) x + 48, (int) y + 48, ID.BasicEnemy, handler));
        }
        if (x <= 0 || x >= Game.width - 96) velX *= -1;

    }

    //paint boss
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 96, 48);
    }
}

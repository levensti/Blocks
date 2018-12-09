import java.awt.*;

public class Trail extends GameObject {
    private float alpha = 1;
    private Handler handler;
    private Color color;
    private int width, height;
    private float life;

    //basic constructors for trails effect
    public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    //movements of the trail per frame
    @Override
    public void tick() {
        if (alpha > life)
            alpha -= (life - 0.0001f);
        else handler.removeObject(this);
    }

    //rendering the trail behind the objects
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
        g2d.setComposite(makeTransparent(1));

    }

    //graphing utils to make the trails transparent
    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    @Override
    //required abstract method getBounds() but useless thus returning null
    public Rectangle getBounds() {
        return null;
    }

}

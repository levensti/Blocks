import java.awt.*;

//abstract class for objects in the game
public abstract class GameObject {
    protected float x, y;
    protected ID id;
    protected float velX, velY;

    //basic per objects constructor
    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    //change of frame
    public abstract void tick();

    //paint the objects
    public abstract void render(Graphics g);

    //setting boundaries
    public abstract Rectangle getBounds();

    //getting & setters for x and y position and velocity of x and y of said objects
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return this.id;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getVelX() {
        return this.velX;
    }

    public float getVelY() {
        return this.velY;
    }
}

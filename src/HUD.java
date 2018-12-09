import java.awt.*;

public class HUD {
    public static float health = 100;
    private float greenValue = 255;
    private int score = 0;
    private int level = 1;

    //heads up display's movement every frame
    public void tick() {
        health = Game.clamp(health, 0f, 100f);
        greenValue = Game.clamp(greenValue, 0f, 255f);
        greenValue = 2 * health;
        score++;
    }

    //painting the heads up displays
    //health, level and scores are recorded
    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, (int) greenValue, 0));
        g.fillRect(15, 15, (int) (health * 2), 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
    }

    //basic setters & getters of scores and levels
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

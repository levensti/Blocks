import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = -1285670934175365101L;
    public static final int width = 640;
    public static final int height = width / 12 * 9;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private Random r;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    public enum STATE {
        Menu,
        Help,
        Game,
        End;
    }

    //starting off with game
    public static STATE gameState = STATE.Game;

    //Constructor of Game() to call all the other classes and and objects
    public Game() {
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);


        new Window(width, height, "GAME", this);

        spawner = new Spawn(handler, hud);

        r = new Random();

        if (gameState == STATE.Game) {
            handler.addObject(new Player(200, 200, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
        }
    }

    //starting the thread for the game
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    //stopping the thread for the game
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //the game loop that runs and updates the frames
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        double timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    //all the movement per frame
    private void tick() {
        handler.tick();
        if (gameState == STATE.Game) {
            hud.tick();
            spawner.tick();

            if (HUD.health <= 0) {
                HUD.health = 100;
                gameState = STATE.End;
                handler.clearEnemys();
            }
        } else if ((gameState == STATE.Menu) || (gameState == STATE.End)) {
            menu.tick();
        }
    }

    //panting and all the called objects
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        handler.render(g);

        if (gameState == STATE.Game) {
            hud.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help || (gameState == STATE.End)) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    //clamping the borders, so the bullets bounces back/stops when reaches the border
    public static float clamp(float var, float min, float max) {
        if (var >= max) return var = max;
        else if (var <= min) return var = min;
        else return var;
    }

    //drive
    public static void main(String[] args) {
        new Game();
    }
}

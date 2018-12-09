
import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private int scoreKeep = 0;

    //basic constructors for spawn class to spawn different interfaces and objects
    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    //movements per frame for spawning different objects
    public void tick() {
        scoreKeep++;
        if (scoreKeep >= 400) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);


            //LEVEL EDITING

            if (hud.getLevel() == 2)
                handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
            else if (hud.getLevel() == 3)
                handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
            else if (hud.getLevel() == 4)
                handler.addObject(new FastEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.FastEnemy, handler));
            else if (hud.getLevel() == 5)
                handler.addObject(new SmartEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.SmartEnemy, handler));
            else if (hud.getLevel() == 6)
                handler.addObject(new FastEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.FastEnemy, handler));
            else if (hud.getLevel() == 7)
                handler.addObject(new SmartEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.SmartEnemy, handler));
            else if (hud.getLevel() == 10) {
                handler.clearEnemys();
                handler.addObject(new EnemyBoss((Game.width / 2) - 48, -120, ID.EnemyBoss, handler));
            } else if (hud.getLevel() == 15) {
                handler.clearEnemys();
                handler.addObject(new EnemyBoss((Game.width / 2) - 48, -120, ID.EnemyBoss, handler));
                handler.addObject(new EnemyBoss((Game.width / 2) - 48, -120, ID.EnemyBoss, handler));
                handler.addObject(new EnemyBoss((Game.width / 2) - 48, -120, ID.EnemyBoss, handler));
                handler.addObject(new EnemyBoss((Game.width / 2) - 48, -120, ID.EnemyBoss, handler));
                handler.addObject(new EnemyBoss((Game.width / 2) - 48, -120, ID.EnemyBoss, handler));
            } else if (hud.getLevel() == 20) {
                handler.clearEnemys();
                handler.addObject(new EnemyBoss((Game.width / 2) - 48, -120, ID.EnemyBoss, handler));
                handler.addObject(new EnemyBoss((Game.width / 2) - 48, -120, ID.EnemyBoss, handler));
                handler.addObject(new SmartEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.SmartEnemy, handler));
                handler.addObject(new SmartEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.SmartEnemy, handler));
            }
        }
    }
}

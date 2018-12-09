
/*
 * The Menu class is supposed to start the menu interface
 * before the games begin. However, the games messes up when
 * it is implemented so we decided to remove this class for
 * now and improve it.
 */


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private Random r;
    private HUD hud;
    private Game.STATE state;

    //basic constructors for Menu class
    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }

    //checking whether the mouse is pressed and the commands for game state
    public void MousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == state.Menu) {
            //play button
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                game.gameState = state.Game;
                handler.addObject(new Player(200, 200, ID.Player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
            }
            //help button
            if (mouseOver(mx, my, 210, 250, 200, 64))
                game.gameState = state.Help;
            //quit button
            if (mouseOver(mx, my, 210, 350, 200, 64))
                System.exit(1);
        }

        //back button for help
        if (game.gameState == state.Help) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = state.Menu;
                return;
            }
        }
    }

    public void MouseReleased(MouseEvent e) {

    }

    //determine if the mouse is over the buttons with set borders
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height)
                return true;
            else return false;
        } else return false;
    }

    public void tick() {

    }

    //painting different buttons
    public void render(Graphics g) {
        if (game.gameState == state.Menu) {
            Font font = new Font("arial", 1, 50);
            Font font2 = new Font("arial", 1, 50);

            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Menu", 245, 70);

            g.setFont(font2);

            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 255, 200);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 255, 300);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 255, 400);
        } else if (game.gameState == state.Help) {
            Font font = new Font("arial", 1, 50);
            Font font2 = new Font("arial", 1, 50);

            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Help", 245, 70);

            g.setFont(font2);
            g.drawString("Use Arrow Keys to move player and dodge", 10, 200);
            g.drawString("the incoming perverted colored squares !", 10, 220);
            g.setFont(font2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 255, 400);
        }
        //GAME OVER page after health bar reduced to 0
        else if (game.gameState == state.End) {
            Font font = new Font("arial", 1, 30);
            Font font2 = new Font("arial", 1, 30);

            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER", 235, 70);

            g.setFont(font2);
            g.drawString("You lost with a score of: " + hud.getScore(), 100, 250);

		/*	g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Play Again", 275, 350);
		*/


        }
    }
}

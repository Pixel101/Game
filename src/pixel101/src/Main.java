package pixel101.src;

import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame
{
	public static Random r = new Random();
	public static final int ts = 16;
	Level currentLevel;
	
	public Main(String title)
	{
		super(title);
	}

	public void init(GameContainer c) throws SlickException
	{
		for (Tile t : Tile.tileList) if (t != null) t.init();
		currentLevel = new Level();
	}

	public void update(GameContainer c, int delta) throws SlickException
	{
		if (c.getInput().isKeyDown(Input.KEY_ESCAPE))
		{
			c.exit();
		}
		currentLevel.update(c, delta);
	}
	
	public void render(GameContainer c, Graphics g) throws SlickException
	{
		//g.scale(2, 2);
		currentLevel.render(c, g);
	}

	public static void main(String[] args)
	{
		Main main = new Main("Game");
		try
		{
			AppGameContainer container = new AppGameContainer(main);
			container.setFullscreen(false);
			container.setDisplayMode(640, 480, false);
			//container.setTargetFrameRate(60);
			container.setVSync(false);
			container.setIcon("res/tex/icon.png");
			container.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
}

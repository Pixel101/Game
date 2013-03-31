package pixel101.src;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame
{
	Level currentLevel;
	
	public Main(String title)
	{
		super(title);
	}

	public void init(GameContainer c) throws SlickException
	{
		c.setFullscreen(false);
		c.setTargetFrameRate(60);
		c.setVSync(false);
		
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
		currentLevel.render(c, g);
	}

	public static void main(String[] args)
	{
		Main main = new Main("Game");
		try
		{
			AppGameContainer container = new AppGameContainer(main);
			container.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
}

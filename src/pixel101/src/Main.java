package pixel101.src;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame
{
	TileRenderer tileRenderer;
	Level level1;
	
	public Main(String title)
	{
		super(title);
	}

	public void init(GameContainer c) throws SlickException
	{
		c.setFullscreen(false);
		
		tileRenderer = new TileRenderer();
		for (Tile t : Tile.tileList) if (t != null) t.loadTexture();
		level1 = new Level();
	}

	public void update(GameContainer c, int delta) throws SlickException
	{
		if (c.getInput().isKeyDown(Input.KEY_ESCAPE))
		{
			c.exit();
		}
		level1.update(c, delta);
	}
	
	public void render(GameContainer c, Graphics g) throws SlickException
	{
		tileRenderer.render(g, level1);
		g.drawString("Hello World - Press Space!  Input is so easy!", 20, 30);
		g.drawString("" + c.getInput().isKeyDown(Input.KEY_SPACE), 20, 50);
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

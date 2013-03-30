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
	
	Image temp;
	float x = 0;

	public Main(String title)
	{
		super(title);
	}

	public void init(GameContainer container) throws SlickException
	{
		container.setFullscreen(false);
		temp = new Image("res/tex/tiles/temp.png");
	}

	public void update(GameContainer container, int delta) throws SlickException
	{
		if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
		{
			container.exit();
		}
		x += delta * 0.02;
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		for (int x = 0; x < 16; x++)
		{
			for (int y = 0; y < 16; y++)
			{
				g.drawImage(temp, this.x + (x * 16), y * 16);
			}
		}
		g.drawString("Hello World - Press Space!  Input is so easy!", 20, 30);
		g.drawString("" + container.getInput().isKeyDown(Input.KEY_SPACE), 20, 50);
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

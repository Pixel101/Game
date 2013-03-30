package pixel101.src;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame
{

	public Main(String title)
	{
		super(title);
	}

	public void init(GameContainer container) throws SlickException
	{
		
	}

	public void update(GameContainer container, int delta) throws SlickException
	{
		
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
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

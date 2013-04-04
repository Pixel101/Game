package pixel101.src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class EntityPlayer extends Entity
{
	public EntityPlayer(Main main, float x, float y)
	{
		super(main, x, y);
		width = 14;
		height = 14;
	}
	
	public void update(GameContainer c, int delta)
	{
		float s = 0.1f;
		Input input = c.getInput();
		if (input.isKeyDown(Input.KEY_W))
		{
			y -= s * delta;
		}
		if (input.isKeyDown(Input.KEY_S))
		{
			y += s * delta;
		}
		if (input.isKeyDown(Input.KEY_A))
		{
			x -= s * delta;
		}
		if (input.isKeyDown(Input.KEY_D))
		{
			x += s * delta;
		}
		super.handleCollisions();
		main.camx = x + width / 2 - (c.getWidth() / 2) / 2;
		main.camy = y + height / 2 - (c.getHeight() / 2) / 2;
		if (main.camx < 0) main.camx = 0;
		else if (main.camx> main.map.getWidth() * main.map.getTileWidth() - c.getWidth() / main.scale) main.camx = main.map.getWidth() * main.map.getTileWidth() - c.getWidth() / main.scale;
		if (main.camy < 0) main.camy = 0;
		else if (main.camy > main.map.getHeight() * main.map.getTileHeight() - c.getHeight() / main.scale) main.camy = main.map.getHeight() * main.map.getTileHeight() - c.getHeight() / main.scale;
		//System.out.println(main.map.getWidth() + ",  " + main.map.getTileWidth() + ",  " + c.getWidth());
		//System.out.println(main.camx + ", " + main.camy);
		for (Entity e : main.entities)
		{
			if (e instanceof EntityMapLoader)
			{
				if (new Rectangle(e.x, e.y, e.width, e.height).intersects(new Rectangle(main.player.x, main.player.y, main.player.width, main.player.height)))
				{
					((EntityMapLoader)e).loadMap();
					return;
				}
			}
		}
		//System.out.println(main.camx + ",  " + main.camy);
	}

}

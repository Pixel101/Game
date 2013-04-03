package pixel101.src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

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
		main.camx = x + width / 2 - c.getWidth() / 2;
		main.camy = y + height / 2 - c.getHeight() / 2;
		//System.out.println(main.camx + ",  " + main.camy);
	}

}

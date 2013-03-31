package pixel101.src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class EntityPlayer extends Entity
{
	public EntityPlayer(Level level, float x, float y) throws SlickException
	{
		super(level, x, y);
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
		level.camx = x + collisionRect.getWidth() / 2 - c.getWidth() / 2;
		level.camy = y + collisionRect.getHeight() / 2 - c.getHeight() / 2;
		//System.out.println(level.camx + ",  " + level.camy);
	}

}

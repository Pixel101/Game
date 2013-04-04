package pixel101.src;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class EntityPlayer extends Entity
{
	SpriteSheet spriteSheet;
	Animation walk_up;
	Animation walk_down;
	Animation walk_left;
	Animation walk_right;
	byte direction, prevdirection;
	boolean walking;
	
	public EntityPlayer(Main main, float x, float y, GameContainer c)
	{
		super(main, x, y);
		pos.setSize(14, 14);
		try
		{
			spriteSheet = new SpriteSheet("res/tex/ent/player.png", 16, 24);
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
		int d = 100;
		(walk_down = new Animation(spriteSheet, new int[] {0, 0, 1, 0, 0, 0, 2, 0}, new int[] {d, d, d, d})).setAutoUpdate(false);
		(walk_up = new Animation(spriteSheet, new int[] {0, 1, 1, 1, 0, 1, 2, 1}, new int[] {d, d, d, d})).setAutoUpdate(false);
		(walk_right = new Animation(spriteSheet, new int[] {0, 2, 1, 2, 0, 2, 2, 2}, new int[] {d, d, d, d})).setAutoUpdate(false);
		(walk_left = new Animation(spriteSheet, new int[] {0, 3, 1, 3, 0, 3, 2, 3}, new int[] {d, d, d, d})).setAutoUpdate(false);
	}
	
	public void update(GameContainer c, int delta)
	{
		handleMovement(c, delta);
		handleCollisions();
		moveCamera(c);
		for (Entity e : main.entities)
		{
			if (e instanceof EntityMapLoader)
			{
				if (pos.intersects(e.pos))
				{
					((EntityMapLoader)e).loadMap(c);
					return;
				}
			}
		}
	}
	
	void handleMovement(GameContainer c, int delta)
	{
		final float s = 0.08f;
		Input input = c.getInput();
		if (input.isKeyPressed(Input.KEY_W))
		{
			direction = 1;
			walking = true;
			walk_up.setCurrentFrame(1);
		}
		if (input.isKeyPressed(Input.KEY_S))
		{
			direction = 3;
			walking = true;
			walk_down.setCurrentFrame(1);
		}
		if (input.isKeyPressed(Input.KEY_A))
		{
			direction = 2;
			walking = true;
			walk_left.setCurrentFrame(1);
		}
		if (input.isKeyPressed(Input.KEY_D))
		{
			direction = 0;
			walking = true;
			walk_right.setCurrentFrame(1);
		}
		if (walking)
		{
			switch (direction)
			{
				case 0:
				{
					if (!input.isKeyDown(Input.KEY_D))
					{
						walking = false;
						walk_right.setCurrentFrame(0);
					}
					break;
				}
				case 1:
				{
					if (!input.isKeyDown(Input.KEY_W))
					{
						walking = false;
						walk_up.setCurrentFrame(0);
					}
					break;
				}
				case 2:
				{
					if (!input.isKeyDown(Input.KEY_A))
					{
						walking = false;
						walk_left.setCurrentFrame(0);
					}
					break;
				}
				case 3:
				{
					if (!input.isKeyDown(Input.KEY_S))
					{
						walking = false;
						walk_down.setCurrentFrame(0);
					}
					break;
				}
			}
		}
		if (walking)
		{
			switch (direction)
			{
				case 0:
				{
					move(s * delta, 0);
					walk_right.update(delta);
					break;
				}
				case 1:
				{
					move(0, -s * delta);
					walk_up.update(delta);
					break;
				}
				case 2:
				{
					move(-s * delta, 0);
					walk_left.update(delta);
					break;
				}
				case 3:
				{
					move(0, s * delta);
					walk_down.update(delta);
					break;
				}
			}
		}
		prevdirection = direction;
	}

	void moveCamera(GameContainer c)
	{
		main.camx = pos.getCenterX() - (c.getWidth() / 2) / main.scale;
		main.camy = pos.getCenterY() - (c.getHeight() / 2) / main.scale;
		if (main.camx < 0) main.camx = 0;
		else if (main.camx> main.map.getWidth() * main.map.getTileWidth() - c.getWidth() / main.scale)
			main.camx = main.map.getWidth() * main.map.getTileWidth() - c.getWidth() / main.scale;
		if (main.camy < 0) main.camy = 0;
		else if (main.camy > main.map.getHeight() * main.map.getTileHeight() - c.getHeight() / main.scale)
			main.camy = main.map.getHeight() * main.map.getTileHeight() - c.getHeight() / main.scale;
		
	}
	
	public void render(GameContainer c, Graphics g)
	{
		//anim.draw(x - (16 - width), y - (24 - height), 16, 24);
		Animation animToDraw = null;
		switch (direction)
		{
			case 0:
			{
				animToDraw = walk_right;
				break;
			}
			case 1:
			{
				animToDraw = walk_up;
				break;
			}
			case 2:
			{
				animToDraw = walk_left;
				break;
			}
			case 3:
			{
				animToDraw = walk_down;
				break;
			}
		}
		if (animToDraw != null)
			animToDraw.draw(pos.getX() - (animToDraw.getWidth() - pos.getWidth()) / 2,
							pos.getY() - (animToDraw.getHeight() - pos.getHeight()),
							animToDraw.getWidth(), animToDraw.getHeight());
	}
}

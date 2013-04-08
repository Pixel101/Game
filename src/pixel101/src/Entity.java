package pixel101.src;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Entity
{
	Game game;
	public Rectangle pos;
	static Animation anim;
	
	public Entity(Game game)
	{
		this.game = game;
		pos = new Rectangle(0, 0, 0, 0);
	}
	
	public Entity(Game game, float x, float y)
	{
		this(game);
		this.pos.setLocation(x, y);
	}
	
	public static void init(GameContainer c)
	{
		
	}
	
	public void update(GameContainer c, int delta)
	{
		
	}
	
	protected void handleCollisions()
	{
		int tw = game.map.getTileWidth();
		int th = game.map.getTileHeight();
		int tx = (int)(pos.getCenterX() / tw);
		int ty = (int)(pos.getCenterY() / th);
		float mx, my;
		boolean s;
		Rectangle r = new Rectangle(0, 0, tw, th);
		for (int lx = tx - 1; lx <= tx + 1; lx++)
		{
			for (int ly = ty - 1; ly <= ty + 1; ly++)
			{
				if (lx < 0 || lx >= game.solid.length || ly < 0 || ly >= game.solid[0].length)
				{
					s = !(this instanceof EntityPlayer);
				}
				else
				{
					s = game.solid[lx][ly];
				}
				if (s)
				{
					r.setX(lx * tw);
					r.setY(ly * th);
					r = new Rectangle(lx * tw, ly * th, tw, th);
					if (r.intersects(pos))
					{
						//System.out.println(System.currentTimeMillis());
						float left = Math.abs(pos.getMinX() - r.getMaxX());
			            float right = (pos.getMaxX() - r.getMinX());
			            float top = Math.abs(pos.getMinY() - r.getMaxY());
			            float bottom = (pos.getMaxY() - r.getMinY());
			            if (left < right) mx = left; else mx = -right;
			            if (top < bottom) my = top; else my = -bottom;
			            if (Math.abs(mx) < Math.abs(my))
			            {
			            	move(mx, 0);
			            }
			            else
			            {
			            	move(0, my);
			            }
					}
				}
			}
		}
	}
	
	public void move(float x, float y)
	{
		pos.setX(pos.getX() + x);
		pos.setY(pos.getY() + y);
	}
	
	public void move(float amount, byte direction)
	{
		switch (direction)
		{
			case 0:
			{
				move(amount, 0);
				break;
			}
			case 1:
			{
				move(0, -amount);
				break;
			}
			case 2:
			{
				move(-amount, 0);
				break;
			}
			case 3:
			{
				move(0, amount);
				break;
			}
		}
	}
	
	public void render(GameContainer c, Graphics g)
	{
		if (anim != null)
			anim.draw(pos.getX(), pos.getY(), pos.getWidth(), pos.getHeight());
	}
}

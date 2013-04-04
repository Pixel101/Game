package pixel101.src;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Entity
{
	Main main;
	public float x, y;
	float width = 0, height = 0;
	Animation anim;
	
	public Entity(Main main)
	{
		this.main = main;
		if (width == 0 && height == 0)
		{
			width = main.map.getTileWidth();
			height = main.map.getTileHeight();
		}
		//System.out.println(collisionRect.toString());
		try
		{
			anim = new Animation();
			anim.addFrame(new Image("res/tex/ent/notex.png"), 1);
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public Entity(Main main, float x, float y)
	{
		this(main);
		this.x = x;
		this.y = y;
	}
	
	public void update(GameContainer c, int delta)
	{
		handleCollisions(); 
	}
	
	protected void handleCollisions()
	{
		int tw = main.map.getTileWidth();
		int th = main.map.getTileHeight();
		int tx = (int)((x + width / 2) / tw);
		int ty = (int)((y + height / 2) / th);
		float mx, my;
		boolean s;
		Rectangle r = new Rectangle(0, 0, tw, th);
		Rectangle er;
		for (int lx = tx - 1; lx <= tx + 1; lx++)
		{
			for (int ly = ty - 1; ly <= ty + 1; ly++)
			{
				if (lx < 0 || lx >= main.solid.length || ly < 0 || ly >= main.solid[0].length)
				{
					s = !(this instanceof EntityPlayer);
				}
				else
				{
					s = main.solid[lx][ly];
				}
				if (s)
				{
					r.setX(lx * tw);
					r.setY(ly * th);
					r = new Rectangle(lx * tw, ly * th, tw, th);
					er = new Rectangle(x, y, width, height);
					if (r.intersects(er))
					{
						//System.out.println(System.currentTimeMillis());
						float left = Math.abs(er.getMinX() - r.getMaxX());
			            float right = (er.getMaxX() - r.getMinX());
			            float top = Math.abs(er.getMinY() - r.getMaxY());
			            float bottom = (er.getMaxY() - r.getMinY());
			            if (left < right) mx = left; else mx = -right;
			            if (top < bottom) my = top; else my = -bottom;
			            if (Math.abs(mx) < Math.abs(my))
			            {
			            	x += mx;
			            }
			            else
			            {
			            	y += my;
			            }
					}
				}
			}
		}
	}
	
	public void render(GameContainer c, Graphics g)
	{
		g.drawImage(anim.getCurrentFrame(),
					x - main.camx, y - main.camy, x - main.camx + width, y - main.camy + height,
					0, 0, anim.getWidth(), anim.getHeight());
	}
}

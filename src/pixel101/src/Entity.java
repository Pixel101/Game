package pixel101.src;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

public class Entity
{
	Main main;
	public Rectangle pos;
	Animation anim;
	
	public Entity(Main main)
	{
		this.main = main;
		pos = new Rectangle(0, 0, 0, 0);
	}
	
	public Entity(Main main, float x, float y)
	{
		this(main);
		this.pos.setLocation(x, y);
	}
	
	public void update(GameContainer c, int delta)
	{
		
	}
	
	protected void handleCollisions()
	{
		int tw = main.map.getTileWidth();
		int th = main.map.getTileHeight();
		int tx = (int)(pos.getCenterX() / tw);
		int ty = (int)(pos.getCenterY() / th);
		float mx, my;
		boolean s;
		Rectangle r = new Rectangle(0, 0, tw, th);
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
	
	public void render(GameContainer c, Graphics g)
	{
		if (anim != null)
			g.drawImage(anim.getCurrentFrame(),
						pos.getX(), pos.getY(), pos.getMaxX(), pos.getMaxY(),
						0, 0, anim.getWidth(), anim.getHeight());
	}
}

package pixel101.src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Entity
{
	Level level;
	public float x, y;
	Rectangle collisionRect;
	
	public Entity(Level level)
	{
		collisionRect = new Rectangle(0, 0, 14, 14);
	}
	
	public Entity(Level level, float x, float y)
	{
		this(level);
		this.x = x;
		this.y = y;
	}
	
	public void update(GameContainer c, int delta)
	{
		//	collisions
		int currentTileX = (int)Math.floor(x / 16);
		int currentTileY = (int)Math.floor(y / 16);
		int currentTileWorldX = currentTileX * 16;
		int currentTileWorldY = currentTileY * 16;
		
	}
	
	public void render(GameContainer c, Graphics g)
	{
		
	}
}

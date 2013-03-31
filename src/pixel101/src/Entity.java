package pixel101.src;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Entity
{
	Level level;
	public float x, y;
	Rectangle collisionRect;
	Image spriteSheet;
	
	
	public Entity(Level level) throws SlickException
	{
		this.level = level;
		collisionRect = new Rectangle(0, 0, 14, 14);
		spriteSheet = new Image("res/tex/ent/notex.png");
	}
	
	public Entity(Level level, float x, float y) throws SlickException
	{
		this(level);
		this.x = x;
		this.y = y;
	}
	
	public void update(GameContainer c, int delta)
	{
		handleCollisions();
	}
	
	protected void handleCollisions()
	{
		int currentTileX = (int)Math.floor((x + collisionRect.getWidth() / 2) / 16);
		int currentTileY = (int)Math.floor((y + collisionRect.getHeight() / 2) / 16);
		int currentTileWorldX = currentTileX * 16;
		int currentTileWorldY = currentTileY * 16;
		float tx = x, ty = y;
		//System.out.println(currentTileX + ",  " + currentTileY + ",    " + currentTileWorldX + ",  " + currentTileWorldY);
		if (x < currentTileWorldX)
		{
			if (Tile.tileList[level.getTileId(currentTileX - 1, currentTileY)].solid ||
				y < currentTileWorldY && Tile.tileList[level.getTileId(currentTileX, currentTileY - 1)].solid ||
				y >= currentTileWorldY + 1 && Tile.tileList[level.getTileId(currentTileX, currentTileY + 1)].solid)
			{
				tx = currentTileWorldX;
				//collision
			}
			else
			{
				//walking on
			}
		}
		else if (x + collisionRect.getWidth() >= currentTileWorldX + 16)
		{
			if (Tile.tileList[level.getTileId(currentTileX + 1, (int)Math.floor(y / 16))].solid)
			{
				tx = currentTileWorldX + 16 - 1 - collisionRect.getWidth();
				//collision
			}
			else
			{
				//walking on
			}
		}
		if (y < currentTileWorldY)
		{
			if (Tile.tileList[level.getTileId((int)Math.floor(x / 16), currentTileY - 1)].solid)
			{
				ty = currentTileWorldY;
				//collision
			}
			else
			{
				//walking on
			}
		}
		else if (y + collisionRect.getHeight() >= currentTileWorldY + 16)
		{
			if (Tile.tileList[level.getTileId((int)Math.floor(x / 16), currentTileY + 1)].solid)
			{
				ty = currentTileWorldY + 16 - 1 - collisionRect.getHeight();
				//collision
			}
			else
			{
				//walking on
			}
		}
		x = tx;
		y = ty;
	}
	
	public void render(GameContainer c, Graphics g)
	{
		g.drawImage(spriteSheet, x - level.camx, y - level.camy);
		g.setColor(Color.white);
		g.drawRect(x - level.camx + collisionRect.getX(), y - level.camy + collisionRect.getY(), collisionRect.getWidth(), collisionRect.getHeight());
	}
}

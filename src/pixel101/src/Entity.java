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
		if (collisionRect == null)
		{
			collisionRect = new Rectangle(0, 0, 1, 1);
		}
		//collisionRect.grow(Main.ts, Main.ts);
		collisionRect.setWidth(collisionRect.getWidth() * Main.ts);
		collisionRect.setHeight(collisionRect.getHeight() * Main.ts);
		//System.out.println(collisionRect.toString());
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
		int currentTileX = (int)Math.floor((x + collisionRect.getWidth() / 2) / Main.ts);
		int currentTileY = (int)Math.floor((y + collisionRect.getHeight() / 2) / Main.ts);
		int currentTileWorldX = currentTileX * Main.ts;
		int currentTileWorldY = currentTileY * Main.ts;
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
		else if (x + collisionRect.getWidth() >= currentTileWorldX + Main.ts)
		{
			if (Tile.tileList[level.getTileId(currentTileX + 1, (int)Math.floor(y / 16))].solid)
			{
				tx = currentTileWorldX + Main.ts - 1 - collisionRect.getWidth();
				//collision
			}
			else
			{
				//walking on
			}
		}
		if (y < currentTileWorldY)
		{
			if (Tile.tileList[level.getTileId((int)Math.floor(x / Main.ts), currentTileY - 1)].solid)
			{
				ty = currentTileWorldY;
				//collision
			}
			else
			{
				//walking on
			}
		}
		else if (y + collisionRect.getHeight() >= currentTileWorldY + Main.ts)
		{
			if (Tile.tileList[level.getTileId((int)Math.floor(x / Main.ts), currentTileY + 1)].solid)
			{
				ty = currentTileWorldY + Main.ts - 1 - collisionRect.getHeight();
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
		g.drawImage(spriteSheet, x - level.camx, y - level.camy, x - level.camx + Main.ts, y - level.camy + Main.ts, 0, 0, spriteSheet.getWidth(), spriteSheet.getHeight());
		g.setColor(Color.white);
		g.drawRect(x - level.camx + collisionRect.getX(), y - level.camy + collisionRect.getY(), collisionRect.getWidth(), collisionRect.getHeight());
	}
}

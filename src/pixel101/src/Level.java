package pixel101.src;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Level
{
	byte[] tiles;
	ArrayList<Entity> entities;
	int width, height;
	float camx, camy;
	
	public Level() throws SlickException
	{
		camx = camy = 0;
		width = height = 10;
		tiles = new byte[width * height];
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				if (x == 0 || x == width - 1 || y == 0 || y == height - 1)
				{
					setTile(Tile.stone.id, x, y);
				}
				else
				{
					setTile(Tile.grass.id, x, y);
				}
			}
		}
		setTile(Tile.stone.id, 5, 5);
		
		entities = new ArrayList<Entity>();
		entities.add(new EntityPlayer(this, 30, 30));
	}
	
	public void update(GameContainer c, int delta)
	{
		
		for (Entity e : entities) e.update(c, delta);
	}
	
	public void render(GameContainer c, Graphics g)
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				Tile.tileList[getTileId(x, y)].render(c, g, (x * 16) - (int)camx, (y * 16) - (int)camy);
			}
		}
		for (Entity e : entities) e.render(c, g);
	}
	
	public int getTileId(int x, int y)
	{
		if (insideWorld(x, y))
		{
			return tiles[x + y * width];
		}
		else
		{
			return Tile.stone.id;
		}
	}

	public void setTile(int id, int x, int y)
	{
		if (insideWorld(x, y))
			tiles[x + y * width] = (byte)id;
	}
	
	public boolean insideWorld(int x, int y)
	{
		if (x < 0) return false;
		if (x >= width) return false;
		if (y < 0) return false;
		if (y >= height) return false;
		return true;
	}
}

package pixel101.src;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Level
{
	byte[] tiles;
	ArrayList<Entity> entities;
	int width, height;
	float camx, camy;
	
	public Level()
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
		entities.add(new Entity(this, 30, 30));
	}
	
	public void update(GameContainer c, int delta)
	{
		float s = 0.1f;
		Input input = c.getInput();
		if (input.isKeyDown(Input.KEY_W))
		{
			camy -= s * delta;
		}
		if (input.isKeyDown(Input.KEY_S))
		{
			camy += s * delta;
		}
		if (input.isKeyDown(Input.KEY_A))
		{
			camx -= s * delta;
		}
		if (input.isKeyDown(Input.KEY_D))
		{
			camx += s * delta;
		}
		for (Entity e : entities) e.update(c, delta);
	}
	
	public void render(GameContainer c, Graphics g)
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				Tile.tileList[getTileId(x, y)].render(c, g, (int)((x * 16) - camx), (int)((y * 16) - camy));
			}
		}
		
	}
	
	public int getTileId(int x, int y)
	{
		return tiles[x + y * width];
	}

	public void setTile(int id, int x, int y)
	{
		tiles[x + y * width] = (byte)id;
	}
}

package pixel101.src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Level
{
	byte[] tiles;
	int width, height;
	float camx, camy;
	
	public Level()
	{
		camx = camy = 0;
		width = height = 10;
		tiles = new byte[width * height];
		for (int i = 0; i < tiles.length; i++)
		{
			tiles[i] = Tile.stone.id;
		}
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
	}
	
	public int getTileId(int x, int y)
	{
		return tiles[x + y * width];
	}
}

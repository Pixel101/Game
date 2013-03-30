package pixel101.src;

import org.newdawn.slick.Graphics;

public class TileRenderer
{
	public TileRenderer()
	{
		
	}
	
	public void render(Graphics g, Level level)
	{
		for (int x = 0; x < level.width; x++)
		{
			for (int y = 0; y < level.height; y++)
			{
				g.drawImage(
							Tile.tileList[level.getTileId(x, y)].getTexture(),
							(x * 16) - level.camx,
							(y * 16) - level.camy
						);
			}
		}
	}
}

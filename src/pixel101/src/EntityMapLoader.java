package pixel101.src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class EntityMapLoader extends Entity
{
	String map;
	int spawn;
	
	public EntityMapLoader(Main main, float x, float y, String map, int spawn)
	{
		super(main, x, y);
		this.map = map;
		this.spawn = spawn;
		width = main.map.getTileWidth();
		height = main.map.getTileHeight();
		spriteSheet = null;
	}
	
	public void loadMap()
	{
		main.loadMap(map, spawn);
	}
	
	public void update(GameContainer c, int delta)
	{
		
	}
	
	public void render(GameContainer c, Graphics g)
	{
		
	}

}

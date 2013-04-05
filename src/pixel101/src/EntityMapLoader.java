package pixel101.src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class EntityMapLoader extends Entity
{
	String map;
	int spawn;
	
	public EntityMapLoader(Main main, float x, float y, String map, int spawn)
	{
		super(main, x + 3, y + 3);
		this.map = map;
		this.spawn = spawn;
		pos.setSize(main.map.getTileWidth() - 6, main.map.getTileHeight() - 6);
	}
	
	public void loadMap(GameContainer c)
	{
		main.loadMap(c, map, spawn);
	}
	
	public void update(GameContainer c, int delta)
	{
		
	}
	
	public void render(GameContainer c, Graphics g)
	{
		
	}

}

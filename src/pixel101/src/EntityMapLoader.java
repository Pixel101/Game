package pixel101.src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class EntityMapLoader extends Entity
{
	String map;
	int spawn;
	
	public EntityMapLoader(Game game, float x, float y, String map, int spawn)
	{
		super(game, x + 3, y + 3);
		this.map = map;
		this.spawn = spawn;
		pos.setSize(game.map.getTileWidth() - 6, game.map.getTileHeight() - 6);
	}
	
	public void loadMap(GameContainer c)
	{
		//game.loadMap(c, map, spawn);
		Main.map = map;
		Main.spawn = spawn;
		Main.loadMap = true;
	}
	
	public void update(GameContainer c, int delta)
	{
		
	}
	
	public void render(GameContainer c, Graphics g)
	{
		
	}

}

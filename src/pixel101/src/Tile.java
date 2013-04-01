package pixel101.src;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile
{
	public static Tile[] tileList = new Tile[255];
	public static final Tile stone = new Tile(0).setName("stone").setSolid(true);
	public static final Tile grass = new Tile(1).setName("grass");
	
	public byte id;
	String name;
	Image texture;
	boolean solid;
	
	public Tile(int id)
	{
		this.id = (byte)id;
		tileList[id] = this;
	}
	
	
	
	public Tile setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Tile setSolid(boolean solid)
	{
		this.solid = solid;
		return this;
	}
	
	
	public void init() throws SlickException
	{
		texture = new Image("res/tex/tiles/" + name + ".png");
	}
	
	public void update(GameContainer c, int delta)
	{
		
	}
	
	public void render(GameContainer c, Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, x + Main.ts, y + Main.ts, 0, 0, texture.getWidth(), texture.getHeight());
	}
	
}

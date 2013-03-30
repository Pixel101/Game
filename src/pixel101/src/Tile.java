package pixel101.src;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile
{
	public static Tile[] tileList = new Tile[255];
	public static final Tile stone = new Tile(0).setName("stone");
	
	public byte id;
	String name;
	Image texture;
	
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
	
	public void loadTexture() throws SlickException
	{
		texture = new Image("res/tex/tiles/" + name + ".png");
	}
	
	public Image getTexture()
	{
		return texture;
	}
	
}

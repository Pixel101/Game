package pixel101.src;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.GroupObject;
import org.newdawn.slick.tiled.ObjectGroup;
import org.newdawn.slick.tiled.TileSet;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.tiled.TiledMapPlus;

public class Main extends BasicGame
{
	public static Random r = new Random();
	TiledMapPlus map;
	boolean[][] solid;
	public float camx, camy;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public static Map nameToEntity;
	
	public Main(String title)
	{
		super(title);
		nameToEntity = new HashMap();
		nameToEntity.put("bat", EntityBat.class);
	}

	public void init(GameContainer c) throws SlickException
	{
		loadMap("map2", 0);
		//entities.add(new EntityPlayer(this, 30, 30));
		//entities.add(new EntityBat(this, 120, 120));
	}

	public void update(GameContainer c, int delta) throws SlickException
	{
		if (c.getInput().isKeyDown(Input.KEY_ESCAPE))
		{
			c.exit();
		}
		for (Entity e : entities) e.update(c, delta);
	}
	
	public void render(GameContainer c, Graphics g) throws SlickException
	{
		map.render(-(int)camx, -(int)camy);
		for (Entity e : entities) e.render(c, g);
		//g.drawString("" + entities.get(0).x, 30, 50);
		g.setColor(Color.blue);
		g.fillRect(map.getObjectX(0, 0) * map.getTileWidth() - camx, map.getObjectY(0, 0) * map.getTileHeight() - camy, map.getObjectWidth(0, 0) * map.getTileWidth(), map.getObjectHeight(0, 0) * map.getTileHeight());
	}
	
	
	public void loadMap(String mapName, int playerSpawn)
	{
		try
		{
			map = new TiledMapPlus("res/maps/" + mapName + ".tmx");
		}
		catch (SlickException e)
		{
			System.out.println("Failed to load world: \'" + mapName + "\'");
			e.printStackTrace();
			return;
		}
		solid = new boolean[map.getWidth()][map.getHeight()];
		for (int x = 0; x < map.getWidth(); x++)
		{
			for (int y = 0; y < map.getHeight(); y++)
			{
				int id = map.getTileId(x, y, 0);
				//System.out.println(id + ", " + x + ", " + y);
				//solid[x][y] = "true".equals(map.getTileProperty(66, "solid", "false"));
				solid[x][y] = id > 64 && id <= 128;
			}
		}
		//System.out.println(map.getObjectGroup("Object Layer 1").getObjects().size());
		Class c;
		String s1, s2;
		for (ObjectGroup group : map.getObjectGroups())
		{
			for (GroupObject obj : group.getObjects())
			{
				switch (obj.props.getProperty("type"))
				{
					case "entity":
					{
						s1 = obj.props.getProperty("name");
						if (s1 == null) continue;
						c = (Class)nameToEntity.get(s1);
						try
						{
							entities.add((Entity)c.getConstructor(new Class[] {Main.class, int.class, int.class}).newInstance(new Object[] {this, obj.x, obj.y}));
						}
						catch (InstantiationException | IllegalAccessException
								| IllegalArgumentException | InvocationTargetException
								| NoSuchMethodException | SecurityException e)
						{
							e.printStackTrace();
						}
						break;
					}
					case "player":
					{
						s1 = obj.props.getProperty("spawn");
						s2 = obj.props.getProperty("map");
						if (Integer.parseInt(s1) == playerSpawn)
						{
							//spawn player
							entities.add(new EntityPlayer(this, obj.x, obj.y));
						}
						if (s2 != null)
						{
							//add map loading point
						}
						break;
					}
					default:
					{
						
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		Main main = new Main("Game");
		try
		{
			AppGameContainer container = new AppGameContainer(main);
			container.setFullscreen(false);
			container.setDisplayMode(640, 480, false);
			container.setTargetFrameRate(60);
			container.setVSync(false);
			container.setIcon("res/tex/icon.png");
			container.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
}

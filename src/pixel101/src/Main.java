package pixel101.src;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.GroupObject;
import org.newdawn.slick.tiled.ObjectGroup;
import org.newdawn.slick.tiled.TiledMapPlus;

public class Main extends BasicGame
{
	public static Random r = new Random();
	TiledMapPlus map;
	boolean[][] solid;
	public float camx, camy;
	public int scale = 2;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public EntityPlayer player;
	public static Map<String, Class<?>> nameToEntity;
	
	public Main(String title)
	{
		super(title);
		nameToEntity = new HashMap<String, Class<?>>();
		nameToEntity.put("bat", EntityBat.class);
	}

	public void init(GameContainer c) throws SlickException
	{
		loadMap(c, "mapwobbly", 0);
		Class<?> cl;
		for (int i = 0; i < nameToEntity.size(); i++)
		{
			cl = (Class<?>)nameToEntity.values().toArray()[i];
			try
			{
				cl.getMethod("init", new Class<?>[] {GameContainer.class}).invoke(null, c);
			}
			catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException
					| SecurityException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void update(GameContainer c, int delta) throws SlickException
	{
		if (c.getInput().isKeyDown(Input.KEY_ESCAPE))
		{
			c.exit();
		}
		for (Entity e : entities) e.update(c, delta);
		player.update(c, delta);
	}
	
	public void render(GameContainer c, Graphics g) throws SlickException
	{
		g.scale(scale, scale);
		g.translate(-camx, -camy);
		map.render(0, 0);
		for (Entity e : entities) e.render(c, g);
		player.render(c, g);
	}
	
	
	public void loadMap(GameContainer cont, String mapName, int playerSpawn)
	{
		entities.clear();
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
		Class<?> c;
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
						c = (Class<?>)nameToEntity.get(s1);
						try
						{
							entities.add((Entity)c.getConstructor(new Class[] {Main.class, float.class, float.class}).newInstance(new Object[] {this, obj.x, obj.y}));
						}
						catch (InstantiationException | IllegalAccessException
								| IllegalArgumentException | InvocationTargetException
								| NoSuchMethodException | SecurityException e)
						{
							//e.printStackTrace();
							e.getCause().printStackTrace();
						}
						break;
					}
					case "player":
					{
						s1 = obj.props.getProperty("spawn");
						s2 = obj.props.getProperty("map");
						if (s1 != null)
						{
							if (Integer.parseInt(s1) == playerSpawn)
							{
								if (player == null) player = new EntityPlayer(this, obj.x, obj.y, cont);
								else player.pos.setLocation(obj.x, obj.y);
							}
						}
						if (s2 != null)
						{
							entities.add(new EntityMapLoader(this, obj.x, obj.y, s2.split(",")[0], Integer.parseInt(s2.split(",")[1])));
						}
						break;
					}
					default:
					{
						System.out.println("Object not coded yet: " + obj.toString());
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

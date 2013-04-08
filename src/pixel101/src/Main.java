package pixel101.src;

import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Main extends StateBasedGame
{
	public static Random r = new Random();
	
	GameState game;
	
	public static String map = "mapwobbly";
	public static int spawn = 0;
	public static boolean loadMap = false;
	
	public Main(String title)
	{
		super(title);
		Game.nameToEntity = new HashMap<String, Class<?>>();
		Game.nameToEntity.put("bat", EntityBat.class);
	}
	
	public void initStatesList(GameContainer container) throws SlickException
	{
		addState((game = new Game()));
	}

	public void update(GameContainer c, int delta) throws SlickException
	{
		if (loadMap)
		{
			enterState(game.getID(), new FadeOutTransition(), new FadeInTransition());
			//game.enter(c, this);
			loadMap = false;
		}
		super.update(c, delta);
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

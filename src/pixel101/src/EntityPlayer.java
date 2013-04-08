package pixel101.src;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class EntityPlayer extends Entity
{
	static SpriteSheet spriteSheet;
	static Animation[] anim_walk;
	byte direction, prevdirection;
	boolean walking;
	
	public EntityPlayer(Game game, float x, float y, GameContainer c)
	{
		super(game, x, y);
		pos.setSize(14, 14);
	}
	
	public static void init(GameContainer c)
	{
		try
		{
			spriteSheet = new SpriteSheet("res/tex/ent/player.png", 16, 24);
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
		int d = 100;
		anim_walk = new Animation[4];
		(anim_walk[3] = new Animation(spriteSheet, new int[] {0, 0, 1, 0, 0, 0, 2, 0}, new int[] {d, d, d, d})).setAutoUpdate(false);
		(anim_walk[1] = new Animation(spriteSheet, new int[] {0, 1, 1, 1, 0, 1, 2, 1}, new int[] {d, d, d, d})).setAutoUpdate(false);
		(anim_walk[0] = new Animation(spriteSheet, new int[] {0, 2, 1, 2, 0, 2, 2, 2}, new int[] {d, d, d, d})).setAutoUpdate(false);
		(anim_walk[2] = new Animation(spriteSheet, new int[] {0, 3, 1, 3, 0, 3, 2, 3}, new int[] {d, d, d, d})).setAutoUpdate(false);
	}
	
	public void update(GameContainer c, int delta)
	{
		handleMovement(c, delta);
		handleCollisions();
		moveCamera(c);
		for (Entity e : game.entities)
		{
			if (e instanceof EntityMapLoader)
			{
				if (e.pos.intersects(pos))
				{
					((EntityMapLoader)e).loadMap(c);
					return;
				}
			}
		}
	}
	
	ArrayList<Byte> dirs = new ArrayList<Byte>();
	void handleMovement(GameContainer c, int delta)
	{
		final float s = 0.06f;
		Input input = c.getInput();
		if (input.isKeyPressed(Input.KEY_W)) { dirs.add((byte) 1); anim_walk[1].setCurrentFrame(1); }
		if (input.isKeyPressed(Input.KEY_S)) { dirs.add((byte) 3); anim_walk[3].setCurrentFrame(1); }
		if (input.isKeyPressed(Input.KEY_A)) { dirs.add((byte) 2); anim_walk[2].setCurrentFrame(1); }
		if (input.isKeyPressed(Input.KEY_D)) { dirs.add((byte) 0); anim_walk[0].setCurrentFrame(1); }
		if (!input.isKeyDown(Input.KEY_W)) { dirs.remove(Byte.valueOf((byte)1)); anim_walk[1].setCurrentFrame(0); }
		if (!input.isKeyDown(Input.KEY_S)) { dirs.remove(Byte.valueOf((byte)3)); anim_walk[3].setCurrentFrame(0); }
		if (!input.isKeyDown(Input.KEY_A)) { dirs.remove(Byte.valueOf((byte)2)); anim_walk[2].setCurrentFrame(0); }
		if (!input.isKeyDown(Input.KEY_D)) { dirs.remove(Byte.valueOf((byte)0)); anim_walk[0].setCurrentFrame(0); }
		if (dirs.size() > 0)
		{
			direction = dirs.get(dirs.size() - 1);
			move(s * delta, direction);
			anim_walk[direction].update(delta);
		}
	}
	
	void moveCamera(GameContainer c)
	{
		game.camx = pos.getCenterX() - (c.getWidth() / 2) / game.scale;
		game.camy = pos.getCenterY() - (c.getHeight() / 2) / game.scale;
		if (game.camx < 0) game.camx = 0;
		else if (game.camx> game.map.getWidth() * game.map.getTileWidth() - c.getWidth() / game.scale)
			game.camx = game.map.getWidth() * game.map.getTileWidth() - c.getWidth() / game.scale;
		if (game.camy < 0) game.camy = 0;
		else if (game.camy > game.map.getHeight() * game.map.getTileHeight() - c.getHeight() / game.scale)
			game.camy = game.map.getHeight() * game.map.getTileHeight() - c.getHeight() / game.scale;
		
	}
	
	public void render(GameContainer c, Graphics g)
	{
		//anim.draw(x - (16 - width), y - (24 - height), 16, 24);
		anim_walk[direction].draw(pos.getCenterX() - anim_walk[direction].getWidth() / 2,
						pos.getMaxY() - anim_walk[direction].getHeight(),
						anim_walk[direction].getWidth(), anim_walk[direction].getHeight());
	}
}

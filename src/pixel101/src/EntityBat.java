package pixel101.src;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class EntityBat extends Entity
{
	float[] asdf = {0, 90, 180, 270};
	
	public EntityBat(Main main, float x, float y)
	{
		super(main, x, y);
		pos.setSize(8, 5);
	}
	
	public static void init(GameContainer c)
	{
		try
		{
			anim = new Animation(new SpriteSheet(new Image("res/tex/ent/bat.png"), 16, 10), 10);
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
		anim.addFrame(100, 0, 0);
		anim.addFrame(100, 1, 0);
		anim.setLooping(true);
		anim.setAutoUpdate(true);
	}
	
	public void update(GameContainer c, int delta)
	{
		float a = 0.04f, b = 0.015f;
		for (int i = 0; i < asdf.length; i++)
		{
			asdf[i] += (Main.r.nextInt(2000) - 1000) * delta * 0.001;
			if (asdf[i] < 0) asdf[i] += 360; else if (asdf[i] >= 360) asdf[i] -= 360;
			move((float)Math.cos(Math.toRadians(asdf[i])) * ((a - b) + Main.r.nextFloat() * b * 2) * delta,
				 (float)Math.sin(Math.toRadians(asdf[i])) * ((a - b) + Main.r.nextFloat() * b * 2) * delta);
		}
		super.handleCollisions();
	}
	
	public void render(GameContainer c, Graphics g)
	{
		super.render(c, g);
//		for (int i = 0; i < asdf.length; i++)
//		{
//			g.setColor(Color.white);
//			g.drawLine(pos.getCenterX(), pos.getCenterY(), pos.getCenterX() + (float)Math.cos(Math.toRadians(asdf[i])) * 15f, pos.getCenterY() + (float)Math.sin(Math.toRadians(asdf[i])) * 15f);
//		}
	}
}

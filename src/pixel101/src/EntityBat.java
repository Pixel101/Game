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
	Animation a;
	float[] asdf = {0, 0, 0, 90, 180, 270};
	
	public EntityBat(Level level, int x, int y) throws SlickException
	{
		super(level, x, y);
		a = new Animation(new SpriteSheet(new Image("res/tex/ent/bat.png"), 16, 10), 10);
		a.addFrame(100, 0, 0);
		a.addFrame(100, 1, 0);
		a.setLooping(true);
		a.setAutoUpdate(true);
	}
	
	public void update(GameContainer c, int delta)
	{
		float a = 0.02f, b = 0.01f;
		for (int i = 2; i < asdf.length; i++)
		{
			asdf[i] += (Main.r.nextInt(2000) - 1000) * delta * 0.001;
			if (asdf[i] < 0) asdf[i] += 360; else if (asdf[i] >= 360) asdf[i] -= 360;
			x += Math.cos(Math.toRadians(asdf[i])) * ((a - b) + Main.r.nextFloat() * b * 2) * delta;
			y += Math.sin(Math.toRadians(asdf[i])) * ((a - b) + Main.r.nextFloat() * b * 2) * delta;
		}
		if (Math.sqrt(Math.pow(Math.abs(-y + level.entities.get(0).y), 2) + Math.pow(Math.abs(-x + level.entities.get(0).x), 2)) > Main.ts * 3)
		{
			asdf[0] = (int)Math.toDegrees(Math.atan2(-y + level.entities.get(0).y, -x + level.entities.get(0).x));
			x += Math.cos(Math.toRadians(asdf[0])) * ((a - b) + Main.r.nextFloat() * b * 2) * delta;
			y += Math.sin(Math.toRadians(asdf[0])) * ((a - b) + Main.r.nextFloat() * b * 2) * delta;
		}
		if (Math.sqrt(Math.pow(Math.abs(-y + level.entities.get(0).y), 2) + Math.pow(Math.abs(-x + level.entities.get(0).x), 2)) < Main.ts * 5)
		{
			asdf[1] = (int)Math.toDegrees(Math.atan2(-y + level.entities.get(0).y, -x + level.entities.get(0).x));
			asdf[1] += (asdf[3] > 100) ? 90 : -90;
			if (asdf[1] < 0) asdf[1] += 360; else if (asdf[1] >= 360) asdf[1] -= 360;
			x += Math.cos(Math.toRadians(asdf[0])) * ((a - b) + Main.r.nextFloat() * b * 2) * delta;
			y += Math.sin(Math.toRadians(asdf[0])) * ((a - b) + Main.r.nextFloat() * b * 2) * delta;
		}
		super.handleCollisions();
	}
	
	public void render(GameContainer c, Graphics g)
	{
		//super.render(c, g);
		a.draw(x - level.camx, y - level.camy);
		Color[] cs = {Color.blue, Color.red, Color.white, Color.white, Color.white, Color.white};
		for (int i = 0; i < asdf.length; i++)
		{
			g.setColor(cs[i]);
			g.drawLine(x - level.camx, y - level.camy, (x - level.camx) + (float)Math.cos(Math.toRadians(asdf[i])) * 15f, (y - level.camy) + (float)Math.sin(Math.toRadians(asdf[i])) * 15f);
		}
	}
}

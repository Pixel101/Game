package pixel101.src;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Main extends Canvas
{
	private static final long serialVersionUID = 1L;
	public static Dimension screensize = new Dimension(800, 600);
	public boolean running = true;
	
	Input input;
	
	public Main()
	{
		input = new Input();
	}
	
	public void run()
	{
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();
		long now;

		//try {
			init();
		//} catch (IOException e1) {
		//	e1.printStackTrace();
		//}

		while (running) {
			now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			frames++;
			render();

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void init()
	{
		
	}
	
	public void tick()
	{
		
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		Graphics2D graphics = (Graphics2D)bs.getDrawGraphics();
		graphics.setBackground(Color.black);
		graphics.clearRect(0, 0, screensize.width, screensize.height);
		
		
		
		graphics.dispose();
		bs.show();
	}
	
	
	
	public static void main(String[] args)
	{
		Main g = new Main();
		g.setMinimumSize(screensize);
		g.setPreferredSize(screensize);
		g.setMaximumSize(screensize);
		
		JFrame f = new JFrame();
		f.setTitle("Hey look a game!");
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(g, BorderLayout.CENTER);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		g.run();
	}
}
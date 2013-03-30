package pixel101.src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Hashtable;

public class Input implements KeyListener
{
	static class Key
	{
		private boolean state, prev_state;
		
		public Key()
		{
			state = false;
			prev_state = false;
			keys.add(this);
		}
		
		public void tick()
		{
			prev_state = state;
		}
		
		public void setState(boolean state)
		{
			this.state = state;
		}
		
		public boolean getState()
		{
			return state;
		}
		
		public boolean getPressed()
		{
			return state && !prev_state;
		}
		
		public boolean getReleased()
		{
			return !state && prev_state;
		}
	}
	
	private static ArrayList<Key> keys = new ArrayList<Key>();
	
	public Key Up = new Key();
	public Key Down = new Key();
	public Key Left = new Key();
	public Key Right = new Key();
	public Key A = new Key();
	public Key B = new Key();
	
	public Input()
	{
		
	}
	
	public void tick()
	{
		for (Key k : keys)
		{
			k.tick();
		}
	}

	public void keyPressed(KeyEvent arg0)
	{
		set(arg0.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent arg0)
	{
		set(arg0.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent arg0)
	{
		
	}
	
	private void set(int key, boolean state)
	{
		switch (key)
		{
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
			{
				Up.setState(state);
				break;
			}
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
			{
				Down.setState(state);
				break;
			}
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
			{
				Left.setState(state);
				break;
			}
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
			{
				Right.setState(state);
				break;
			}
			case KeyEvent.VK_K:
			case KeyEvent.VK_Z:
			{
				A.setState(state);
				break;
			}
			case KeyEvent.VK_L:
			case KeyEvent.VK_X:
			{
				B.setState(state);
				break;
			}
		}
	}
}

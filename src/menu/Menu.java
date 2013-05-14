import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Menu {


  long lastFrame;
	int fps;
	long lastFPS;

	private Star[] _stars;

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(1080, 720));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); 
		getDelta(); 
		lastFPS = getTime(); 
		
		while (!Display.isCloseRequested()) {
			int delta = getDelta();

			update(delta);
			renderGL();

			Display.update();
			Display.sync(60); 
		}

		Display.destroy();
	}

	public void update(int delta) {
		for(Star x: _stars){
			x.update(delta);
		}
		updateFPS();
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1080, 0, 720, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		_stars = new Star[400];
		for(int i = 0; i < 400; i++){
			_stars[i] = new Star(1080,720,.3);
		}
		
	

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(0, 0);
		for(int i = 0; i <= 20; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
		    double angle = Math.PI * 2 * i / 20;
		    GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
		}
		GL11.glEnd();

		
		
	}

	public void renderGL() {
		  
		// Clear The Screen And The Depth Buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		// R,G,B,A Set The Color To Blue One Time Only
		GL11.glColor3f(0.5f, 1.0f, 0.5f);

		// draw quad
		for (Rectangle x: _stars){
			x.draw();
		}
	}

	public static void main(String[] argv) {
		Menu menu = new Menu();
		menu.start();
	}
}

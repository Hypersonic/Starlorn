import org.lwjgl.opengl.GL11;

public class Star extends Rectangle {

  private double _size;
	private double _svel;
	private boolean _show;
	private int _color;

	public Star(double displayx, double displayy, double s) {
		super(Math.random() * (displayx - s + 1), Math.random()
				* (displayy - s + 1), s, s);
		_show = false;
		_size = s;
		_svel = 0;
	}

	public void draw() {
		if (_show) {
			GL11.glColor3d(1, 1, 1);
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2d(getXcor(), getYcor());
			GL11.glVertex2d(getXcor() + _size, getYcor());
			GL11.glVertex2d(getXcor() + _size, getYcor() + _size);
			GL11.glVertex2d(getXcor(), getYcor() + _size);
			GL11.glEnd();
		}
	}

	public double setSize(double s) {
		double temp = _size;
		_size = s;
		return _size;
	}

	public void update(int delta) {
		super.update(delta);
		double dist = Math.sqrt(Math.abs(540 - getXcor()) * Math.abs(540 - getXcor())
				+ Math.abs(360 - getYcor()) * Math.abs(360 - getYcor()));
		
		setXvel((getXcor() - 540) / 1);
		setYvel((getYcor() - 360) / 1);
		
		_size += _svel;
		_svel = dist * dist / 700000;

		if (getXcor() <= 0 || getYcor() <= 0 || getXcor() >= 1080
				|| getYcor() >= 720) {
			setXcor(Math.random() * 1080);
			setYcor(Math.random() * 720);
			setXvel(0);
			setYvel(0);
			_size = 1;
			_show = true;
		}

	}

}

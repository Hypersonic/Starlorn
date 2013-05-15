import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Button extends Rectangle {

	private boolean _pressed;
	private boolean _hover;

	public Button(double x, double y, double h, double w) {
		super(x, y, h, w);
		_pressed = false;
	}

	public boolean isPressed() {
		return _pressed;
	}

	public boolean setPressed(boolean p) {
		boolean temp = _pressed;
		_pressed = p;
		return temp;
	}

	public boolean isHover() {
		return _hover;
	}

	public boolean setHover(boolean p) {
		boolean temp = _hover;
		_hover = p;
		return temp;
	}

	public void update(int delta) {
		super.update(delta);
		if (Mouse.getX() >= getXcor() && Mouse.getX() <= getXcor() + getWidth()
				&& Mouse.getY() >= getYcor()
				&& Mouse.getY() <= getYcor() + getHeight()) {
			_hover = true;
		}
		else _hover = false;
		
		if (Mouse.isButtonDown(0) && _hover) {
			_pressed = true;
		}

		else
			_pressed = false;
	}
}

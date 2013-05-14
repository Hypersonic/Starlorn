import org.lwjgl.opengl.GL11;

public class Rectangle {
	
	private double _xcor;
	private double _ycor;
	private double _height;
	private double _width;
	
	private double _xvel;
	private double _yvel;
	private double _xacc;
	private double _yacc;
	
	public Rectangle(double x, double y, double h, double w) {
		GL11.glColor3d(1,1,1);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(x	 , y	);
		GL11.glVertex2d(x + w, y	);
		GL11.glVertex2d(x + w, y + h);
		GL11.glVertex2d(x	 , y + h);
		GL11.glEnd();
		
		_xcor = x;
		_ycor = y;
		_height = h;
		_width = w;
	}
	
	public void draw(){
		GL11.glColor3d(1,1,1);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(_xcor	      , _ycor	);
		GL11.glVertex2d(_xcor + _width, _ycor	);
		GL11.glVertex2d(_xcor + _width, _ycor + _height);
		GL11.glVertex2d(_xcor		  , _ycor + _height);
		GL11.glEnd();
	}
	
	public double getWidth(){return _width;}
	public double setWidth(double Width){
		double temp = _width;
		_width = Width;
		return temp;}
	public double getHeight(){return _height;}
	public double setHeight(double height){
		double temp = _height;
		_height = height;
		return temp;}
	
	public double getXcor(){return _xcor;}
	public double setXcor(double xcor){
		double temp = _xcor;
		_xcor = xcor;
		return temp;}
	public double getYcor(){return _ycor;}
	public double setYcor(double ycor){
		double temp = _ycor;
		_ycor = ycor;
		return temp;}
	
	public double getXvel(){return _xvel;}
	public double setXvel(double xvel){
		double temp = _xvel;
		_xvel = xvel;
		return temp;}
	public double getYvel(){return _yvel;}
	public double setYvel(double yvel){
		double temp = _yvel;
		_yvel = yvel;
		return temp;}
	
	public double getXacc(){return _xacc;}
	public double setXacc(double xacc){
		double temp = _xacc;
		_xacc = xacc;
		return temp;}
	public double getYacc(){return _yacc;}
	public double setYacc(double yacc){
		double temp = _yacc;
		_yacc = yacc;
		return temp;}
	
	public void update(int delta){
		_xvel += _xacc * (double) delta / 1000;
		_yvel += _yacc * (double) delta / 1000;
		
		_xcor += _xvel * (double) delta / 1000;
		_ycor += _yvel * (double) delta / 1000;
	}
}

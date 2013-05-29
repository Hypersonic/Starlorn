package edu.stuy.starlorn.menu;

import java.awt.Graphics;

public class Rectangle {

    private double xcor, ycor, width, height, xvel, yvel, xacc, yacc;

    public Rectangle(double x, double y, double w, double h) {
        xcor = x;
        ycor = y;
        width = w;
        height = h;
        xvel = yvel = xacc = yacc = 0;
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(_image, 0, 0, width, height, null);
    }

    public double getWidth() {
        return width;
    }

    public double setWidth(double w) {
        double temp = width;
        width = w;
        return temp;
    }

    public double getHeight() {
        return height;
    }

    public double setHeight(double h) {
        double temp = height;
        height = h;
        return temp;
    }

    public double getXcor() {
        return xcor;
    }

    public double setXcor(double cor) {
        double temp = xcor;
        xcor = cor;
        return temp;
    }

    public double getYcor() {
        return ycor;
    }

    public double setYcor(double cor) {
        double temp = ycor;
        ycor = cor;
        return temp;
    }

    public double getXvel() {
        return xvel;
    }

    public double setXvel(double vel) {
        double temp = xvel;
        xvel = vel;
        return temp;
    }

    public double getYvel() {
        return yvel;
    }

    public double setYvel(double vel) {
        double temp = yvel;
        yvel = vel;
        return temp;
    }

    public double getXacc() {
        return xacc;
    }

    public double setXacc(double acc) {
        double temp = xacc;
        xacc = acc;
        return temp;
    }

    public double getYacc() {
        return yacc;
    }

    public double setYacc(double acc) {
        double temp = yacc;
        yacc = acc;
        return temp;
    }

    public void update(int delta) {
        xvel += xacc * (double) delta / 1000;
        yvel += yacc * (double) delta / 1000;
        xcor += xvel * (double) delta / 1000;
        ycor += yvel * (double) delta / 1000;
    }
}

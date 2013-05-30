package edu.stuy.starlorn.util;

import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Preferences {

	protected static HashMap<String, Object> _data;

	public Preferences() {
		_data = new HashMap<String, Object>();
		init();
	}

	public static ArrayList loadPreferences() throws IOException {
		String file_name = "res/data.txt";
		FileReader fr = new FileReader(file_name);
		BufferedReader br = new BufferedReader(fr);
		ArrayList aList = new ArrayList(_data.size());

		String line = null;
		while ((line = br.readLine()) != null) {
			aList.add(line);// ugh damnit victor you deleted the cutting of the
							// string god damn it
							// -agnok
		}

		br.close();
		fr.close();

		return aList;
	}

	public static void writePreferences() throws IOException {
		File yay = new File("res/data.txt");
		if (yay.exists()) {
			yay.delete();
		}
		yay.createNewFile();
		PrintWriter pw = new PrintWriter(yay);
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		try {
			fWriter = new FileWriter("res/data.txt");
			writer = new BufferedWriter(fWriter);
			Set<String> data = _data.keySet();
			for (String x : data) {
				writer.write(x);
				writer.write(": ");
				writer.write((Integer) _data.get(x));
				writer.newLine();
				writer.flush();
				pw.print("ughh");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Error in the code");
		}

		
	}

	public static void init() {
		_data = new HashMap<String, Object>();
		_data.put("upKey", 0);
		_data.put("downKey", 0);
		_data.put("leftKey", 0);
		_data.put("rightKey", 0);

		_data.put("screenWidth", 800);
		_data.put("screenHeight", 640);
		_data.put("screenRate", 60);
		_data.put("fullScreen", 0);
	}

	public HashMap getData() {
		return _data;
	}

	public HashMap setData(HashMap a) {
		HashMap temp = _data;
		_data = a;
		return temp;
	}

	/*
	 * protected static int _upKey; protected static int _downKey; protected
	 * static int _leftKey; protected static int _rightKey;
	 * 
	 * protected static int _screenWidth; protected static int _screenHeight;
	 * 
	 * protected static int _screenRate;
	 * 
	 * protected static boolean _fullScreen;
	 * 
	 * public static int getUpKey(){return _upKey;} public static int
	 * getDownKey(){return _downKey;} public static int getLeftKey(){return
	 * _leftKey;} public static int getRightKey(){return _rightKey;}
	 * 
	 * public static void setUpKey(int a){_upKey = a;} public static void
	 * setDownKey(int a){_downKey = a;} public static void setLeftKey(int
	 * a){_leftKey = a;} public static void setRightKey(int a){_rightKey = a;}
	 * 
	 * public static int getScreenWidth(){return _screenWidth;} public static
	 * int getScreenHeight(){return _screenHeight;} public static int
	 * getScreenRate(){return _screenRate;}
	 * 
	 * public static void setScreenWidth(int a){_screenWidth = a;} public static
	 * void setScreenHeight(int a){_screenHeight = a;} public static void
	 * setScreenRate(int a){_screenRate = a;}
	 * 
	 * public static boolean getFullScreen(){return _fullScreen;} public static
	 * void setFullScreen(boolean a){_fullScreen = a;}
	 */

}

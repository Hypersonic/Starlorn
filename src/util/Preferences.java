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

	protected HashMap<String, Integer> _data;

	public Preferences() {
		_data = new HashMap<String, Integer>();
	}

	public void loadPreferences() {
        try {
            String filename = "preferences.txt";
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while (br.ready()) {
                String[] data = br.readLine().split(" : ");
                _data.put(data[0], Integer.parseInt(data[1]));
            }

            br.close();

        } catch (IOException e) {
            System.out.println("You don't seem to have a preferences file. I'll make one for you, then");  
            loadDefaultPreferences();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void writePreferences() {
        String filename = "preferences.txt";
        try {
            BufferedWriter b = new BufferedWriter(new FileWriter(filename));
            for (String key : _data.keySet()) {
                b.write(key);
                b.write(" : ");
                b.write(_data.get(key));
                b.newLine();
            }
            b.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
    
    public int getValue(String key) {
        return _data.get(key);
    }

	public void loadDefaultPreferences() {
		_data.put("upKey", 0);
		_data.put("downKey", 0);
		_data.put("leftKey", 0);
		_data.put("rightKey", 0);
		_data.put("shootKey", 0);

		_data.put("screenWidth", 800);
		_data.put("screenHeight", 640);
		_data.put("fullScreen", 0);
	}


}

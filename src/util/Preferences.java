package edu.stuy.starlorn.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.awt.event.KeyEvent;

public class Preferences {

    private static HashMap<String, Integer> _data;

    public static void load(int width, int height) {
        _data = new HashMap<String, Integer>();
        try {
            String filename = "preferences.txt";
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while (br.ready()) {
                String[] data = br.readLine().split(" : ");
                _data.put(data[0], Integer.parseInt(data[1]));
            }

            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("You don't seem to have a preferences file. I'll make one for you, then.");
            loadDefault(width, height);
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        String filename = "preferences.txt";
        try {
            BufferedWriter b = new BufferedWriter(new FileWriter(filename));
            for (String key : _data.keySet()) {
                b.write(key);
                b.write(" : ");
                b.write("" + _data.get(key));
                b.newLine();
            }
            b.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getValue(String key) {
        return _data.get(key);
    }

    private static void loadDefault(int width, int height) {
        _data.put("upKey", KeyEvent.VK_W);
        _data.put("downKey", KeyEvent.VK_S);
        _data.put("leftKey", KeyEvent.VK_A);
        _data.put("rightKey", KeyEvent.VK_D);
        _data.put("shootKey", KeyEvent.VK_SPACE);
        _data.put("pauseKey", KeyEvent.VK_P);

        _data.put("screenWidth", width);
        _data.put("screenHeight", height);
        _data.put("fullScreen", 0);
    }


}

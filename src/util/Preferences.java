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

    public static void put(String key, Integer data){
        if (_data == null) load(800, 600);
        _data.put(key,data);
    }

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
            loadDefault(width, height, false); // load in any missing options
            save();

        } catch (FileNotFoundException e) {
            System.out.println("You don't seem to have a preferences file. I'll make one for you, then.");
            loadDefault(width, height, true);
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
        if (_data == null) load(800, 600);
        return _data.get(key);
    }

    private static void loadDefault(int width, int height, boolean force) {
        if (force || !_data.containsKey("upKey")) _data.put("upKey", KeyEvent.VK_W);
        if (force || !_data.containsKey("downKey")) _data.put("downKey", KeyEvent.VK_S);
        if (force || !_data.containsKey("leftKey")) _data.put("leftKey", KeyEvent.VK_A);
        if (force || !_data.containsKey("rightKey")) _data.put("rightKey", KeyEvent.VK_D);
        if (force || !_data.containsKey("shootKey")) _data.put("shootKey", KeyEvent.VK_SPACE);
        if (force || !_data.containsKey("pauseKey")) _data.put("pauseKey", KeyEvent.VK_P);

        if (force || !_data.containsKey("fancyGraphics")) _data.put("fancyGraphics", 1);
        if (force || !_data.containsKey("devMode")) _data.put("devMode", 0);
        if (force || true /*!_data.containsKey("screenWidth")*/) _data.put("screenWidth", width);
        if (force || true /*!_data.containsKey("screenHeight")*/) _data.put("screenHeight", height);
        if (force || !_data.containsKey("fullscreen")) _data.put("fullScreen", 0);
    }


}

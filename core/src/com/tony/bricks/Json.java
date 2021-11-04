package com.tony.bricks;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

public class Json {
    public static void main(String[] args) {

        try {
            FileInputStream stream = new FileInputStream(new File("scaledPhysicsData_ver1.json"));
            JsonValue root = new JsonReader().parse(stream);
            root = root.child.child;
            int size = root.size;
            while(root.child()!=null){
                JsonValue child = root;
                String s = child.toString();
                int i = s.indexOf(":");
                String substring = s.substring(0, i);
                System.out.println(substring);
                root = child.child.next;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
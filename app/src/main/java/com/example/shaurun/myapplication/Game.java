package com.example.shaurun.myapplication;

import android.app.Activity;

import com.example.shaurun.engine.Phisics;
import com.example.shaurun.playObjects.BreakableWall;
import com.example.shaurun.playObjects.Government;
import com.example.shaurun.playObjects.PlayerTank;
import com.example.shaurun.visualization.GameObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Shaurun on 13.03.2016.
 */
public class Game {
    public static Game game;
    private List<GameObject> objects;
    private List<GameObject> dinamic_remove;
    private List<GameObject> dinamic_add;
    private PlayerTank player;

    private final static int FIELD_SIZE = 13;
    public float FIELD_WIDTH_PIXELS;
    public float CELL_SIZE_PIXELS;
    private float width;
    private float height;

    public Game(){
        objects = new LinkedList<>();
        dinamic_remove = new ArrayList<>();
        dinamic_add = new ArrayList<>();

        //generateTestLevel();
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public void init(float width, float heigth){
        this.width = width;
        this.height = heigth;
        FIELD_WIDTH_PIXELS = width > heigth ? heigth : width;
        CELL_SIZE_PIXELS = FIELD_WIDTH_PIXELS/FIELD_SIZE;

        if (player == null){
            loadLevel(1);
            EnemyController.init();
        } else {
            for (GameObject go : objects){
                go.setSizeX(CELL_SIZE_PIXELS);
                go.setSizeY(CELL_SIZE_PIXELS);
            }
        }
    }

    public void init(){
        if (player == null){
            loadLevel(1);
            EnemyController.init();
        } else {
            for (GameObject go : objects){
                go.setSizeX(PlaygroundDimensions.getCellSize());
                go.setSizeY(PlaygroundDimensions.getCellSize());
            }
        }
    }

    public void loadLevel(int level){
        String file = "res/raw/level" + level;
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);

        InputStreamReader inputreader = new InputStreamReader(in);
        BufferedReader buffreader = new BufferedReader(inputreader);

        char[][] chars = new char[13][13];

        int i, j;
        i = j = 0;
        try {
            int c;
            while ((c = buffreader.read()) != -1) {
                System.out.println((char) c);
                if((char)c == ' ' || (char)c == '\n'){
                    continue;
                }
                if(i>=13){
                    i=0;
                    j++;
                }
                chars[i][j]= ((char) c);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffreader != null) {
                try {
                    buffreader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        for(i = 0; i < 13; i++){
            for (j = 0; j < 13; j++){
                switch (chars[i][j]){
                    case 'E': break;
                    case 'P':
                        if(player == null){
                            player = new PlayerTank(PlaygroundDimensions.getX(i),
                                    PlaygroundDimensions.getY(12-j),
                                    PlaygroundDimensions.getCellSize()-2,
                                    PlaygroundDimensions.getCellSize()-2);
                        }
                        objects.add(player); break;
                    case '1': objects.add(new BreakableWall(PlaygroundDimensions.getX(i),
                            PlaygroundDimensions.getY(12-j),
                            PlaygroundDimensions.getCellSize(),
                            PlaygroundDimensions.getCellSize())); break;
                    case 'G':  objects.add(new Government(PlaygroundDimensions.getX(i),
                            PlaygroundDimensions.getY(12-j),
                            PlaygroundDimensions.getCellSize(),
                            PlaygroundDimensions.getCellSize())); break;
                }
            }
        }
    }

    //TODO: review
    /*public void getInput(){
        player.getInput();
    }*/

    //TODO:think about unite update and render
    public void update(){
        //TODO: dinamic remove static method instead of getRemove()?
        for (GameObject go : objects) {
            if (!go.getRemove()) {
                go.update();
            } else {
                dinamic_remove.add(go);
            }
        }

        for (GameObject go: dinamic_remove){
            objects.remove(go);
        }

        for (GameObject go: dinamic_add){
            objects.add(go);
        }

        dinamic_remove.clear();
        dinamic_add.clear();

        //TODO: think where to put this method
        EnemyController.controlEnemies();
    }


    //TODO: static gl?
    public void render(GL10 gl){
        for (GameObject go : objects){
            go.render(gl);
        }
    }

    public void dinamicAdd(GameObject go){
        dinamic_add.add(go);
    }

    //TODO: remove player get
    public PlayerTank getPlayer(){
        return player;
    }

    public List<GameObject> getObjects(){
        return objects;
    }

    public static List<GameObject> rectangleCollide(GameObject gameObject) {
        ArrayList<GameObject> result = new ArrayList<>();

        for (GameObject go : game.getObjects()){
            if(go.equals(gameObject)){
                continue;
            }

            if(Phisics.checkCollision(gameObject, go)){
                result.add(go);
            }
        }

        return result;
    }

    public static boolean goesOutField(GameObject gameObject){
        return Phisics.checkMovingOutRegion(gameObject,
                PlaygroundDimensions.getWidth(), PlaygroundDimensions.getHeight(),
                PlaygroundDimensions.getLeftX(), PlaygroundDimensions.getBottomY());
    }

    public void over() {
        Activity toRemove = null;
        for (Activity activity : Activities.activities){
            if (activity instanceof AndroidMainActivity){
                activity.finish();
                toRemove = activity;
                break;
            }
        }

        Activities.activities.remove(toRemove);
    }
}

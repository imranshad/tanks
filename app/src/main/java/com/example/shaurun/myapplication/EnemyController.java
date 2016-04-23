package com.example.shaurun.myapplication;

import com.example.shaurun.playObjects.EnemyTank;
import com.example.shaurun.playObjects.Movement;
import com.example.shaurun.visualization.GameObject;

import java.util.Random;

/**
 * Created by Shaurun on 20.03.2016.
 */
public class EnemyController {
    private static Random rand = new Random();
    static int enemiesOnField;

    private static float[] enemies = new float[3];
    private static int enemyRespawnIndex = 0;

    public static void init(){
        enemies[0] = 0.5f*PlaygroundDimensions.getCellSize();
        enemies[1] = 7*PlaygroundDimensions.getCellSize()+0.5f*PlaygroundDimensions.getCellSize();
        enemies[2] = 12*PlaygroundDimensions.getCellSize()+0.5f*PlaygroundDimensions.getCellSize();

        for(int i = 0; i < 3; i++){
            enemies[i] += PlaygroundDimensions.getLeftX();

            Game.game.dinamicAdd(new EnemyTank(enemies[i], PlaygroundDimensions.getBottomY()+
                    12*PlaygroundDimensions.getCellSize()+0.5f*PlaygroundDimensions.getCellSize(),
                    PlaygroundDimensions.getCellSize(), PlaygroundDimensions.getCellSize()));
        }
    }

    public static void controlEnemies(){
        enemiesOnField = 0;
        for (GameObject go : Game.game.getObjects()){
            if (go instanceof EnemyTank){
                controlEnemy((EnemyTank) go);
                enemiesOnField++;
            }
        }

        if(enemiesOnField < 3){
            respawn();
        }
    }

    private static void controlEnemy(EnemyTank enemyTank){
        if(rand.nextInt(100) > 90) {
            enemyTank.setMovement(Movement.values()[rand.nextInt(Movement.values().length)]);

        }
        enemyTank.fire();
    }

    public static void respawn(){
        Game.game.dinamicAdd(new EnemyTank(enemies[enemyRespawnIndex]+1,
                12*PlaygroundDimensions.getCellSize()+0.5f*PlaygroundDimensions.getCellSize()+1,
                PlaygroundDimensions.getCellSize()-2, PlaygroundDimensions.getCellSize()-2));
        if(enemyRespawnIndex >= 2){
            enemyRespawnIndex = 0;
        } else{
            enemyRespawnIndex++;
        }
    }
}

package com.Philco;

/**
 * Created by PhillipsDaramola on 30/06/2017.
 */
public class BeeHive {
    private int queenNumber;
    private int workerNumber;
    private int warriorNumber;

    public BeeHive(int queenNumber, int workerNumber, int warriorNumber) {
        this.queenNumber = queenNumber;
        this.workerNumber = workerNumber;
        this.warriorNumber = warriorNumber;
    }

    // First element = Healthpoints
    // Second element = Damages
    public int[] queen () {

        Bee queen = new Bee(50,1);

        int[] queenStats = {this.queenNumber * queen.getHealthPoint(), this.queenNumber * queen.getDamages()};
        return queenStats;
    }

    public int[] worker () {

        Bee queen = new Bee(5,4);

        int[] workerStats = {this.workerNumber * queen.getHealthPoint(), this.workerNumber * queen.getDamages()};
        return workerStats;
    }

    public int[] warrior () {

        Bee queen = new Bee(10,7);

        int[] warriorStats = {this.warriorNumber * queen.getHealthPoint(), this.warriorNumber * queen.getDamages()};
        return warriorStats;
    }

}

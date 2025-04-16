package simplerace.x;

import simplerace.*;
import java.util.HashMap;
import java.util.Map;

public class AIController implements Controller, Constants {
    private final double angleThreshold;
    private final double maxSpeedAdd;
    private final double maxSpeedPrams;
    private final double maxSpeedcoefficient;
    private final double minSpeedAdd;
    private final double minSpeedPrams;
    private final double minSpeedcoefficient;


    private static final Map<String, Integer> commandMap = createCommandMap();

    public AIController(double[] genes) {
        this.angleThreshold = genes[0];
        this.maxSpeedAdd = genes[1];
        this.maxSpeedPrams = genes[2];
        this.maxSpeedcoefficient = genes[3];
        this.minSpeedAdd = genes[4];
        this.minSpeedPrams = genes[5];
        this.minSpeedcoefficient = genes[6];
    }

    public AIController() {
        this(new double[] {
            1.2836665031146275, 3.702209323899995, 3.962904078280526, 3.369374043946777, 3.9587972942198943, 3.8303847952224532, 3.6014974915745492
        });
    }

    private static Map<String, Integer> createCommandMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("brake", 1);
        map.put("left", 3);
        map.put("keep", 4);
        map.put("right", 5);
        map.put("leftAccelerate", 6);
        map.put("Accelerate", 7);
        map.put("rightAccelerate", 8);
        return map;
    }

    @Override
    public void reset() {
    }

    @Override
    public int control(SensorModel inputs) {
        double angle = inputs.getAngleToNextWaypoint();
        double nextAngle = inputs.getAngleToNextNextWaypoint();
        double speed = inputs.getSpeed();
        double dist = inputs.getDistanceToNextWaypoint();

        double maxSpeed = maxSpeedcoefficient*Math.pow(maxSpeedPrams, dist) + maxSpeedAdd;
        double minSpeed = minSpeedcoefficient*Math.pow(minSpeedPrams, dist) + minSpeedAdd;

        String command = "Accelerate";

        if (Math.abs(angle) < angleThreshold){
            if (speed < maxSpeed) {
                command = "Accelerate";
            } else {
                command = "keep";
            }
        }else{
            if (angle > 0) {
                if (Math.abs(angle) < angleThreshold + 5) {
                    command = "leftAccelerate";
                } else {
                    command = "left";
                }
            } else {
                if (Math.abs(angle) < angleThreshold + 5) {
                    command = "rightAccelerate";
                } else {
                    command = "right";
                }
            }
        }

        return commandMap.getOrDefault(command, 7);
    }
}

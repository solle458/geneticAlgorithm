package simplerace.x;

import simplerace.*;

public class GAUtils {
    public static double evaluate(double[] genes) {
        Controller controller = new AIController(genes);
        return Evaluator.evaluateSolo(controller, new BasicTrack(), false);
    }
}

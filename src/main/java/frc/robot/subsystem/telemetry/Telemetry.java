/**
 * two lidars
 * one boolean to see if the robot is parallel at a certain distance
 * 
 * boolean isSquare(double distance)
 * 
 */
 


package frc.robot.subsystem.telemetry;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.PortMan;
import java.util.logging.Logger;






public class Telemetry extends SubsystemBase{

    /*
    private LidarPWM frontLidar, rearLidar;
    private double frontLidarDistance, rearLidarDistance;
    private static Logger logger = Logger.getLogger(Telemetry.class.getName());

    private double betweenLidarDistance = 0;
    private double lidarTolerance = 5;

    public Telemetry() {


    }

    
    public double isSquare(double frontLidarDistance, double rearLidarDistance)
    {
        if (Math.abs(frontLidarDistance - rearLidarDistance) <= lidarTolerance)
        {
            double angleError = Math.atan((Math.max(frontLidarDistance, rearLidarDistance) - Math.min(frontLidarDistance, rearLidarDistance))/betweenLidarDistance);
            return angleError;
        }
        else {
            return 0;
        }

    }
    
    public void init(PortMan portMan) {
        logger.entering(Telemetry.class.getName(), "init()");

        frontLidar = new LidarPWM(portMan.acquirePort(PortMan.can_19_label, "Telemetry.lidar"));
        rearLidar = new LidarPWM(portMan.acquirePort(PortMan.can_20_label, "Telemetry.lidar"));

        logger.exiting(Telemetry.class.getName(), "init()");
    }
    */

}

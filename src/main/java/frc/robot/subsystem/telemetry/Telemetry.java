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
    private double correction = 0;

    public Telemetry() {


    }

    
    public boolean isSquare(double targetDistance)
    {
        if (Math.abs(frontLidarDistance-targetDistance) > lidarTolerance || Math.abs(rearLidarDistance-targetDistance) > lidarTolerence || Math.abs(frontLidarDistance-rearLidarDistance) > lidarTolerance)
        {
            angleError = Math.atan((Math.max(frontLidarDistance, rearLidarDistance) - Math.min(frontLidarDistance, rearLidarDistance))/betweenLidarDistance);

            if (frontLidarDistance/Math.cos(angleError)-target > rearLidarDistance/Math.cos(angleError)-target)
            {
                //move front wheels by angleError
            }
            else
            {
                //move back wheels by angleError
            }
            while(Math.abs(frontLidarDistance-rearLidarDistance) > lidarTolerance)
            {
                //move by correction
            }
            
            distanceError = Math.abs(frontLidarDistance - targetDistance);

            if (distanceError > lidarTolerance)
            {
                if (frontLidarDistance > targetDistance)
                {
                    //move left distanceError
                }
                else
                {
                    //move right distanceError
                }
            }
        }
        return true;
    }
    
    public void init(PortMan portMan) {
        logger.entering(Telemetry.class.getName(), "init()");

        frontLidar = new LidarPWM(portMan.acquirePort(PortMan.can_19_label, "Telemetry.lidar"));
        rearLidar = new LidarPWM(portMan.acquirePort(PortMan.can_20_label, "Telemetry.lidar"));

        logger.exiting(Telemetry.class.getName(), "init()");
    }
    */

}

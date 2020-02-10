package frc.robot.subsystem.climber;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.PortMan;

public class Climber extends SubsystemBase {
    private static Logger logger = Logger.getLogger(Climber.class.getName());
    private TalonSRX motor;
    
    public void init(PortMan portMan) throws Exception {
        logger.info("init");
        motor = new TalonSRX(portMan.acquirePort(PortMan.can_47_label, "Climber.motor"));

      motor.enableCurrentLimit(true);
      motor.configPeakCurrentLimit(30);
      motor.configContinuousCurrentLimit(20);
      motor.configPeakCurrentDuration(400);
      motor.configAllowableClosedloopError(0, 5);
      motor.setSelectedSensorPosition(0, 0, 0);

      motor.config_kP(0, .3, 0);
      motor.config_kI(0, 0, 0);
      motor.config_kD(0, .1, 0);
      motor.config_kF(0, 0, 0);

      motor.configMotionCruiseVelocity(4500);
      motor.configMotionAcceleration(4096);
    }
    
    public void extend(){
        logger.info("extend");
        
    }

    public void retract(){
        logger.info("retract");
    }
}

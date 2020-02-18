package frc.robot.subsystem.climber;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.PortMan;

public class Climber extends SubsystemBase {
    private static Logger logger = Logger.getLogger(Climber.class.getName());
    private TalonSRX motor;
    private DigitalInput hardLowLimit;
    private DigitalInput optimalLimit;
    private DigitalInput hardTopLimit;

    private double moveRoboUpSpeed;
    private double moveRoboDownSpeed;
    private double moveCustomUpSpeed;
    private double moveCustomDownSpeed;
    
    public void init(PortMan portMan) throws Exception {
    logger.info("init");
    motor = new TalonSRX(portMan.acquirePort(PortMan.can_30_label, "Climber.motor"));
    hardLowLimit = new DigitalInput(portMan.acquirePort(PortMan.digital2_label, "Climber.HardLowLimit"));
    optimalLimit = new DigitalInput(portMan.acquirePort(PortMan.digital3_label, "Climber.MinimumLimit"));
    hardTopLimit = new DigitalInput(portMan.acquirePort(PortMan.digital4_label, "Climber.HardHighLimit"));


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

      moveRoboUpSpeed = .4;
      moveRoboDownSpeed = -.3;
      moveCustomUpSpeed = .4;
      moveCustomDownSpeed = -.3;
    }
    
    public void extend(){
        logger.info("extend");
        if(getOptimalLimit() == true)
        {
            motor.set(ControlMode.PercentOutput, moveRoboUpSpeed);
        }
        else{
            logger.info("extend off");
            motor.set(ControlMode.PercentOutput, 0.0);
        }  
    }

    public void controlForward(){
        logger.info("CONTROLFORWARD");
        if(getHardHighLimit() == true)
            motor.set(ControlMode.PercentOutput, moveCustomUpSpeed);
        else{
            logger.info("controlBack");
            motor.set(ControlMode.PercentOutput, 0.0);
        }

    }
    public void controlBack(){
        if(getHardLowLimit() == true)
            motor.set(ControlMode.PercentOutput, moveCustomDownSpeed);
        else{
            logger.info("controlBack");
            motor.set(ControlMode.PercentOutput, 0.0);
        }
    }

    public void retract(){
        logger.info("retract");
        if(getHardLowLimit() == true)
            motor.set(ControlMode.PercentOutput, moveRoboDownSpeed);
        else   
            motor.set(ControlMode.PercentOutput, 0.0);
    }

    public boolean getHardLowLimit(){
        return hardLowLimit.get();
    }

    public boolean getOptimalLimit(){
        return optimalLimit.get();
    }

    public boolean getHardHighLimit(){
        return hardTopLimit.get();
    }
    public void setRoboMoveUpSpeed(double a){
        moveRoboUpSpeed = a;
    }
    public void setRoboMoveDownSpeed(double a){
        moveRoboDownSpeed = a;
    }

    public void setCustomMoveUpSpeed(double a){
        moveCustomUpSpeed = a;
    }
    public void setCustomMoveDownSpeed(double a){
        moveCustomDownSpeed = a;
    }
}

package frc.robot.subsystem.climber;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMTalonSRX;
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
      motor.configContinuousCurrentLimit(30);
      motor.configPeakCurrentDuration(400);

      moveRoboUpSpeed = .8;
      moveRoboDownSpeed = -.3;
      moveCustomUpSpeed = .8;
      moveCustomDownSpeed = -.3;
    }
    
    public void extend(){
        logger.info("extend");
        logger.info("optimalLimit [" + getOptimalLimit() + "]");
        if(getOptimalLimit() == true)
        {
            motor.set(ControlMode.PercentOutput, moveRoboUpSpeed);
        }
        else{
            motor.set(ControlMode.PercentOutput, 0.0);
        } 
    }

    public void stop() {
        motor.set(ControlMode.PercentOutput, 0.0);
    }

    public void manualUp(){
        logger.info("hardHighLimit [" + getHardHighLimit() + "]");
        if(getHardHighLimit() == true)
            motor.set(ControlMode.PercentOutput, moveCustomUpSpeed);
        else{
            motor.set(ControlMode.PercentOutput, 0.0);
        }
    }

    public void manualDown(){
        logger.info("hardLowLimit [" + getHardLowLimit() + "]");
        if(getHardLowLimit() == true)
            motor.set(ControlMode.PercentOutput, moveCustomDownSpeed);
        else{
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
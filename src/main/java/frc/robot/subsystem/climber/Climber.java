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
    private PWMTalonSRX motor;
    private DigitalInput hardLowLimit;
    private DigitalInput optimalLimit;
    private DigitalInput hardTopLimit;

    private double moveRoboUpSpeed;
    private double moveRoboDownSpeed;
    private double moveCustomUpSpeed;
    private double moveCustomDownSpeed;

    private boolean hasActivated;
    
    public void init(PortMan portMan) throws Exception {
        logger.info("init");
        motor = new PWMTalonSRX(portMan.acquirePort(PortMan.pwm8_label, "Climber.motor"));
        //FIX THE CHANNEL FOR ^^^^^^^
        hardLowLimit = new DigitalInput(portMan.acquirePort(PortMan.digital2_label, "Climber.HardLowLimit"));
        optimalLimit = new DigitalInput(portMan.acquirePort(PortMan.digital3_label, "Climber.MinimumLimit"));
        hardTopLimit = new DigitalInput(portMan.acquirePort(PortMan.digital4_label, "Climber.HardHighLimit"));
        hasActivated = false;

      moveRoboUpSpeed = 1;
      moveRoboDownSpeed = -.4;
      moveCustomUpSpeed = .5;
      moveCustomDownSpeed = -.4
      ;
    }
    
    public void extend(){
        logger.info("extend");
        logger.info("optimalLimit [" + getOptimalLimit() + "]");
        if(getOptimalLimit() == true){
            motor.set(moveRoboUpSpeed);
        }
        else{
            motor.set(0.0);
        } 
    }

    public void stop() {
        motor.set(0.0);
    }

    public void manualUp(){
        logger.info("hardHighLimit [" + getHardHighLimit() + "]");
        if(getHardHighLimit() == true){
            if(hasActivated)
                motor.set(moveCustomUpSpeed);
            else{
                motor.set(moveRoboUpSpeed);
                if(getOptimalLimit() == false)
                    hasActivated = true;
            }
        }
        else{
            motor.set(0.0);
        }
    }

    public void manualDown(){
        logger.info("hardLowLimit [" + getHardLowLimit() + "]");
        if(getHardLowLimit() == true)
            motor.set(moveCustomDownSpeed);
        else{
            motor.set(0.0);
        }
    }

    public void retract(){
        logger.info("retract");
        if(getHardLowLimit() == true)
            motor.set(moveRoboDownSpeed);
        else   
            motor.set(0.0);
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
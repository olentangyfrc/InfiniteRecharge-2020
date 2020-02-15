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
    private boolean pastValue1 = false;
    private DigitalInput switch1;
    private DigitalInput switch2;
    private DigitalInput hardTopLimit;
    
    public void init(PortMan portMan) throws Exception {
        logger.info("init");
        motor = new TalonSRX(portMan.acquirePort(PortMan.can_16_label, "Climber.motor"));
        switch1 = new DigitalInput(portMan.acquirePort(PortMan.digital0_label, "Climber.HardLowLimit"));
        switch2 = new DigitalInput(portMan.acquirePort(PortMan.digital1_label, "Climber.MinimumLimit"));


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
        if(getDigitalInput1() == false)
        {
            motor.set(ControlMode.PercentOutput, .5);
        }
        else{
            logger.info("extend off");
            motor.set(ControlMode.PercentOutput, 0.0);
        }  
    }

    public void retract(){
        logger.info("retract");
        if(getDigitalInput2() == false && getDigitalInput1() == true)
        {
            motor.set(ControlMode.PercentOutput, -.5);
        }
        else{
            motor.set(ControlMode.PercentOutput, 0.0);
        }
    }

    public boolean getDigitalInput1(){
        return switch1.get();
    }

    public boolean getDigitalInput2(){
        return switch2.get();
    }
}

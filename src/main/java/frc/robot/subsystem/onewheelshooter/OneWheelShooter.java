/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.onewheelshooter;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.subsystem.PortMan;

/**
 * Add your docs here.
 */
public class OneWheelShooter extends SubsystemBase{

    private static Logger logger = Logger.getLogger(OneWheelShooter.class.getName());
    private TalonSRX motor;

    private double pValue;
    private double iValue;
    private double dValue;

    private double velocity;
    
    public void init(final PortMan portMan) throws Exception {
        logger.info("init");
        motor = new TalonSRX(portMan.acquirePort(PortMan.can_16_label, "OneWheelShooter"));

        pValue = .3;
        iValue = 0;
        dValue = .2;
        velocity = 100000;


      motor.enableCurrentLimit(true);
      motor.configPeakCurrentLimit(30);
      motor.configContinuousCurrentLimit(20);
      motor.configPeakCurrentDuration(400);
      motor.configAllowableClosedloopError(0, 5);
      motor.setSelectedSensorPosition(0, 0, 0);

      motor.config_kP(0, pValue, 0);
      motor.config_kI(0, iValue, 0);
      motor.config_kD(0, dValue, 0);
      motor.config_kF(0, 0, 0);

      motor.configMotionCruiseVelocity(4500);
      motor.configMotionAcceleration(4096);

    }
    
    public void shoot(){
        motor.set(ControlMode.Velocity, velocity);
    }
    public void reverse(){
        motor.set(ControlMode.Velocity, -velocity);
    }
    public void setVelocity(double a){
        velocity = a;
    }
    public void stop(){
        motor.set(ControlMode.Velocity, 0);
    }
    public void changePID(double p, double i, double d){
        if(pValue != p)
            pValue = p;
        if(iValue != i)
            iValue = i;
        if(dValue != d)
            dValue = d;
    }
    public double getVelocity(){
        return motor.getSelectedSensorVelocity();
    }
}

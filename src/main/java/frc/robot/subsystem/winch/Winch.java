/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.winch;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.subsystem.PortMan;

/**
 * Add your docs here.
 */
public class Winch extends SubsystemBase{

    private static Logger logger = Logger.getLogger(Winch.class.getName());
    private TalonFX motor;

    private double pValue;
    private double iValue;
    private double dValue;

    private double velocity;
    
    public void init(final PortMan portMan) throws Exception {
        logger.info("init");
        motor = new TalonFX(portMan.acquirePort(PortMan.can_10_label, "Winch"));

        pValue = .3;
        iValue = 0;
        dValue = .2;
        velocity = -100000;

      motor.setNeutralMode(NeutralMode.Brake);
      motor.configFactoryDefault();
      motor.configAllowableClosedloopError(0, 5);
      motor.setSelectedSensorPosition(0, 0, 0);

      motor.config_kP(0, pValue, 0);
      motor.config_kI(0, iValue, 0);
      motor.config_kD(0, dValue, 0);
      motor.config_kF(0, 0, 0);


      motor.configClosedloopRamp(.9);
    }
    
    public void up(){
        motor.set(ControlMode.Velocity, velocity);
    }
    public void down(){
        motor.set(ControlMode.Velocity, -velocity);
    }
    public void setVelocity(double a){
        velocity = a;
    }
    public void stop(){
        motor.set(ControlMode.PercentOutput, 0);
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
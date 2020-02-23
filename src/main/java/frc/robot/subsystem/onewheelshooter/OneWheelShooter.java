/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.onewheelshooter;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.subsystem.PortMan;

/**
 * Add your docs here.
 */
public class OneWheelShooter extends SubsystemBase{

    private static Logger logger = Logger.getLogger(OneWheelShooter.class.getName());
    private TalonFX motor;

    private double pValue;
    private double iValue;
    private double dValue;

    private double velocity;
    
    public void init(final PortMan portMan) throws Exception {
        logger.info("init");
        motor = new TalonFX(portMan.acquirePort(PortMan.can_28_label, "OneWheelShooter"));

        pValue = .8;
        iValue = 0;
        dValue = .002;
        velocity = 10790;

      motor.setNeutralMode(NeutralMode.Coast);
      motor.configFactoryDefault();
      motor.configAllowableClosedloopError(0, 5);
      motor.setSelectedSensorPosition(0, 0, 0);

      motor.config_kP(0, pValue, 0);
      motor.config_kI(0, iValue, 0);
      motor.config_kD(0, dValue, 0);
      motor.config_kF(0, 0, 0);


      motor.configClosedloopRamp(.9);

    }
    
    public void shoot(){
        logger.info("shoot");
        logger.info("shoot [" + velocity + "]");

        motor.set(ControlMode.Velocity, velocity);
        //motor.set(ControlMode.PercentOutput, velocity);
        logger.info("[" + velocity + "]");
    }
    public void reverse(){
        logger.info("reverse [" + -velocity + "]");
        motor.set(ControlMode.Velocity, -velocity);
    }
    public void setVelocity(double a){
        velocity = a;
    }
    public void stop(){
        logger.info("stop");
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
    public double getCurrentVelocity(){
        return motor.getSelectedSensorVelocity();
    }
    public double getVelocity() {
        return velocity;
    }
    public double getPValue(){
        return pValue;
    }
    public double getIValue(){
        return iValue;
    }
    public double getDValue(){
        return dValue;
    }
    public double getCurrent(){
        return motor.getSupplyCurrent();
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.PortMan;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake extends SubsystemBase {
  private static Logger logger = Logger.getLogger(Intake.class.getName());
  private DoubleSolenoid doubleSolenoid;
  private TalonSRX motor;
  private boolean gateUp;
  private double percent;

  public void init(PortMan portMan) throws Exception {
    doubleSolenoid = new DoubleSolenoid(portMan.acquirePort(PortMan.pcm4_label, "Intake.gate0"), portMan.acquirePort(PortMan.pcm5_label, "Intake.gate1"));
    motor = new TalonSRX(portMan.acquirePort(PortMan.can_25_label, "Intake.wheel"));
    gateUp = false;
    percent = .5;
  }

  public void moveWheelIntakeUp(){
      doubleSolenoid.set(DoubleSolenoid.Value.kForward);
      gateUp = true;
  }
  public void moveWheelIntakeDown(){
    doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    gateUp = false;
  }
  public boolean getGateStatus(){
    return gateUp;
  }
  public void wheelSpinFront(){
    motor.set(ControlMode.PercentOutput,-percent);
  }
  public void wheelSpinBack(){
    motor.set(ControlMode.PercentOutput, percent);
  }
  public void setPercent(double a){
    percent = a;
  }
  public double getPercent(){
    return motor.getMotorOutputPercent();
  }
  public void stop(){
    motor.set(ControlMode.PercentOutput, 0.0);
  }
}

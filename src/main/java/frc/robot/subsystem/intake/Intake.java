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
  private DoubleSolenoid gate;
  private TalonSRX motor;
  private boolean gateUp;

  public void init(PortMan portMan) throws Exception {
    gate = new DoubleSolenoid(portMan.acquirePort(PortMan.pcm0_label, "Intake.gate0"), portMan.acquirePort(PortMan.pcm1_label, "Intake.gate1"));
    motor = new TalonSRX(portMan.acquirePort(PortMan.can_39_label, "Intake.wheel"));
    gateUp = false;
  }

  public void moveGate(boolean up){
    if (up == true){
      gate.set(DoubleSolenoid.Value.kReverse);
      gateUp = true;
    }
    else{
      gate.set(DoubleSolenoid.Value.kForward);
      gateUp = false;
    }
  }
  public boolean getGateStatus(){
    return gateUp;
  }
  public void wheelSpin(double percent){
    motor.set(ControlMode.PercentOutput, percent);
  }
}

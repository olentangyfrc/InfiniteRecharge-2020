/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.onewheelshooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.onewheelshooter.OneWheelShooter;

public class OneWheelReverse extends CommandBase {
  private OneWheelShooter oneWheelShooter;
  private boolean stop;
  /**
   * Creates a new OneWheelReverse.
   */
  public OneWheelReverse(OneWheelShooter s) {
    oneWheelShooter = s;
    stop = false;
    addRequirements(s);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    oneWheelShooter.reverse();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    stop = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.intake.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.logging.Logger;
import frc.robot.subsystem.intake.Intake;

public class IntakeUp extends CommandBase {
  private Intake intake;
  private boolean stop;

  /**
   * Creates a new IntakeUp.
   */
  public IntakeUp(Intake i) {
    intake = i;
    addRequirements(i);
    stop = false;
  }

  // Called when the comsmand is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.moveWheelIntakeUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    stop = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}

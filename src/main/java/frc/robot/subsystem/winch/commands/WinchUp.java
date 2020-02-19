/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.winch.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.winch.Winch;

public class WinchUp extends CommandBase {
  /**
   * Creates a new Shoot.
   */

  private Winch winch;
  private boolean stop;

  public WinchUp(Winch w) {
    winch = w;
    addRequirements(winch);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    stop = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    winch.up();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      stop = true;
      winch.stop();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}

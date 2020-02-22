/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.controlpanel.ControlPanel;

public class Stop extends CommandBase {
  /**
   * Creates a new Stop.
   */
  private ControlPanel controlPanel;
  private boolean stop;
  public Stop(ControlPanel c) {
    controlPanel = c;
    addRequirements(c);
    stop = true;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    controlPanel.stop();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

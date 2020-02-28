/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.controlpanel.ControlPanel;

public class SpinManual extends CommandBase {
  /**
   * Creates a new SpinManual.
   */
  ControlPanel controlPanel;
  private boolean stop;
  public SpinManual(ControlPanel c) {
    controlPanel = c;
    addRequirements(c);
    stop = false;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    stop = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(stop)
      return;
    controlPanel.spin();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(interrupted){
      stop = true;
      controlPanel.stop();
    }

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}

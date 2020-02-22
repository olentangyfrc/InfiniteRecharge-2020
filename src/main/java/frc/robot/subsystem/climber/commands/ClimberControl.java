/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.climber.Climber;

public class ClimberControl extends CommandBase {
  /**
   * Creates a new ClimberControl.
   */
  private Climber climber;
  private boolean stop;
  public ClimberControl(Climber c) {
    // Use addRequirements() here to declare subsystem dependencies.
    climber = c;
    addRequirements(c);
    stop = false;
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
    climber.manualUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      stop = true; 
      climber.stop();
    }

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}

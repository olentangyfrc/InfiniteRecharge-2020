/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.climber.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.climber.Climber;

public class Climb extends CommandBase {
  /**
   * Creates a new PatsCommand.
   */

  private Climber climber;
  private boolean stop;
  private static Logger logger = Logger.getLogger(Climb.class.getName());
  public Climb(Climber climb) {
    climber = climb;
    stop = false;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    logger.info("Climb execute");
    climber.extend();
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

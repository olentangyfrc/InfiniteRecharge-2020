/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystem.controlpanel.ControlPanel;


public class SpinnerUp extends CommandBase {
  private final Logger logger = Logger.getLogger(SpinnerUp.class.getName());
  
  private ControlPanel controlPanel;
  /**
   * Creates a new Spin.
   */
  public SpinnerUp(ControlPanel c) {
    controlPanel = c;
    addRequirements(c);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    logger.info("execute");
    controlPanel.pushColorSpinnerUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
      logger.info("Ended");
  }

  @Override
  public synchronized void cancel() {
      logger.info("Canceled");
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

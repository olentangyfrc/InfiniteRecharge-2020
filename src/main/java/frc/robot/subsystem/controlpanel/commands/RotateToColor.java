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

public class RotateToColor extends CommandBase {
  private final Logger logger = Logger.getLogger(RotateToColor.class.getName());
  
  private ControlPanel controlPanel;
  private boolean stop = false;
  private String targetColor;

  /**
   * Creates a new DisplayColor.
   */
  public RotateToColor(ControlPanel c, String target) {
    controlPanel = c;
    addRequirements(c);
    targetColor = target;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    stop = false;
    controlPanel.setBrakeMode(true);
    switch(targetColor){
      case ("Blue"):
        targetColor = "Red";
        break;
      case ("Red"):
        targetColor = "Blue";
        break;
      case ("Green"):
        targetColor = "Yellow";
        break;
      case ("Yellow"):
        targetColor = "Green";
        break;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (controlPanel.getDetectedColor().equals(targetColor))
      stop = true;
    controlPanel.spin(0.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
      controlPanel.spin(0.0);
      logger.info("Ended");
  }

  @Override
  public synchronized void cancel() {
      controlPanel.spin(0.0);
      logger.info("Canceled");
      stop = true;
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}

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
  private String oppositeColor;

  /**
   * Creates a new DisplayColor.
   */
  public RotateToColor(ControlPanel c) {
    controlPanel = c;
    addRequirements(c);
    targetColor = controlPanel.getControlPanelTargetColor();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    stop = false;
    controlPanel.setBrakeMode(true);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    targetColor = controlPanel.getControlPanelTargetColor();

    if (targetColor.length() > 0) {
      switch(targetColor){
        case ("Blue"):
          oppositeColor = "Red";
          break;
        case ("Red"):
          oppositeColor = "Blue";
          break;
        case ("Green"):
          oppositeColor = "Yellow";
          break;
        case ("Yellow"):
          oppositeColor = "Green";
          break;
      }

      if (controlPanel.getDetectedColor().equals(oppositeColor)) {
        stop = true;
      } else {
          controlPanel.spin(controlPanel.getPercentOutput());
      }

    } else {
        stop = true;
    }
    
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
    return true;
  }
}

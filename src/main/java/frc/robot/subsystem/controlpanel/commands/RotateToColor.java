/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.DriverStation;
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
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    stop = false;
    controlPanel.setBrakeMode(true);
    targetColor = DriverStation.getInstance().getGameSpecificMessage();
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
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    logger.info(controlPanel.getDetectedColor());
    if (!controlPanel.getDetectedColor().equals(oppositeColor))
      controlPanel.spin();
    else{
      stop = true;
      controlPanel.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}

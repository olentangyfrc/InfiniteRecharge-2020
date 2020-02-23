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

public class SpinRotations extends CommandBase {
  private final Logger logger = Logger.getLogger(RotateToColor.class.getName());
  
  private ControlPanel controlPanel;
  private int spinCount;
  private int count;
  private String startColor;
  private String pastColor;
  private boolean stop;
  

  public SpinRotations(ControlPanel c, int sc) {
    controlPanel = c;
    addRequirements(c);
    spinCount = sc;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    count = 0;
    startColor = controlPanel.getDetectedColor();
    pastColor = startColor;
    stop = false;
    controlPanel.setBrakeMode(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    logger.info(controlPanel.getDetectedColor());
    if (count >= spinCount){
      stop = true;
      controlPanel.stop();
      return;
    }
    controlPanel.spin();
    String color = controlPanel.getDetectedColor();
    if(!(pastColor.equals(color)) && color.equals(startColor))
    {
      logger.info("color is equal to startColor");
      count++;
    }
    pastColor = color;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    stop = true;
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}

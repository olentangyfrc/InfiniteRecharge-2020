/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.controlpanel.ControlPanel;

public class SensorSpin extends CommandBase {
  private final Logger logger = Logger.getLogger(RotateToColor.class.getName());
  
  private ControlPanel controlPanel;
  private int spinCount;
  private int count;
  private Color startColor;
  private Color pastColor;
  private boolean stop;
  

  public SensorSpin(ControlPanel c, int sc) {
    controlPanel = c;
    addRequirements(c);
    spinCount = sc;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    count = 0;
    startColor = controlPanel.getColor();
    pastColor = startColor;
    stop = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (count >= spinCount){
      stop = true;
      return;
    }
    controlPanel.spin(0.2);
    Color color = controlPanel.getColor();
    logger.info("pastColor RGB: " + pastColor.red + ", " + pastColor.green + ", " + pastColor.blue);
    logger.info("color RGB: " + color.red + ", " + color.green + ", " + color.blue);
    logger.info("startColor RGB: " + startColor.red + ", " + startColor.green + ", " + startColor.blue);
    //if(!(pastColor.equals(color)) && color.equals(startColor))
    //{
      //logger.info("color is equal to startColor");
      count++;
    //}
    pastColor = color;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
      logger.info("Ended");
      controlPanel.spin(0);
  }

  @Override
  public synchronized void cancel() {
      logger.info("Canceled");
      controlPanel.spin(0);
      stop = true;
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}

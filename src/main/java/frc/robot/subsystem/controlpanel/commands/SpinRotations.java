/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Timer;
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
  private Timer timer;
  private boolean isColorSeen;
  private boolean firstTime;
  private double reactionTime;
  private String expectedColor;
  

  public SpinRotations(ControlPanel c, int sc) {
    controlPanel = c;
    addRequirements(c);
    spinCount = sc;
    timer = new Timer();
    isColorSeen = false;
    firstTime = true;
    reactionTime = .15;
    expectedColor = "";
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    count = 0;
    startColor = controlPanel.getDetectedColor();
    pastColor = startColor;
    stop = false;
    timer.reset();
    firstTime = true;
    controlPanel.setBrakeMode(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    logger.info("RED: " + controlPanel.getRedValue() + "... BLUE: " + controlPanel.getBlueValue() + "... GREEN: " + controlPanel.getGreenValue());
    String color = controlPanel.getDetectedColor();
    if(!color.equals("Red") && firstTime == true){
      controlPanel.spinRotations();
      return;
    }
    else{
      startColor = "Red";
      firstTime = false;
    }
    if (count >= spinCount){
      stop = true;
      controlPanel.stop();
      return;
    }
    controlPanel.spinRotations();
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

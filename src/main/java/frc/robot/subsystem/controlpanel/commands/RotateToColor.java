/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystem.controlpanel.ControlPanel;

public class RotateToColor extends CommandBase {
  private final Logger logger = Logger.getLogger(RotateToColor.class.getName());
  
  private ControlPanel controlPanel;
  private boolean stop = false;
  private String targetColor;
  private String oppositeColor;
  private String detectedColor;
  private String pastColor;
  private String beforeColor;
  private boolean firstTime;
  private Timer timer;
  private double reactionTime;


  /**
   * Creates a new DisplayColor.
   */
  public RotateToColor(ControlPanel c) {
    controlPanel = c;
    addRequirements(c);
    timer = new Timer();
    reactionTime = .25;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    firstTime = true;
    timer.reset();
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
    logger.info("Opp Color: " + oppositeColor);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    logger.info("RED: " + controlPanel.getRedValue() + "... BLUE: " + controlPanel.getBlueValue() + "... GREEN: " + controlPanel.getGreenValue());
    detectedColor = controlPanel.getDetectedColor();
    if(detectedColor.equals("Green") && targetColor.equals("Green") && firstTime == true){
      controlPanel.spinColor();
      return;
    }
    if(detectedColor.equals("Yellow") && targetColor.equals("Yellow") && firstTime == true){
      controlPanel.spinColor();
      return;
    }
    if(firstTime)
      pastColor = detectedColor;

    /*
    if(detectedColor.equals("Green") && pastColor.equals("Yellow"))
      detectedColor = "Blue";
    if(detectedColor.equals("Yellow") && pastColor.equals("Green"))
      detectedColor = "Red";
      */

    if (!detectedColor.equals(oppositeColor)){
      if(firstTime){
        timer.start();
        firstTime = false;
      }
      if(!pastColor.equals(detectedColor)){
        timer.reset();
        timer.start();
      }
      controlPanel.spinColor();
      pastColor = detectedColor;
    }
    else{
      timer.stop();
      if(timer.get() < reactionTime){
          stop = true;
          controlPanel.stop();
      }
      else{
        timer.reset();
        timer.start();
      }
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

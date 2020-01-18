/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.SubsystemFactory;

import frc.robot.subsystem.controlpanel.ControlPanel;
import frc.robot.subsystem.controlpanel.ControlPanelSBTab;

public class DisplayColor extends CommandBase {
  private final Logger logger = Logger.getLogger(DisplayColor.class.getName());
  
  private ControlPanel controlPanel;
  private ControlPanelSBTab shuffleBoard;
  private boolean stop = false;
  /**
   * Creates a new DisplayColor.
   */
  public DisplayColor(ControlPanel c) {
    controlPanel = c;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shuffleBoard = new ControlPanelSBTab(controlPanel);
    stop = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    controlPanel.displayColors();
    shuffleBoard.update();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
      logger.info("Ended");
  }

  @Override
  public synchronized void cancel() {
      logger.info("Canceled");
      stop = true;
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}

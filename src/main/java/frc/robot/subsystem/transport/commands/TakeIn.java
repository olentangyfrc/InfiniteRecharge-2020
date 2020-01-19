/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.transport.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.transport.Transport;

public class TakeIn extends CommandBase {

  private Logger logger = Logger.getLogger(TakeIn.class.getName());

  private Transport transport;
  
  public TakeIn(Transport t) {
    transport = t;
    addRequirements(t);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (transport.count() < 5)
    {
      transport.take();
    }
    else
    {
      logger.info("At capacity, can't take.");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

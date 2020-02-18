/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.transport.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.transport.Transport;

public class TailGateDown extends CommandBase {
  /**
   * Creates a new TailGateDown.
   */
  private Transport transport;
  private boolean stop;

  public TailGateDown(Transport t) {
    transport = t;
    addRequirements(t);
    stop = false;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    transport.moveTailGateDown();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    stop = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

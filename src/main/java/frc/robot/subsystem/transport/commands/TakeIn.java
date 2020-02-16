/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.transport.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.transport.Transport;

public class TakeIn extends CommandBase {

  private Logger logger = Logger.getLogger(TakeIn.class.getName());

  private Transport transport;
  private double time;
  private double targetTime;
  private boolean isMoving;
  
  public TakeIn(Transport t) {
    transport = t;
    addRequirements(t);
    isMoving = false;
  }

  @Override
  public void initialize() {
    isMoving = false;
  }

  @Override
  public void execute() {

    if(isMoving)
      if(transport.getTime() < targetTime){
        return;
      }
      else {
        transport.stop();
        isMoving = false;
      }

    if(transport.getTransportReceiverSwitch() == false){
        logger.info("activated");
        transport.take();
        isMoving = true;
        time = Timer.getFPGATimestamp();
        targetTime = time + transport.getTargetTime();
      }
    }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    isMoving = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.telemetry.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.telemetry.Telemetry;

public class SquareSelf extends CommandBase {
  /**
   * Creates a new SquareSelf.
   */
  private Telemetry telemetry;
  private boolean stop;
  private double targetDistance;
  private static Logger logger = Logger.getLogger(SquareSelf.class.getName());

  public SquareSelf(Telemetry sqs, double td) {
    // Use addRequirements() here to declare subsystem dependencies.
    telemetry = sqs;
    targetDistance = td;
    addRequirements(sqs);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  stop = false;

  if (Math.abs(telemetry.getFrontLidarDistance()-targetDistance) > telemetry.getTolerance() || Math.abs(telemetry.getRearLidarDistance()-targetDistance) > telemetry.getTolerance() || Math.abs(telemetry.getFrontLidarDistance()-telemetry.getRearLidarDistance()) > telemetry.getTolerance())
  {
    double angleError = Math.atan((Math.max(telemetry.getFrontLidarDistance(), telemetry.getRearLidarDistance())-Math.min(telemetry.getFrontLidarDistance(), telemetry.getRearLidarDistance()))/telemetry.getBetweenLidar());

    if (telemetry.getFrontLidarDistance()*Math.cos(angleError)-targetDistance > telemetry.getRearLidarDistance()*Math.cos(angleError)-targetDistance)
    {
      if (telemetry.getFrontLidarDistance() < telemetry.getRearLidarDistance())
      {
          //move front wheels right angleError, turn right
          logger.info("Move front wheels right angleError (" + angleError + "), turn right.");
      }
      else
      {
          //move front wheels left angleError, turn left
          logger.info("Move front wheels left angleError (" + angleError + "), turn left.");
      }
    }
    else
    {
      if (telemetry.getFrontLidarDistance() < telemetry.getRearLidarDistance())
      {
          //move back wheels left angleError, turn right
          logger.info("move back wheels left angleError (" + angleError + "), turn right.");
      }
      else
      {
          //move back wheels right angleError, turn left
          logger.info("move back wheels right angleError (" + angleError + "), turn left.");
      }
    }

    while(telemetry.isSquare(telemetry.getTolerance()) == false)
    {
      if (telemetry.getFrontLidarDistance()*Math.cos(angleError)-targetDistance > telemetry.getRearLidarDistance()*Math.cos(angleError)-targetDistance)
      {
        if (telemetry.getFrontLidarDistance() < telemetry.getRearLidarDistance())
        {
            //move front wheels right correction, turn right
            logger.info("move front wheels right correction, turn right.");
        }
        else
        {
            //move front wheels left correction, turn left
            logger.info("move front wheels left correction, turn left.");
        }
      }
      else
      {
        if (telemetry.getFrontLidarDistance() < telemetry.getRearLidarDistance())
        {
            //move back wheels left correction, turn right
            logger.info("move back wheels left correction, turn right.");
        }
        else
        {
            //move back wheels right correction, turn left
            logger.info("move back wheels right correction, turn left.");
        }
      }
    }
    
    double distanceError = Math.abs(telemetry.getFrontLidarDistance() - targetDistance);

    if (distanceError > telemetry.getTolerance())
    {
        if (telemetry.getFrontLidarDistance() > targetDistance)
        {
            //move left distanceError
            logger.info("move left distanceError (" + distanceError + ")");
        }
        else
        {
            //move right distanceError
            logger.info("move right distanceError (" + distanceError + ")");
        }
    }
  }
  stop = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    stop = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}

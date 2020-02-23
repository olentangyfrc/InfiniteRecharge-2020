package frc.robot.subsystem.transport;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystem.SBInterface;

public class TransportSBTab implements SBInterface
{
    private Transport transport;
    private ShuffleboardTab tab;
    private NetworkTableEntry motorTopSpeedForward;
    private NetworkTableEntry motorBottomSpeedForward;
    private NetworkTableEntry motorSpeedBackward;
    private NetworkTableEntry ballCount;
    private NetworkTableEntry dio1;
    private NetworkTableEntry dio2;
    private NetworkTableEntry direction;
    private NetworkTableEntry isLogging;
    private NetworkTableEntry gateStatus;
    private NetworkTableEntry currentTime;
    private NetworkTableEntry targetTime;
    private NetworkTableEntry current;
    private NetworkTableEntry shooterSpeedLow;
    private NetworkTableEntry shooterSpeedHigh;
    private NetworkTableEntry intakeStopDuration;



    public TransportSBTab(Transport c) 
    {
      transport = c;

      tab = Shuffleboard.getTab("Transport");
      motorTopSpeedForward = tab.add("Top Motor Speed Forward", transport.getTopMotorSpeedForward()).getEntry();
      motorBottomSpeedForward = tab.add("Bottom Motor Speed Forward", transport.getBottomMotorSpeedForward()).getEntry();
      motorSpeedBackward = tab.add("Motor Speed Backward", transport.getMotorSpeedBackward()).getEntry();
      ballCount = tab.add("ballCount", 0).getEntry();
      dio1 = tab.add("Digital Input 1", false).getEntry();
      dio2 = tab.add("Digital Input 2", false).getEntry();
      gateStatus = tab.add("Gate up", false).getEntry();
      currentTime = tab.add("Current Time elapsed", 0.0).getEntry();
      targetTime = tab.add("Transport target time", transport.getTargetTime()).getEntry();
      shooterSpeedLow = tab.add("Shoot Speed Low", transport.getShooterSpeedLow()).getEntry();
      shooterSpeedHigh = tab.add("Shoot Speed High", transport.getShooterSpeedHigh()).getEntry();
      current = tab.add("LeftIntake Current", transport.getCurrent()).getEntry();
      intakeStopDuration = tab.add("Intake Stop Duration", transport.getIntakeStopDuration()).getEntry();
   }

   public void update() 
   {
     //speed.setDouble(transport.getVelocity());
     ballCount.setDouble(transport.getBallCount());
     dio1.setBoolean(transport.getTransportReceiverSwitch());
     dio2.setBoolean(transport.getTransportSenderSwitch());
     gateStatus.setBoolean(transport.getGateStatus());
     currentTime.setDouble(Timer.getFPGATimestamp());
     if(targetTime.getDouble(0.0) != transport.getTargetTime())
        transport.setTargetTime(targetTime.getDouble(transport.getTargetTime()));
      transport.setTopMotorSpeedForward(motorTopSpeedForward.getDouble(transport.getTopMotorSpeedForward()));
      transport.setBottomMotorSpeedForward(motorBottomSpeedForward.getDouble(transport.getBottomMotorSpeedForward()));
      transport.setMotorSpeedBackward(motorSpeedBackward.getDouble(transport.getMotorSpeedBackward()));
      transport.setShooterSpeedLow(shooterSpeedLow.getDouble(transport.getShooterSpeedLow()));
      transport.setShooterSpeedHigh(shooterSpeedHigh.getDouble(transport.getShooterSpeedHigh()));
      current.setDouble(transport.getCurrent());
      transport.setIntakeStopDuration(intakeStopDuration.getDouble(transport.getIntakeStopDuration()));
   }

}
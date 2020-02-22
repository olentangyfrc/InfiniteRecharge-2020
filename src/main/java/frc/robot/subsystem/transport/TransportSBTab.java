package frc.robot.subsystem.transport;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystem.SBInterface;

public class TransportSBTab implements SBInterface
{
    private Transport transport;
    private ShuffleboardTab tab;
    private NetworkTableEntry speed;
    private NetworkTableEntry ballCount;
    private NetworkTableEntry dio1;
    private NetworkTableEntry dio2;
    private NetworkTableEntry direction;
    private NetworkTableEntry isLogging;
    private NetworkTableEntry gateStatus;
    private NetworkTableEntry currentTime;
    private NetworkTableEntry targetTime;
    

    public TransportSBTab(Transport c) 
    {
      transport = c;

      tab = Shuffleboard.getTab("Transport");
      speed = tab.add("speed", 0).getEntry();
      ballCount = tab.add("ballCount", 0).getEntry();
      dio1 = tab.add("Digital Input 1", false).getEntry();
      dio2 = tab.add("Digital Input 2", false).getEntry();
      gateStatus = tab.add("Gate up", false).getEntry();
      currentTime = tab.add("Current Time elapsed", 0.0).getEntry();
      targetTime = tab.add("Transport target time", 2).getEntry();
   }

   public void update() 
   {
     speed.setDouble(transport.getVelocity());
     ballCount.setDouble(transport.getBallCount());
     dio1.setBoolean(transport.getTransportReceiverSwitch());
     dio2.setBoolean(transport.getTransportSendserSwitch());
     gateStatus.setBoolean(transport.getGateStatus());
     currentTime.setDouble(transport.getTime());
     if(targetTime.getDouble(0.0) != transport.getTargetTime())
        transport.setTargetTime(targetTime.getDouble(2));
   }

}
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
    

    public TransportSBTab(Transport c) 
    {
      transport = c;

      tab = Shuffleboard.getTab("Transport");
      speed = tab.add("speed", 0).getEntry();
      ballCount = tab.add("ballCount", 0).getEntry();
      dio1 = tab.add("Digital Input 1", false).getEntry();
      dio2 = tab.add("Digital Input 2", false).getEntry();
      gateStatus = tab.add("Gate up", false).getEntry();
   }

   public void update() 
   {
     speed.setDouble(transport.getVelocity());
     ballCount.setDouble(transport.getBallCount());
     dio1.setBoolean(transport.getDigitalInput1());
     dio2.setBoolean(transport.getDigitalInput2());
     gateStatus.setBoolean(transport.getGateStatus());
   }

}
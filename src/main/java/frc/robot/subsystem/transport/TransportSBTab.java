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
    private NetworkTableEntry distance;
    private NetworkTableEntry direction;
    private NetworkTableEntry isLogging;
    

    public TransportSBTab(Transport c) 
    {
      transport = c;

      tab = Shuffleboard.getTab("Transport");
      speed = tab.add("speed", 0).getEntry();
      ballCount = tab.add("ballCount", 0).getEntry();
      distance = tab.add("distance", 0).getEntry();
   }

   public void update() 
   {
     speed.setDouble(transport.getVelocity());
     ballCount.setDouble(transport.getBallCount());
     distance.setDouble(transport.getDistance());
   }

}
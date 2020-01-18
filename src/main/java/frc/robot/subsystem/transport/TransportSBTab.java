package frc.robot.subsystem.transport;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class TransportSBTab
{
    private Transport transport;
    private ShuffleboardTab tab;

     public TransportSBTab(Transport c) 
     {
       transport = c;

       tab = Shuffleboard.getTab("Transport");
       tab.add("data", 0);
   }

   public void update() 
   {
   }

}
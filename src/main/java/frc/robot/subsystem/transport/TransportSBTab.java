/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
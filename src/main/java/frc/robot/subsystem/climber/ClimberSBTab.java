/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.climber;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystem.SBInterface;

/**
 * Add your docs here.
 */
public class ClimberSBTab implements SBInterface {
    private Climber climber;
    private ShuffleboardTab tab;
    private NetworkTableEntry limitSwitch1;
    private NetworkTableEntry limitSwitch2;

    public ClimberSBTab(Climber c) {
        climber = c;

        tab = Shuffleboard.getTab("Climber");
        limitSwitch1 = tab.add("Limit Switch1", false).getEntry();
        limitSwitch2 = tab.add("Limit Switch2", false).getEntry();
    }


    /**
     * get data from subsystem and update SB widgets
     * get data from SB widgets and update subsystem
     */
    public void update() {
        limitSwitch1.setBoolean(climber.getDigitalInput1());
    }
}

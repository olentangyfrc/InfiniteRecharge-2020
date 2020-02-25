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
    private NetworkTableEntry hardLowLimit;
    private NetworkTableEntry hardTopLimit;
    private NetworkTableEntry optimalLimit; 
    private NetworkTableEntry roboUpSpeed;
    private NetworkTableEntry roboDownSpeed;
    private NetworkTableEntry customUpSpeed;
    private NetworkTableEntry customDownSpeed;
    

    public ClimberSBTab(Climber c) {
        climber = c;

        tab = Shuffleboard.getTab("Climber");
        hardLowLimit = tab.add("HardLowLimit", false).getEntry();
        hardTopLimit = tab.add("HardTop Liwit", false).getEntry();
        optimalLimit = tab.add("OptimalLimit", false).getEntry();
        roboUpSpeed = tab.add("Robot Up Speed", .8).getEntry();
        roboDownSpeed = tab.add("Robot Down Speed", -.3).getEntry();
        customUpSpeed = tab.add("Custom Up Speed", .5).getEntry();
        customDownSpeed = tab.add("Custo Down Speed", -.3).getEntry();
    }


    /**
     * get data from subsystem and update SB widgets
     * get data from SB widgets and update subsystem
     */
    public void update() {
        hardLowLimit.setBoolean(climber.getHardLowLimit());
        hardTopLimit.setBoolean(climber.getHardHighLimit());
        optimalLimit.setBoolean(climber.getOptimalLimit());
        climber.setRoboMoveUpSpeed(roboUpSpeed.getDouble(.4));
        climber.setRoboMoveDownSpeed(roboDownSpeed.getDouble(-.3));
        climber.setCustomMoveUpSpeed(customUpSpeed.getDouble(.4));
        climber.setCustomMoveDownSpeed(customDownSpeed.getDouble(-.3));
    }
}

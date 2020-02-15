/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.intake;

import java.util.logging.Logger;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystem.SBInterface;

public class IntakeSBTab implements SBInterface{
    private Intake intake;
    private ShuffleboardTab tab;

    private NetworkTableEntry gateStatus;
    private NetworkTableEntry velocity;


    private static Logger logger = Logger.getLogger(IntakeSBTab.class.getName());

    public IntakeSBTab(Intake i) {
        
        intake = i;

        tab = Shuffleboard.getTab("Intake");

        gateStatus = tab.add("Gate Up", false).getEntry();
        velocity = tab.add("Velocity", 0.0).getEntry();
    }


    /**
     * get data from subsystem and update SB widgets
     * get data from SB widgets and update subsystem
     */
    public void update() {
        gateStatus.setBoolean(intake.getGateStatus());
        intake.setVelocity(velocity.getDouble(10000));

    }
}

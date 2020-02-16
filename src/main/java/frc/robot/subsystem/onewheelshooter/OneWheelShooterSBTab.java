/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.onewheelshooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystem.SBInterface;

/**
 * Add your docs here.
 */
public class OneWheelShooterSBTab implements SBInterface{
    private OneWheelShooter shooter;
    private ShuffleboardTab tab;
    private NetworkTableEntry velocity;
    private NetworkTableEntry pValue;
    private NetworkTableEntry iValue;
    private NetworkTableEntry dValue;
    private NetworkTableEntry currentVelocity;

    public OneWheelShooterSBTab(OneWheelShooter c) {
        shooter = c;

        tab = Shuffleboard.getTab("OneWheelShooter");
        velocity = tab.add("Velocity", 10000).getEntry();
        pValue = tab.add("PValue", .3).getEntry();
        iValue = tab.add("IValue", 0.0).getEntry();
        dValue = tab.add("DValue", 0.2).getEntry();
        currentVelocity = tab.add("Current Velocity", 0.0).getEntry();
    }


    /**
     * get data from subsystem and update SB widgets
     * get data from SB widgets and update subsystem
     */
    public void update() {
        shooter.changePID(pValue.getDouble(.3), iValue.getDouble(0), dValue.getDouble(.2));
        shooter.setVelocity(velocity.getDouble(10000));
        currentVelocity.setDouble(shooter.getVelocity());
    }
}
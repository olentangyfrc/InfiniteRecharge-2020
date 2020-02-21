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
    private NetworkTableEntry current;

    public OneWheelShooterSBTab(OneWheelShooter c) {
        shooter = c;

        tab = Shuffleboard.getTab("OneWheelShooter");
        velocity = tab.add("Velocity", shooter.getVelocity()).getEntry();
        pValue = tab.add("PValue", shooter.getPValue()).getEntry();
        iValue = tab.add("IValue", shooter.getIValue()).getEntry();
        dValue = tab.add("DValue", shooter.getDValue()).getEntry();
        currentVelocity = tab.add("Current Velocity", 0.0).getEntry();
        current = tab.add("Current", 0.0).getEntry();
    }


    /**
     * get data from subsystem and update SB widgets
     * get data from SB widgets and update subsystem
     */
    public void update() {
        shooter.changePID(pValue.getDouble(.3), iValue.getDouble(0), dValue.getDouble(.2));
        shooter.setVelocity(velocity.getDouble(shooter.getVelocity()));
        currentVelocity.setDouble(shooter.getCurrentVelocity());
        current.setDouble(shooter.getCurrent());
    }
}
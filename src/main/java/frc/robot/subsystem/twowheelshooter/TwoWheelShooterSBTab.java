/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.twowheelshooter;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Add your docs here.
 */
public class TwoWheelShooterSBTab {
    private TwoWheelShooter twowheelshooter;
    private ShuffleboardTab tab;

    public TwoWheelShooterSBTab(TwoWheelShooter c) {
        twowheelshooter = c;

        tab = Shuffleboard.getTab("TwoWheelShooter");
        tab.add("data", 0);
    }


    /**
     * get data from subsystem and update SB widgets
     * get data from SB widgets and update subsystem
     */
    public void update() {
    }
}

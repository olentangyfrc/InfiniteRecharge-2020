/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class ControlPanelSBTab {
    private ControlPanel controlPanel;
    private ShuffleboardTab tab;

    private NetworkTableEntry red;
    private NetworkTableEntry green;
    private NetworkTableEntry blue;
    private NetworkTableEntry confidence;
    private NetworkTableEntry detectedColor;

    public ControlPanelSBTab(ControlPanel c) {
        controlPanel = c;

        tab = Shuffleboard.getTab("Control Panel");

        red = tab.add("Red", 0).getEntry();
        green = tab.add("Green", 0).getEntry();
        blue = tab.add("Blue", 0).getEntry();
        confidence = tab.add("Confidence", 0).getEntry();
        detectedColor = tab.add("Detected Color", "").getEntry();
    }


    /**
     * get data from subsystem and update SB widgets
     * get data from SB widgets and update subsystem
     */
    public void update() {
        red.setDouble(controlPanel.getRedValue());
        green.setDouble(controlPanel.getGreenValue());
        blue.setDouble(controlPanel.getBlueValue());
        confidence.setDouble(controlPanel.getConfidenceValue());
        detectedColor.setString(controlPanel.getDetectedColor());
        // finish this method based on controlpanel getvalues
        // make sure to call the init method in this method in order for all the values to be updated
    }
}

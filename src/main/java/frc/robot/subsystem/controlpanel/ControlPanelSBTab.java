/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel;

import java.util.logging.Logger;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystem.SBInterface;

public class ControlPanelSBTab implements SBInterface{
    private ControlPanel controlPanel;
    private ShuffleboardTab tab;

    private NetworkTableEntry red;
    private NetworkTableEntry green;
    private NetworkTableEntry blue;
    private NetworkTableEntry detectedColor;
    private NetworkTableEntry percentOutput;
    private NetworkTableEntry velocity;
    private NetworkTableEntry current;
    private NetworkTableEntry position;
    private NetworkTableEntry pValue;
    private NetworkTableEntry iValue;
    private NetworkTableEntry dValue;
    private NetworkTableEntry encoderPosition;
    private NetworkTableEntry stickStatus;
    private NetworkTableEntry brakeMode;

    private static Logger logger = Logger.getLogger(ControlPanelSBTab.class.getName());

    public ControlPanelSBTab(ControlPanel c) {
        
        controlPanel = c;

        tab = Shuffleboard.getTab("Control Panel");

        red = tab.add("Red", 0).getEntry();
        green = tab.add("Green", 0).getEntry();
        blue = tab.add("Blue", 0).getEntry();
        detectedColor = tab.add("Detected Color", "None").getEntry();
        percentOutput = tab.add("Percent Output", 0.0).getEntry();
        velocity = tab.add("Velocity", 20000).getEntry();
        current = tab.add("Current", 0.0).getEntry();
        position = tab.add("Encoder Position", 0.0).getEntry();
        pValue = tab.add("P Value", 0.2).getEntry();
        iValue = tab.add("I Value", 0.0).getEntry();
        dValue = tab.add("D Value", 0.2).getEntry();
        encoderPosition = tab.add("Setting Encoder Position", false).withSize(1, 1).withPosition(0, 1).getEntry();
        stickStatus = tab.add("Stick Status", false).getEntry();
        brakeMode = tab.add("Brake Mode OnOff", false).getEntry();
    }


    /**
     * get data from subsystem and update SB widgets
     * get data from SB widgets and update subsystem
     */
    public void update() {
        red.setDouble(controlPanel.getRedValue());
        green.setDouble(controlPanel.getGreenValue());
        blue.setDouble(controlPanel.getBlueValue());
        detectedColor.setString(controlPanel.getDetectedColor());
        percentOutput.setDouble(controlPanel.getPercentOutput());
        velocity.setDouble(controlPanel.getVelocity());
        current.setDouble(controlPanel.getCurrent());
        position.setDouble(controlPanel.getPosition());
        brakeMode.setBoolean(controlPanel.getBrakeOnOff());
        controlPanel.changePID(pValue.getDouble(0.2), iValue.getDouble(0.0), dValue.getDouble(0.2));
        stickStatus.setBoolean(controlPanel.getStickStatus());
        if(encoderPosition.getBoolean(false) == true){
            controlPanel.setZero();
            encoderPosition.setBoolean(false);
        }
        controlPanel.setVelocity(velocity.getDouble(20000));
        controlPanel.setPercentOutput(percentOutput.getDouble(0.6));
        // finish this method based on controlpanel getvalues
        // make sure to call the init method in this method in order for all the values to be updated
    }
}

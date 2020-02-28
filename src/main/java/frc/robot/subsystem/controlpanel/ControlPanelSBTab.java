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
import edu.wpi.first.wpilibj.util.Color;
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
    private NetworkTableEntry confidence;

    private static Logger logger = Logger.getLogger(ControlPanelSBTab.class.getName());

    public ControlPanelSBTab(ControlPanel c) {
        
        controlPanel = c;

        tab = Shuffleboard.getTab("Control Panel");

        red = tab.add("Red", 0).getEntry();
        green = tab.add("Green", 0).getEntry();
        blue = tab.add("Blue", 0).getEntry();
        detectedColor = tab.add("Detected Color", "None").getEntry();
        percentOutput = tab.add("Percent Output", 0.0).getEntry();
        //velocity = tab.add("Velocity", 20000).getEntry();
        //current = tab.add("Current", 0.0).getEntry();
        //position = tab.add("Encoder Position", 0.0).getEntry();
       // pValue = tab.add("P Value", controlPanel.getPValue()).getEntry();
       //iValue = tab.add("I Value", controlPanel.getIValue()).getEntry();
        //dValue = tab.add("D Value", controlPanel.getDValue()).getEntry();
       // encoderPosition = tab.add("Setting Encoder Position", false).withSize(1, 1).withPosition(0, 1).getEntry();
        stickStatus = tab.add("Stick Status", false).getEntry();
        brakeMode = tab.add("Brake Mode OnOff", false).getEntry();
        confidence = tab.add("Confidence", 0.0).getEntry();
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
        stickStatus.setBoolean(controlPanel.getStickStatus());

        controlPanel.setPercentOutput(percentOutput.getDouble(0.6));
        confidence.setDouble(controlPanel.getConfidenceValue());

        Color c = controlPanel.getColor();
        logger.info("red [" + c.red + "] green [" + green + "] blue [" + blue + "] confidence [" + controlPanel.getConfidenceValue() + "]");
    }
}

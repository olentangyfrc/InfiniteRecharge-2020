/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel;

/**
 * Add your docs here.
 */
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorMatchResult;

import java.util.logging.Logger;

import com.revrobotics.ColorMatch;

public class ControlPanel {
  private static Logger logger = Logger.getLogger(ControlPanel.class.getName());

    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

    private final ColorMatch m_colorMatcher = new ColorMatch();

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    private Color detectedColor;
    private ColorMatchResult match;
    private String colorString;

    private boolean logging = false;

    public void init() {
      logger.entering(ControlPanel.class.getName(), "init()");

        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);   
        
        logger.exiting(ControlPanel.class.getName(), "init()");
    }

    public void displayColors() {
        
        detectedColor = m_colorSensor.getColor();
    
        match = m_colorMatcher.matchClosestColor(detectedColor);
    
        if (match.color == kBlueTarget) {
          colorString = "Blue";
        } else if (match.color == kRedTarget) {
          colorString = "Red";
        } else if (match.color == kGreenTarget) {
          colorString = "Green";
        } else if (match.color == kYellowTarget) {
          colorString = "Yellow";
        } else {
          colorString = "Unknown";
        }


        //logger.info(String.format("Red [%f]\n", detectedColor.red));
      }

      public double getRedValue() {
        return detectedColor.red;
      }
      public double getGreenValue() {
        return detectedColor.green;
      }
      public double getBlueValue() {
        return detectedColor.blue;
      }
      public double getConfidenceValue() {
        return match.confidence;
      }
      public String getDetectedColor() {
        return colorString;
      }
      

    }

      

      /*
      public boolean isLogging(){
        logging = isLogging.getBoolean(false);
        return logging;
    }
    */


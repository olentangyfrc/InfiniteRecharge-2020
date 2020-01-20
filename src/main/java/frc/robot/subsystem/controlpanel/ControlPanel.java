/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel;

import java.util.logging.Logger;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OzoneException;
import frc.robot.subsystem.PortMan;

public class ControlPanel extends SubsystemBase {
    private static Logger logger = Logger.getLogger(ControlPanel.class.getName());

    private ColorSensorV3 m_colorSensor ; 
    private TalonSRX motor;
    private int oneRevolution; // the number of clicks for one entire revolution
    private int currentSpinnerPosition;
    private int targetSpinnerPosition;

    private final ColorMatch m_colorMatcher = new ColorMatch();
    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    private Color detectedColor;
    private ColorMatchResult match;
    private String colorString;




    public void init(PortMan portMan) throws Exception {
      logger.entering(ControlPanel.class.getName(), "init()");

      m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

      m_colorMatcher.addColorMatch(kBlueTarget);
      m_colorMatcher.addColorMatch(kGreenTarget);
      m_colorMatcher.addColorMatch(kRedTarget);
      m_colorMatcher.addColorMatch(kYellowTarget);   

      detectedColor = m_colorSensor.getColor();
      match = m_colorMatcher.matchClosestColor(detectedColor);
      colorString = "None";

      motor = new TalonSRX(portMan.acquirePort(PortMan.can_18_label, "ControlPanel.spinner"));
      logger.exiting(ControlPanel.class.getName(), "init()");

    }
  
    public void spin(int spinNum) {
      logger.info("spinning");
    
      currentSpinnerPosition = motor.getSelectedSensorPosition();
      targetSpinnerPosition = currentSpinnerPosition + (spinNum * oneRevolution);
      motor.setSelectedSensorPosition(targetSpinnerPosition);
    }

    public void goToColor() {
      logger.info("goToColor");

        detectedColor = m_colorSensor.getColor();
    
        match = m_colorMatcher.matchClosestColor(detectedColor);
    
        if (match.color == kBlueTarget) {
          motor.set(ControlMode.PercentOutput, .5);
          colorString = "Blue";
        } else if (match.color == kRedTarget) {
          motor.set(ControlMode.PercentOutput, 0);
          colorString = "Red";
        } else if (match.color == kGreenTarget) {
          motor.set(ControlMode.PercentOutput, 0);
          colorString = "Green";
        } else if (match.color == kYellowTarget) {
          motor.set(ControlMode.PercentOutput, 0);
          colorString = "Yellow";
        } else {
          motor.set(ControlMode.PercentOutput, 0);
          colorString = "Unknown";
        }
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
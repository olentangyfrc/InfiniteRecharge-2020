/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.controlpanel;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OzoneException;
import frc.robot.subsystem.PortMan;
import frc.robot.subsystem.telemetry.Telemetry;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ControlPanel extends SubsystemBase {
    private static Logger logger = Logger.getLogger(ControlPanel.class.getName());

    private ColorSensorV3 m_colorSensor ; 
    private PWMTalonSRX motor;
    private final ColorMatch m_colorMatcher = new ColorMatch();
    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    private ColorMatchResult match;
    private String colorString;

    private Telemetry telemetry;
    private int targetDistance = 0;
    private Color targetColor;

    private double velocity;
    private double percentOutputRotations;
    private double percentOutputColor;
    private boolean brakeOnOff;

    private double pValue;
    private double iValue;
    private double dValue;

    private boolean isControlSpinnerUp;
    private DoubleSolenoid pusher;

    public void init(PortMan portMan, Telemetry t) throws Exception {
      logger.entering(ControlPanel.class.getName(), "init()");

      m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

      m_colorMatcher.addColorMatch(kBlueTarget);
      m_colorMatcher.addColorMatch(kGreenTarget);
      m_colorMatcher.addColorMatch(kRedTarget);
      m_colorMatcher.addColorMatch(kYellowTarget);   

      
      colorString = "None";

      motor = new PWMTalonSRX(portMan.acquirePort(PortMan.pwm9_label, "ControlPanel.spinner"));

      telemetry = t;

      pValue = .2;
      iValue = 0;
      dValue = .2;

      percentOutputRotations = .5;
      percentOutputColor = .2;

      /*
      motor.enableCurrentLimit(true);
      motor.configPeakCurrentLimit(30);
      motor.configContinuousCurrentLimit(20);
      motor.configPeakCurrentDuration(400);
      motor.configAllowableClosedloopError(0, 5);
      motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
      motor.setSelectedSensorPosition(0, 0, 0);

      motor.config_kP(0, pValue, 0);
      motor.config_kI(0, iValue, 0);
      motor.config_kD(0, dValue, 0);
      motor.config_kF(0, 0, 0);

      motor.configMotionCruiseVelocity(4500);
      motor.configMotionAcceleration(4096);
      */

      velocity = 20000;

      pusher = new DoubleSolenoid(portMan.acquirePort(PortMan.pcm0_label, "Stick.inDoubleSolenoidx"), portMan.acquirePort(PortMan.pcm1_label, "Stick.outDoubleSolenoidx"));

      isControlSpinnerUp = false;

    logger.exiting(ControlPanel.class.getName(), "exiting init");

    }

    public double getRedValue() {
      return m_colorSensor.getColor().red;
    }
    public double getGreenValue() {
      return m_colorSensor.getColor().green;
    }
    public double getBlueValue() {
      return m_colorSensor.getColor().blue;
    }
    public double getConfidenceValue() {
      return m_colorMatcher.matchClosestColor(m_colorSensor.getColor()).confidence;
    }
    public String convertColorToString(Color c){
      if (c.equals(kBlueTarget))
        return "Blue";
      else if (c.equals(kRedTarget))
        return "Red";
      else if (c.equals(kYellowTarget))
        return "Yellow";
      else if (c.equals(kGreenTarget))
        return "Green";
      else
        return "Unknown";
    }

   
      public String getDetectedColor() {
        return convertColorToString(m_colorMatcher.matchClosestColor(m_colorSensor.getColor()).color);
      }
      public Color getColor(){
        return m_colorSensor.getColor();
      }
      /*
      public double getCurrent(){
        return motor.getSupplyCurrent();
      }
      public double getVelocity() {
        return motor.getSelectedSensorVelocity();
      }
      public double getPosition(){
        return motor.getSelectedSensorPosition();
      }
      */
      public boolean getStickStatus() {
        return isControlSpinnerUp;
      }

      public void changePID(double p, double i, double d){
       if(p != pValue){
         pValue = p;
       }
       if(i != iValue){
         iValue = i;
       }
       if(d != dValue){
         dValue = d;
       }
      }
      /*
      public void setZero(){
        motor.setSelectedSensorPosition(0);
      }
      */
      public void setPercentOutput(double sp){
        percentOutputRotations = sp;
      }
      public double getPercentOutput(){
        return percentOutputRotations;
      }
      public void spinRotations(){
        motor.set(percentOutputRotations);
      }
      public void spinColor(){
        motor.set(percentOutputColor);
      }
      public void spin(){
        motor.set(.7);
      }
      public void stop(){
        motor.set(0.0);
      }
      public void setBrakeMode(boolean on){
        /*
        if (on == true){
          motor.setNeutralMode(NeutralMode.Brake);
          brakeOnOff = true;
        }
        else{
          motor.setNeutralMode(NeutralMode.Coast);
          brakeOnOff = false;
        }
        */
      }
      /*
      public void spinMotionMagic(int magicNumber) {
        motor.set(ControlMode.MotionMagic, magicNumber);
      }
      */
      public boolean getBrakeOnOff(){
        return brakeOnOff;
      }
      public void rotate(int number) {
        Color startColor = m_colorSensor.getColor();
        Color pastColor = startColor;
        int count = 0;
        motor.set(0.2);
        do
        {
          Color color = m_colorSensor.getColor();
          if(pastColor != color && color == startColor)
          {
            count++;
          }
          pastColor = color;
        }
        while(count < number);
        motor.set(0);
      }

      public void pushColorSpinnerUp() {
        pusher.set(DoubleSolenoid.Value.kForward);

        isControlSpinnerUp = true;
      }
        
      public void retractPistons() {
        pusher.set(DoubleSolenoid.Value.kReverse);

        isControlSpinnerUp = false;
      }

      public double getPValue(){
        return pValue;
      }
      public double getIValue(){
        return iValue;
      }
      public double getDValue(){
        return dValue;
      }
      
    }
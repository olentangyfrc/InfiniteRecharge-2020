/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystem.DisplayManager;
import frc.robot.subsystem.PortMan;
import frc.robot.subsystem.SubsystemFactory;
import frc.robot.subsystem.commandgroups.ScoringAuton;
import frc.robot.subsystem.commandgroups.SimpleAuton;
import frc.robot.subsystem.controlpanel.ControlPanel;
import frc.robot.util.OzoneLogger;

import frc.robot.subsystem.SBInterface;
import frc.robot.subsystem.controlpanel.ControlPanelSBTab;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  static Logger logger = Logger.getLogger(Robot.class.getName());
  ControlPanel controlPanel;
  private static SubsystemFactory subsystemFactory;

  private DisplayManager dManager;
  private NetworkTableEntry simpleAuton;
  private ShuffleboardTab tab;

  private Auton test;
  private SequentialCommandGroup autonCommand;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    subsystemFactory = SubsystemFactory.getInstance();

    tab = Shuffleboard.getTab("Auton");
    simpleAuton = tab.add("Simple Auton", true).getEntry();

    OzoneLogger.getInstance().init(Level.FINE);
    dManager = new DisplayManager();

    try {
      subsystemFactory.init(dManager, new PortMan());

    } catch (Exception e) {
      StringWriter writer = new StringWriter();
      PrintWriter pw  = new PrintWriter(writer);
      e.printStackTrace(pw);
      logger.severe(writer.toString());
    }

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
       CommandScheduler.getInstance().run();
       //dManager.update();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().run();
    test = new Auton(0.3);
    test.initialize();


    if(simpleAuton.getBoolean(true))
      autonCommand = new SimpleAuton();
    else{
      autonCommand = new ScoringAuton(
        SubsystemFactory.getInstance().getTransport(), 
        SubsystemFactory.getInstance().getIntake(),
        SubsystemFactory.getInstance().getControlPanel(), 
        SubsystemFactory.getInstance().getShooter());
    }

    CommandScheduler.getInstance().schedule(autonCommand);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();

    //test.execute();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}

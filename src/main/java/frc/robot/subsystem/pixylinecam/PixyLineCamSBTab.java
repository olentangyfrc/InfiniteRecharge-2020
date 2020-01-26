/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.pixylinecam;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystem.SBInterface;

public class PixyLineCamSBTab implements SBInterface{
    private PixyLineCam pixyLineCam;

    private ShuffleboardTab tab;

    private NetworkTableEntry x0;
    private NetworkTableEntry y0;
    private NetworkTableEntry x1;
    private NetworkTableEntry y1;
    private NetworkTableEntry index;
    private NetworkTableEntry flags;
    private NetworkTableEntry arrayNumber;
    private NetworkTableEntry slope;
    private NetworkTableEntry angle;
    private NetworkTableEntry leftStatus;
    private NetworkTableEntry middleStatus;
    private NetworkTableEntry rightStatus;
    

    public PixyLineCamSBTab(PixyLineCam c) {
        pixyLineCam = c;

        tab = Shuffleboard.getTab("Pixy Line Camera");

        x0 = tab.add("Pixy x0", 0).getEntry();
        y0 = tab.add("Pixy y0", 0).getEntry();
        x1 = tab.add("Pixy x1", 0).getEntry();
        y1 = tab.add("Pixy y1", 0).getEntry();
        flags = tab.add("Pixy Flags", 0).getEntry();
        index = tab.add("Pixy Index", 0).getEntry();
        arrayNumber = tab.add("Number of Verticals", 0).getEntry();
        slope = tab.add("Vector Slope", 0).getEntry();
        angle = tab.add("Vector Angle", 0).getEntry();
        leftStatus = tab.add("Left", false).getEntry();
        middleStatus = tab.add("MIDDLE !!!!!!!", false).getEntry();
        rightStatus = tab.add("Right", false).getEntry();
  
    }


    /**
     * get data from subsystem and update SB widgets
     * get data from SB widgets and update subsystem
     */
    public void update() {
        x0.setNumber(pixyLineCam.getX0());
        y0.setNumber(pixyLineCam.getY0());
        x1.setNumber(pixyLineCam.getX1());
        y1.setNumber(pixyLineCam.getY1());
        index.setNumber(pixyLineCam.getIndex());
        flags.setNumber(pixyLineCam.getFlags());
        arrayNumber.setNumber(pixyLineCam.getArrayNumber());
        slope.setNumber(pixyLineCam.getSlope());
        angle.setDouble(pixyLineCam.getAngle());
        leftStatus.setBoolean(pixyLineCam.getLeftStatus());
        rightStatus.setBoolean(pixyLineCam.getRightStatus());
        middleStatus.setBoolean(pixyLineCam.getMiddleStatus());
    }
}

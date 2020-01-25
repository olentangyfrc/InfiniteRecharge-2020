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
import io.github.pseudoresonance.pixy2api.Pixy2Line;

public class PixyLineCamSBTab {
    private PixyLineCam pixyLineCam;
    private Pixy2Line line;
    
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
        x0.setInt(line.getX0());
        y0.setNumber(line.getY0());
        x1.setNumber(line.getX1());
        y1.setNumber(line.getY1());
        index.setNumber(line.getIndex());
        flags.setNumber(line.getFlags());
        arrayNumber.setNumber(number);

        //calculating slope
        int vectorSlope = (line.getY0()-line.getY1())/(line.getX0()-line.getX1());
        slope.setNumber(vectorSlope);

        //calculating angles
        double ratio = (double) (line.getX1()-line.getX0())/(line.getY1()-line.getY0());//Math.toDegrees(Math.atan((line.getX0()-line.getX1())/(line.getY0()-line.getY1())));
        double vectorAngle = Math.toDegrees(Math.atan(ratio));
        angle.setDouble(vectorAngle);

        //identifying relative location
        int averageX = (line.getX0() + line.getX1())/2;
        int leftX = 37;
        int rightX = 41;
        boolean left = false;
        boolean middle = false;
        boolean right = false;
        if (averageX < leftX || averageX < leftX) {
            left = true;
        } else if (averageX > rightX || averageX > rightX) {
            right = true;
        } else {
            middle = true;
        }
        leftStatus.setBoolean(left);
        rightStatus.setBoolean(right);
        middleStatus.setBoolean(middle);
    }
}

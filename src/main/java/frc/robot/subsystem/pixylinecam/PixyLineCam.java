//ADD TO SUBSYSTEM FACTORY

package frc.robot.subsystem.pixylinecam;

import java.util.logging.Logger;

import frc.robot.subsystem.PortMan;
//import frc.robot.networktables.NetTableManager;
import frc.robot.subsystem.pixylinecam.commands.PollPixyLine;
//import org.usfirst.frc.team4611.robot.subsystems.vision.commands.PollNetworkTable;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2Line;

public class PixyLineCam extends SubsystemBase{

    private static Logger logger = Logger.getLogger(PixyLineCam.class.getName());

    private Pixy2 pixy;
    private Pixy2Line line;
    private PixyLineCam pixyLineCam;
    private double x0;
    private double y0;
    private double x1;
    private double y1;
    private double index;
    private double flags;
    private int arrayNumber;
    private int slope;
    private double angle;
    private boolean leftStatus;
    private boolean middleStatus;
    private boolean rightStatus;
    
    public PixyLineCam() {
    }

    public void resetPixyLine() {
        x0 = 0;
        y0 = 0;
        x1 = 0;
        y1 = 0;
        index = 0;
        flags = 0;
        arrayNumber = 0;
        slope = 0;
        angle = 0;
        leftStatus = false;
        middleStatus = false;
        rightStatus = false;


    }

    public void init(PortMan portMan) {
        logger.info("initializing");
        logger.info("Creating Pixy with link type of SPI");
        pixy = Pixy2.createInstance(Pixy2.LinkType.SPI);
        pixy.init(0);
        logger.info("Here comes the version! :) " + pixy.getVersionInfo().toString());
        line = pixy.getLine();

       /* x0 = tab.add("Pixy x0", 0).getEntry();
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
        */

      }


    public Pixy2Line getLine() {
        return this.line;
    }



    public void writeLine(Pixy2Line.Vector line, int number) {
        x0 = line.getX0();
        y0 = line.getY0();
        x1 = line.getX1();
        y1 = line.getY1();
        index = line.getIndex();
        flags = line.getFlags();
        arrayNumber = number; 

        //calculating slope
        int vectorSlope = (line.getY0()-line.getY1())/(line.getX0()-line.getX1());
        slope = vectorSlope;

        //calculating angles
        double ratio = (double) (line.getX1()-line.getX0())/(line.getY1()-line.getY0());//Math.toDegrees(Math.atan((line.getX0()-line.getX1())/(line.getY0()-line.getY1())));
        double vectorAngle = Math.toDegrees(Math.atan(ratio));
        angle = vectorAngle;

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
        leftStatus = left;
        rightStatus = right;
        middleStatus = middle;

    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getIndex() {
        return index;
    }

    public double getFlags() {
        return flags;
    }

    public int getArrayNumber() {
        return arrayNumber;
    }

    public int getSlope() {
        return slope;
    }

    public double getAngle() {
        return angle;
    }

    public boolean getLeftStatus() {
        return leftStatus;
    }

    public boolean getMiddleStatus() {
        return middleStatus;
    }

    public boolean getRightStatus() {
        return rightStatus;
    }

     protected void initDefaultCommand() {
        setDefaultCommand(new PollPixyLine(pixyLineCam));
    } 

}
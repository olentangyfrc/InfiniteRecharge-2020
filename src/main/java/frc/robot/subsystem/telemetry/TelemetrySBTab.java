/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.telemetry;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystem.SBInterface;

/**
 * Add your docs here.
 */
public class TelemetrySBTab implements SBInterface {
    public Telemetry telemetry;
    public ShuffleboardTab tab;

    public NetworkTableEntry frontDistance;
    public NetworkTableEntry rearDistance;
    public NetworkTableEntry isSquare;
    public NetworkTableEntry tolerance;

    public TelemetrySBTab(Telemetry te){
        telemetry = te;
        
        tab = Shuffleboard.getTab("Telemetry");

        frontDistance = tab.add("Front Lidar Distance", 0).getEntry();
        rearDistance = tab.add("Rear Lidar Distance", 0).getEntry();
        isSquare = tab.add("Is Squared?", false).getEntry();
        tolerance = tab.add("Lidar Tolerance", 0.0).getEntry();
    }
    public void update(){
        isSquare.setBoolean(telemetry.isSquare());
        frontDistance.setDouble(telemetry.getFrontLidarDistance());
        rearDistance.setDouble(telemetry.getRearLidarDistance());
        isSquare.setBoolean(telemetry.isSquare(5));
        tolerance.setDouble(telemetry.getTolerance());
        telemetry.setTolerance(tolerance.getDouble(5.0));
    }
}

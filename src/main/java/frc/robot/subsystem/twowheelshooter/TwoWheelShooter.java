/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.twowheelshooter;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.PortMan;

public class TwoWheelShooter extends SubsystemBase {
    private static Logger logger = Logger.getLogger(TwoWheelShooter.class.getName());
    private TalonSRX leftMotor;
    private TalonSRX rightMotor;
    
    public void init(PortMan portMan) throws Exception {
        leftMotor = new TalonSRX(portMan.acquirePort(PortMan.can_49_label, "TwoWheelShooter.leftMotor"));
        rightMotor = new TalonSRX(portMan.acquirePort(PortMan.can_50_label, "TwoWheelShooter.rightMotor"));

        // TODO: read CTRE documentation on how to set up motors that follow 
    }
    
    public void shoot(){
        logger.info("shoot");
    }

    public void stop(){
        logger.info("stop");
    }
}
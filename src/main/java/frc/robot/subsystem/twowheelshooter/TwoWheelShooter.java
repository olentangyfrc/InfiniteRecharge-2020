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
    private final TalonSRX leftMotor = new TalonSRX(49);
    private final TalonSRX rightMotor = new TalonSRX(50);
    
    public void init(PortMan portMan) throws Exception {

    }
    
    public void shoot(){
        logger.info("shoot");
    }

    public void stop(){
        logger.info("stop");
    }
}


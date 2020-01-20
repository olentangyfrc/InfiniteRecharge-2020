/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.onewheelshooter;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.subsystem.PortMan;

/**
 * Add your docs here.
 */
public class OneWheelShooter extends SubsystemBase{
    
    private static Logger logger = Logger.getLogger(OneWheelShooter.class.getName());
    private final TalonSRX Motor = new TalonSRX(57);
    
    public void init(final PortMan portMan) throws Exception {

    }
    
    public void shoot(){
        logger.info("1shoot");
    }

    public void stop(){
        logger.info("1stop");
    }
}

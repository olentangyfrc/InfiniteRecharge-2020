/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.Shooter;

import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class shooter {
    private final TalonSRX leftShooter = new TalonSRX(57);
    private final TalonSRX rightShooter = new TalonSRX(58);

    public void shoot(){
        leftShooter.set(ControlMode.Velocity, 20);
        rightShooter.set(ControlMode.Velocity, 20);
    }


}

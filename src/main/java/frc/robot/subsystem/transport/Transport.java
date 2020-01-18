package frc.robot.subsystem.transport;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.subsystem.PortMan;

public class Transport {
    
    private static Logger logger = Logger.getLogger(Transport.class.getName());

    private final TalonSRX leftIntake = new TalonSRX(57);
    private final TalonSRX rightIntake = new TalonSRX(58);

    private double motorSpeedForward = .5;
    private double motorSpeedBackward = .5;
    private double ballCount = 0;

    private boolean logging = false;

    public void init(PortMan portMan) throws Exception {
        logger.entering(Transport.class.getName(), "init()");

        leftIntake.config_kP(0, .5, 0);
        leftIntake.config_kI(0, 0, 0);
        leftIntake.config_kD(0, 0, 0);
        leftIntake.config_kF(0, 0, 0);
        leftIntake.configMotionCruiseVelocity(4096, 0);
        leftIntake.configMotionAcceleration(4096,0);

        rightIntake.config_kP(0, .5, 0);
        rightIntake.config_kI(0, 0, 0);
        rightIntake.config_kD(0, 0, 0);
        rightIntake.config_kF(0, 0, 0);
        rightIntake.configMotionCruiseVelocity(4096, 0);
        rightIntake.configMotionAcceleration(4096,0);

        rightIntake.follow(leftIntake);
        rightIntake.setInverted(true);

        logger.exiting(Transport.class.getName(), "init()");
    }

    public boolean isLogging(){
        return logging;
    }

    public void take(){
        leftIntake.set(ControlMode.PercentOutput, motorSpeedForward);
    }

    public void stop(){
        leftIntake.set(ControlMode.Velocity, 0);
    }

    public int count(){
        return 0;
    }

    public void expel(){
        leftIntake.set(ControlMode.PercentOutput, -motorSpeedBackward);
    }

    public double getVelocity(){
        return leftIntake.getSelectedSensorVelocity();
    }

    public double getBallCount(){
        return ballCount;
    }

}

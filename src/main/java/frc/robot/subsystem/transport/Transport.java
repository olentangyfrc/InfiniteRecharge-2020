package frc.robot.subsystem.transport;

import java.util.logging.Logger;

import javax.xml.namespace.QName;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OzoneException;
import frc.robot.subsystem.PortMan;

import edu.wpi.first.wpilibj.DigitalInput;

public class Transport extends SubsystemBase {

    private static final Double Repeatable = null;

    private static Logger logger = Logger.getLogger(Transport.class.getName());

    private CANSparkMax leftIntake;
    private CANSparkMax rightIntake;
    private AnalogInput intakeSensor;
    private CANPIDController leftPid;
    private CANPIDController rightPid;
    private AnalogInput exitSensor;
    private MedianFilter filter;
    private double motorSpeedForward = .2;
    private double motorSpeedBackward = .2;

    private int ballCount = 0;
    private boolean pastValue1 = false;
    private boolean pastValue2 = false;
    private DigitalInput enterSwitch;
    private DigitalInput exitSwitch;
    //private Counter ballCount;
    public Transport() {
    }

    public void init(PortMan portMan) throws Exception {
        logger.entering(Transport.class.getName(), "init()");

        enterSwitch = new DigitalInput(portMan.acquirePort(PortMan.digital0_label, "Transport.IntakeEnterSensor"));
        exitSwitch = new DigitalInput(portMan.acquirePort(PortMan.digital1_label, "Transport.IntakeExitSensor"));
        leftIntake = new CANSparkMax(portMan.acquirePort(PortMan.can_57_label, "Transport.transportSparkMax1"), com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
        rightIntake = new CANSparkMax(portMan.acquirePort(PortMan.can_58_label, "Transport.transportSparkMax2"), com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
        leftPid = leftIntake.getPIDController();
        rightPid = rightIntake.getPIDController();
        //ballCount = new Counter(Counter.Mode.kSemiperiod);

        leftIntake.restoreFactoryDefaults();
        rightIntake.restoreFactoryDefaults();

        leftPid.setP(.1);
        leftPid.setI(0);
        leftPid.setD(0);
        leftPid.setIZone(0);
        leftPid.setFF(0);
        leftPid.setOutputRange(-1, 1);

        rightPid.setP(0.1);
        rightPid.setI(0);
        rightPid.setD(0);
        rightPid.setIZone(0);
        rightPid.setFF(0);
        rightPid.setOutputRange(-1, 1);

        /* Talon Code
        leftIntake.config_kP(0, .5, 0);
        leftIntake.config_kI(0, 0, 0);
        leftIntake.config_kD(0, 0, 0);
        leftIntake.config_kF(0, 0, 0);
        leftIntake.configMotionCruiseVelocity(4096, 0);
        leftIntake.configMotionAcceleration(4096, 0);

        rightIntake.config_kP(0, .5, 0);
        rightIntake.config_kI(0, 0, 0);
        rightIntake.config_kD(0, 0, 0);
        rightIntake.config_kF(0, 0, 0);
        rightIntake.configMotionCruiseVelocity(4096, 0);
        rightIntake.configMotionAcceleration(4096, 0);

        rightIntake.follow(leftIntake);
        rightIntake.setInverted(true);
        */

        //ballCount.setUpSource(enterSwitch);
        //ballCount.setSemiPeriodMode(true);

        logger.exiting(Transport.class.getName(), "init()");
    }

    public void take() {
        logger.info("take");
        //leftIntake.set(ControlMode.PercentOutput, motorSpeedForward);
        //leftPid.setReference(motorSpeedForward, ControlType.kVelocity);
        leftIntake.set(motorSpeedForward);
        rightIntake.set(motorSpeedForward);
    }

    public void stop() {
        logger.info("stop");

        leftIntake.set(0);
        rightIntake.set(0);
    }

    public int count() {
        return 0;
    }

    public void expel() {
        logger.info("expel");
        leftIntake.set(-motorSpeedBackward);
        rightIntake.set(-motorSpeedBackward);
    }

    public double getVelocity() {
        return leftIntake.get();
    }

    public int getBallCount() {
        if(getDigitalInput1() == true && pastValue1 == false){
            ballCount++;
        }
        if(getDigitalInput2() == true && pastValue2 == false){
            ballCount--;
        }
        pastValue1 = getDigitalInput1();
        pastValue2 = getDigitalInput2();
        return ballCount;
    }

    public boolean getDigitalInput1(){
        return enterSwitch.get();
    }

    public boolean getDigitalInput2(){
        return exitSwitch.get();
    }
    
    public void update(){
        
    }
}

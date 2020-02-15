package frc.robot.subsystem.transport;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.PortMan;

import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Transport extends SubsystemBase {

    private static Logger logger = Logger.getLogger(Transport.class.getName());

    private DoubleSolenoid doubleSolenoidLeft;
    private DoubleSolenoid doubleSolenoidRight;
    private TalonSRX leftIntake;
    private TalonSRX rightIntake;
    private double motorSpeedForward = .2;
    private double motorSpeedBackward = .2;

    private int ballCount = 0;
    private boolean pastValue1 = false;
    private boolean pastValue2 = false;
    private DigitalInput enterSwitch;
    private DigitalInput exitSwitch;

    private boolean gateUp;

    //private Counter ballCount;
    public Transport() {
    }

    // shooter gate pcm 6, pcm 7
    // statue of liberty pcm 0, pcm 1
    public void init(PortMan portMan) throws Exception {
        logger.entering(Transport.class.getName(), "init()");

        // intake
        doubleSolenoidLeft = new DoubleSolenoid(portMan.acquirePort(PortMan.pcm4_label, "Transport.gate2"), portMan.acquirePort(PortMan.pcm5_label, "Transport.gate3"));
        // tailgate
        doubleSolenoidRight = new DoubleSolenoid(portMan.acquirePort(PortMan.pcm2_label, "Transport.gate4"), portMan.acquirePort(PortMan.pcm3_label, "Transport.gate5"));
        gateUp = false;
        
        enterSwitch = new DigitalInput(portMan.acquirePort(PortMan.digital0_label, "Transport.IntakeEnterSensor"));
        exitSwitch = new DigitalInput(portMan.acquirePort(PortMan.digital1_label, "Transport.IntakeExitSensor"));

        leftIntake = new TalonSRX(portMan.acquirePort(PortMan.can_24_label, "Transport.LeftIntake"));
        rightIntake = new TalonSRX(portMan.acquirePort(PortMan.can_26_label, "Transport.RightIntake"));
        //ballCount = new Counter(Counter.Mode.kSemiperiod)

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

        //ballCount.setUpSource(enterSwitch);
        //ballCount.setSemiPeriodMode(true);

        logger.exiting(Transport.class.getName(), "init()");
    }

    public void moveSideGateOpen(){
        doubleSolenoidLeft.set(DoubleSolenoid.Value.kForward);
        gateUp = true;
    }
    public void moveSideGateClose(){
      doubleSolenoidLeft.set(DoubleSolenoid.Value.kReverse);
      gateUp = false;
    }
    public void moveTailGateUp(){
        doubleSolenoidRight.set(DoubleSolenoid.Value.kForward);
    }
    public void moveTailGateDown(){
        doubleSolenoidRight.set(DoubleSolenoid.Value.kReverse);
    }
    public boolean getGateStatus(){
      return gateUp;
    }

    public void take() {
        logger.info("take");
        leftIntake.set(ControlMode.PercentOutput, motorSpeedForward);
        rightIntake.set(ControlMode.PercentOutput, -motorSpeedForward);
    }

    public void expel() {
        logger.info("expel");
        leftIntake.set(ControlMode.PercentOutput, -motorSpeedBackward);
        rightIntake.set(ControlMode.PercentOutput, motorSpeedBackward);
    }

    public void stop() {
        logger.info("stop");

        leftIntake.set(ControlMode.PercentOutput,0);
        rightIntake.set(ControlMode.PercentOutput,0);
    }

    public int count() {
        return 0;
    }

    public double getVelocity() {
        return leftIntake.getSelectedSensorVelocity();
    }

    public int getBallCount() {
        if(getDigitalInput1() == true && pastValue1 == false){
            ballCount++;
        }
        if(getDigitalInput2() == true && pastValue2 == false){
            ballCount++;
        }
        pastValue1 = getDigitalInput1();
        pastValue2 = getDigitalInput2();
        if(ballCount >= 5){
            leftIntake.set(ControlMode.PercentOutput,0);
            rightIntake.set(ControlMode.PercentOutput,0);
        }
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

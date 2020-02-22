package frc.robot.subsystem.transport;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.PortMan;
import frc.robot.subsystem.SubsystemFactory;
import frc.robot.subsystem.transport.commands.TakeIn;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Transport extends SubsystemBase {

    private static Logger logger = Logger.getLogger(Transport.class.getName());

    private DoubleSolenoid doubleSolenoidLeft;
    private DoubleSolenoid doubleSolenoidRight;
    private PWMTalonSRX bottomTransport;
    private PWMTalonSRX topTransport;
    private double motorSpeedForward = .5;
    private double motorSpeedBackward = .5;

    private int ballCount = 0;
    private boolean pastValue1 = false;
    private boolean pastValue2 = false;
    private DigitalInput transportReceiverSwitch;
    private DigitalInput transportSenderSwitch;

    private boolean gateUp;
    private double transportTime;

    private PowerDistributionPanel pdp;

    //private Counter ballCount;
    public Transport() {
    }

    public void init(PortMan portMan) throws Exception {
        logger.entering(Transport.class.getName(), "init()");

        // sideGate
        doubleSolenoidLeft = new DoubleSolenoid(portMan.acquirePort(PortMan.pcm6_label, "Transport.gate2"), portMan.acquirePort(PortMan.pcm7_label, "Transport.gate3"));
        // tailgate
        doubleSolenoidRight = new DoubleSolenoid(portMan.acquirePort(PortMan.pcm2_label, "Transport.gate4"), portMan.acquirePort(PortMan.pcm3_label, "Transport.gate5"));
        gateUp = false;
        
        transportReceiverSwitch = new DigitalInput(portMan.acquirePort(PortMan.digital0_label, "Transport.IntakeEnterSensor"));
        transportSenderSwitch = new DigitalInput(portMan.acquirePort(PortMan.digital1_label, "Transport.IntakeExitSensor"));

        bottomTransport = new PWMTalonSRX(portMan.acquirePort(PortMan.pwm6_label, "Transport.LeftIntake"));
        topTransport = new PWMTalonSRX(portMan.acquirePort(PortMan.pwm7_label, "Transport.RightIntake"));
        //ballCount = new Counter(Counter.Mode.kSemiperiod)

        /*
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
        */

        transportTime = 0.0;

        //ballCount.setUpSource(enterSwitch);
        //ballCount.setSemiPeriodMode(true);

        setDefaultCommand(new TakeIn(this));

        logger.exiting(Transport.class.getName(), "init()");
    }

    public void moveSideGateOpen(){
        doubleSolenoidLeft.set(DoubleSolenoid.Value.kReverse);
        gateUp = true;
    }
    public void moveSideGateClose(){
      doubleSolenoidLeft.set(DoubleSolenoid.Value.kForward);
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
    public void shoot(){
        bottomTransport.set(.7);
        topTransport.set(-.7);
    }

    public void take() {
        logger.info("take");
        //bottomTransport.set(motorSpeedForward);
        topTransport.set(-motorSpeedForward);
    }

    public void expel() {
        logger.info("expel");
        bottomTransport.set(-motorSpeedBackward);
        topTransport.set(motorSpeedBackward);
    }

    public void stop() {

        bottomTransport.set(0);
        topTransport.set(0);
    }

    public int count() {
        return 0;
    }
    /*
    public double getVelocity() {
        return leftIntake.getSelectedSensorVelocity();
    }
    */

    public int getBallCount() {
        if(getTransportReceiverSwitch() == true && pastValue1 == false){
            ballCount++;
        }
        if(getTransportSendserSwitch() == true && pastValue2 == false){
            ballCount++;
        }
        pastValue1 = getTransportReceiverSwitch();
        pastValue2 = getTransportSendserSwitch();
        /*
        if(ballCount >= 5){
            leftIntake.set(ControlMode.PercentOutput,0);
            rightIntake.set(ControlMode.PercentOutput,0);
        }
        */
        return ballCount;
    }

    public boolean getTransportReceiverSwitch(){
        return transportReceiverSwitch.get();
    }

    public boolean getTransportSendserSwitch(){
        return transportSenderSwitch.get();
    }
    
    public void update(){
        
    }
    public double getTime(){
        return Timer.getFPGATimestamp();
    }
    public void setTargetTime(double a){
        transportTime = a;
    }
    public double getTargetTime(){
        return transportTime;
    }
    public void setMotorSpeedForward(double a){
        motorSpeedForward = a;
    }
    public void setMotorSpeedBackward(double a){
        motorSpeedBackward = a;
    }
    public double getMotorSpeedForward(){
        return motorSpeedForward;
    }
    public double getMotorSpeedBackward(){
        return motorSpeedBackward;
    }
    /*
    public double getLeftIntakeCurrent(){
        return SubsystemFactory.getInstance().getPDP().getCurrent(4);
    }
    public double getRightIntakeCurrent(){
        return SubsystemFactory.getInstance().getPDP().getCurrent(1);
    }
    */
}

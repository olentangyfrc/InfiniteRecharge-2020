package frc.robot.subsystem.climber;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.PortMan;

public class Climber extends SubsystemBase {
    private static Logger logger = Logger.getLogger(Climber.class.getName());
    private final TalonSRX leftIntake = new TalonSRX(57);
    
    public void init(PortMan portMan) throws Exception {

    }
    
    public void extend(){
        logger.info("extend");
    }

    public void retract(){
        logger.info("retract");
    }
}

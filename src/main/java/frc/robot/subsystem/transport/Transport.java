package frc.robot.subsystem.transport;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Transport {
    private final TalonSRX leftIntake = new TalonSRX(57);
    private final TalonSRX rightIntake = new TalonSRX(58);

    public void take(){
    
    }

    public void stop(){

    }

    public int count(){
        return 0;
    }

    public void expel(){

    }

}

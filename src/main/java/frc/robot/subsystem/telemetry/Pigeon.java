package frc.robot.subsystem.telemetry;

import com.ctre.phoenix.sensors.PigeonIMU;
import org.frcteam2910.common.drivers.Gyroscope;
import org.frcteam2910.common.math.Rotation2;
import org.frcteam2910.common.robot.drivers.NavX.Axis;

public final class Pigeon extends Gyroscope {
    private PigeonIMU pigeon;

    public Pigeon(int canPort) {
        pigeon = new PigeonIMU(canPort);
    }

    @Override
    public void calibrate() {
        pigeon.setFusedHeading(0, 20);
    }

    @Override
    public Rotation2 getUnadjustedAngle() {
        //return Rotation2.fromRadians(getAxis(Axis.YAW));
        return Rotation2.ZERO;
    }

    @Override
    public double getUnadjustedRate() {
        double[] xyz = new double[3];
        pigeon.getRawGyro(xyz);

        //return Math.toRadians(xyz[2]);
        return 0.0;
    }

    public double getAxis(Axis axis) {
      	double[] ypr = new double[3];
        pigeon.getYawPitchRoll(ypr);

        switch (axis) {
            case PITCH:
                return Math.toRadians(ypr[1]);
            case ROLL:
                return Math.toRadians(ypr[2]);
            case YAW:
                return Math.toRadians(ypr[0]);
            default:
                return 0.0;
        }
    }
}

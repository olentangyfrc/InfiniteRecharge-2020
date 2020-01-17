package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

/**
 * Simple wrapped of Exception that will put an exception on Shuffleboard
 */
public class OzoneException extends Exception {
    static final long serialVersionUID = 1;

    private static NetworkTableEntry exceptionMessageEntry;
    private static NetworkTableEntry exceptionIndicatorEntry;

    public OzoneException (String message) {
        super (message);

        /*if (exceptionMessageEntry == null) {
            exceptionMessageEntry = Shuffleboard.getTab("Health Tab").add("ExceptionMessage", "No Exception").getEntry();
            exceptionIndicatorEntry = Shuffleboard.getTab("Health Tab").add("ExceptionIndicator", true).getEntry();
        }
        exceptionMessageEntry.setString(message);
        exceptionIndicatorEntry.setBoolean(false);*/
    }
}
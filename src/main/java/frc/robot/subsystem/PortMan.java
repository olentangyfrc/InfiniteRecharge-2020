package frc.robot.subsystem;

import java.util.HashMap;
import java.util.logging.Logger;

import frc.robot.OzoneException;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Portman provides a control mechanism for subsystems to acquire ports for their
 * devices. It does it my keeping track of ports that have been allocated and throws
 * an exception if a port is requested more than once. It help to track down invalid port assignments
 * 
 * Usage of this would look something like the following in a hypothetical mecanum:
 * 
 * public class Mecanum {
 * TalonSRX frontRight;
 * 
 * public init() throws Exception {
 *  frontRight  = new TalonSRX(portMan.acquirePort(PortMan.digital0_label, "Mecanum.frontRight"));
 *
 */

public class PortMan {

    private static Logger logger = Logger.getLogger(PortMan.class.getName());
    private static ShuffleboardTab tab = Shuffleboard.getTab("PortMan");
    
	private HashMap <String, String> allocatedPorts;

    public static final String digital0_label   = "DIGITAL0";
    public static final String digital1_label   = "DIGITAL1";
    public static final String digital2_label   = "DIGITAL2";    
    public static final String digital3_label   = "DIGITAL3";
    public static final String digital4_label   = "DIGITAL4";
    public static final String digital5_label   = "DIGITAL5";
    public static final String digital6_label   = "DIGITAL6";
    public static final String digital7_label   = "DIGITAL7";
    public static final String digital8_label   = "DIGITAL8";
    public static final String digital9_label   = "DIGITAL9";
    public static final String digital10_label   = "DIGITAL10";
    public static final String digital11_label   = "DIGITAL11";
    public static final String digital12_label   = "DIGITAL12";
    public static final String digital13_label   = "DIGITAL13";
    public static final String digital14_label   = "DIGITAL14";
    public static final String digital15_label   = "DIGITAL15";
    public static final String digital16_label   = "DIGITAL16";
    public static final String digital17_label   = "DIGITAL17";
    public static final String digital18_label   = "DIGITAL18";
    public static final String digital19_label   = "DIGITAL19";
    public static final String digital20_label   = "DIGITAL20";

    public static final String analog0_label = "ANALOG0";
    public static final String analog1_label = "ANALOG1";
    public static final String analog2_label = "ANALOG2";
    public static final String analog3_label = "ANALOG3";
    public static final String analog4_label = "ANALOG4";
    public static final String analog5_label = "ANALOG5";

    public static final String relay0_label = "RELAY0";
    public static final String relay1_label = "RELAY1";
    public static final String relay2_label = "RELAY2";
    public static final String relay3_label = "RELAY3";

    public static final String pwm0_label = "PWM0";
    public static final String pwm1_label = "PWM1";
    public static final String pwm2_label = "PWM2";
    public static final String pwm3_label = "PWM3";
    public static final String pwm4_label = "PWM4";
    public static final String pwm5_label = "PWM5";
    public static final String pwm6_label = "PWM6";
    public static final String pwm7_label = "PWM7";
    public static final String pwm8_label = "PWM8";
    public static final String pwm9_label = "PWM9";

    public static final String pcm0_label = "PCM0";
	public static final String pcm1_label = "PCM1";
    public static final String pcm2_label = "PCM2";
    public static final String pcm3_label = "PCM3";
    public static final String pcm4_label = "PCM4";
    public static final String pcm5_label = "PCM5";
    public static final String pcm6_label = "PCM6";
    public static final String pcm7_label = "PCM7";


    public static final String can_01_label = "CAN01";
    public static final String can_02_label = "CAN02";
    public static final String can_03_label = "CAN03";
    public static final String can_04_label = "CAN04";
    public static final String can_05_label = "CAN05";
    public static final String can_06_label = "CAN06";
    public static final String can_07_label = "CAN07";
    public static final String can_08_label = "CAN08";
    public static final String can_09_label = "CAN09";
    public static final String can_10_label = "CAN10";
    public static final String can_11_label = "CAN11";
    public static final String can_12_label = "CAN12";
    public static final String can_13_label = "CAN13";
    public static final String can_14_label = "CAN14";
    public static final String can_15_label = "CAN15";
    public static final String can_16_label = "CAN16";
    public static final String can_17_label = "CAN17";
    public static final String can_18_label = "CAN18";
    public static final String can_19_label = "CAN19";
    public static final String can_20_label = "CAN20";
    public static final String can_21_label = "CAN21";
    public static final String can_22_label = "CAN22";
    public static final String can_23_label = "CAN23";
    public static final String can_24_label = "CAN24";
    public static final String can_25_label = "CAN25";
    public static final String can_26_label = "CAN26";
    public static final String can_27_label = "CAN27";
    public static final String can_28_label = "CAN28";
    public static final String can_29_label = "CAN29";
    public static final String can_30_label = "CAN30";
    public static final String can_31_label = "CAN31";
    public static final String can_32_label = "CAN32";
    public static final String can_33_label = "CAN33";
    public static final String can_34_label = "CAN34";
    public static final String can_35_label = "CAN35";
    public static final String can_36_label = "CAN36";
    public static final String can_37_label = "CAN37";
    public static final String can_38_label = "CAN38";
    public static final String can_39_label = "CAN39";
    public static final String can_40_label = "CAN40";
    public static final String can_41_label = "CAN41";
    public static final String can_42_label = "CAN42";
    public static final String can_43_label = "CAN43";
    public static final String can_44_label = "CAN44";
    public static final String can_45_label = "CAN45";
    public static final String can_46_label = "CAN46";
    public static final String can_47_label = "CAN47";
    public static final String can_48_label = "CAN48";
    public static final String can_49_label = "CAN49";
    public static final String can_50_label = "CAN50";
    public static final String can_51_label = "CAN51";
    public static final String can_52_label = "CAN52";
    public static final String can_53_label = "CAN53";
    public static final String can_54_label = "CAN54";
    public static final String can_55_label = "CAN55";
    public static final String can_56_label = "CAN56";
    public static final String can_57_label = "CAN57";
    public static final String can_58_label = "CAN58";
    public static final String can_59_label = "CAN59";
    public static final String can_60_label = "CAN60";
    public static final String can_61_label = "CAN61";
    public static final String can_62_label = "CAN62";

    public PortMan() {
        allocatedPorts  = new HashMap<String, String> ();
    }

    public int acquirePort(String label, String requestedDevice) throws OzoneException {
        String device   = allocatedPorts.get(label);

        if (device != null) {
            throw new OzoneException ("Port [" + label + "] already allocated to device [" + device + "] when asked for by [" + requestedDevice + "]");
        }
        // remember that we allocated it
        allocatedPorts.put(label, requestedDevice);
        tab.add(requestedDevice, label);

        switch (label) {
            case digital0_label: return 0;
            case digital1_label: return 1;
            case digital2_label: return 2;
            case digital3_label: return 3;
            case digital4_label: return 4;
            case digital5_label: return 5;
            case digital6_label: return 6;
            case digital7_label: return 7;
            case digital8_label: return 8;
            case digital9_label: return 9;
            case digital10_label: return 10;
            case digital11_label: return 11;
            case digital12_label: return 12;
            case digital13_label: return 13;
            case digital14_label: return 14;
            case digital15_label: return 15;
            case digital16_label: return 16;
            case digital17_label: return 17;
            case digital18_label: return 18;
            case digital19_label: return 19;
            case digital20_label: return 20;

            case analog0_label: return 0;
            case analog1_label: return 1;
            case analog2_label: return 2;
            case analog3_label: return 3;
            case analog4_label: return 4;
            case analog5_label: return 5;

            case relay0_label: return 0;
            case relay1_label: return 1;
            case relay2_label: return 2;
            case relay3_label: return 3;

            case pwm0_label: return 0;
            case pwm1_label: return 1;
            case pwm2_label: return 2;
            case pwm3_label: return 3;
            case pwm4_label: return 4;
            case pwm5_label: return 5;
            case pwm6_label: return 6;
            case pwm7_label: return 7;
            case pwm8_label: return 8;
            case pwm9_label: return 9;

            case pcm0_label: return 0;
            case pcm1_label: return 1;
            case pcm2_label: return 2;
            case pcm3_label: return 3;
            case pcm4_label: return 4;
            case pcm5_label: return 5;
            case pcm6_label: return 6;
            case pcm7_label: return 7;

            case can_02_label: return 2;
            case can_03_label: return 3;
            case can_04_label: return 4;
            case can_05_label: return 5;
            case can_06_label: return 6;
            case can_07_label: return 7;
            case can_08_label: return 8;
            case can_09_label: return 9;
            case can_10_label: return 10;
            case can_11_label: return 11;
            case can_12_label: return 12;
            case can_13_label: return 13;
            case can_14_label: return 14;
            case can_15_label: return 15;
            case can_16_label: return 16;
            case can_17_label: return 17;
            case can_18_label: return 18;
            case can_19_label: return 19;
            case can_20_label: return 20;
            case can_21_label: return 21;
            case can_22_label: return 22;
            case can_23_label: return 23;
            case can_24_label: return 24;
            case can_25_label: return 25;
            case can_26_label: return 26;
            case can_27_label: return 27;
            case can_28_label: return 28;
            case can_29_label: return 29;
            case can_30_label: return 30;
            case can_31_label: return 31;
            case can_32_label: return 32;
            case can_33_label: return 33;
            case can_34_label: return 34;
            case can_35_label: return 35;
            case can_36_label: return 36;
            case can_37_label: return 37;
            case can_38_label: return 38;
            case can_39_label: return 39;
            case can_40_label: return 40;
            case can_41_label: return 42;
            case can_42_label: return 42;
            case can_43_label: return 43;
            case can_44_label: return 44;
            case can_45_label: return 45;
            case can_46_label: return 46;
            case can_47_label: return 47;
            case can_48_label: return 48;
            case can_49_label: return 49;
            case can_50_label: return 50;
            case can_51_label: return 51;
            case can_52_label: return 52;
            case can_53_label: return 53;
            case can_54_label: return 54;
            case can_55_label: return 55;
            case can_56_label: return 56;
            case can_57_label: return 57;
            case can_58_label: return 58;
            case can_59_label: return 59;
            case can_60_label: return 60;
            case can_61_label: return 61;
            case can_62_label: return 62;
        }

        throw new OzoneException ("Unknown port identifier [" + label + "]") ;
    }
}
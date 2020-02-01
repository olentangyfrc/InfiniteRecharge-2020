package frc.robot.subsystem;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.logging.Logger;

import frc.robot.OI;
import frc.robot.subsystem.climber.Climber;
import frc.robot.subsystem.controlpanel.ControlPanel;
import frc.robot.subsystem.controlpanel.commands.RotateToColor;

import frc.robot.subsystem.telemetry.Telemetry;
import frc.robot.subsystem.onewheelshooter.OneWheelShooter;
import frc.robot.subsystem.onewheelshooter.commands.OneWheelShoot;
import frc.robot.subsystem.onewheelshooter.commands.OneWheelStop;
import frc.robot.subsystem.pixylinecam.PixyLineCam;
import frc.robot.subsystem.pixylinecam.commands.PollPixyLine;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.climber.commands.Climb;
import frc.robot.subsystem.transport.Transport;
import frc.robot.subsystem.transport.commands.*;
import frc.robot.subsystem.transport.commands.TakeIn;
import frc.robot.subsystem.twowheelshooter.TwoWheelShooter;
import frc.robot.subsystem.twowheelshooter.commands.Shoot;
import frc.robot.subsystem.twowheelshooter.commands.Stop;

public class SubsystemFactory {

    private static SubsystemFactory me;

    static Logger logger = Logger.getLogger(SubsystemFactory.class.getName());

    private static String botMacAddress;

    private String footballMacAddress = "00:80:2F:17:D7:4B";

    private static DisplayManager displayManager;

    /**
     * keep all available subsystem declarations here.
     */

    private Transport transport;
    private ControlPanel controlPanel;
    private Climber climber;
    private OneWheelShooter oneWheelShooter;
    private TwoWheelShooter twoWheelShooter;
    private Telemetry telemetry;
    private PixyLineCam pixyLineCam;
    private static ArrayList<SBInterface> subsystemInterfaceList;

    private SubsystemFactory() {
        // private constructor to enforce Singleton pattern
    }

    public static SubsystemFactory getInstance(boolean b) {

        if (me == null) {
            me = new SubsystemFactory();
        }

        return me;
    }

    public void init(DisplayManager dm, PortMan portMan) throws Exception {

        logger.info("initializing");

        botMacAddress = getMACAddress();
        botMacAddress = footballMacAddress;

        logger.info("[" + botMacAddress + "]");

        displayManager = dm;
        subsystemInterfaceList = new ArrayList<SBInterface>();

        try {

            if (botMacAddress == null || botMacAddress.equals(footballMacAddress)) {
                initFootball(portMan);
            } else {
                throw new Exception("Unrecognized MAC Address [" + botMacAddress + "]");
            }

            initCommon(portMan);

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 
     * init subsystems that are common to all bots
     * 
     */

    private void initCommon(PortMan portMan) {

    }

    /**
     * 
     * init subsystems specific to Football
     * 
     */

    private void initFootball(PortMan portMan) throws Exception {
        logger.info("Initializing Football");
        /**
         * All of the Telemery Stuff goes here
         */
        telemetry = new Telemetry();
        telemetry.init(portMan);
        displayManager.addTelemetry(telemetry);

        /**
         * All of the Climber stuff goes here
         */
        climber = new Climber();
        climber.init(portMan);
        displayManager.addClimber(climber);
        Command c = new Climb(climber);
        OI.getInstance().bind(c, OI.LeftJoyButton1, OI.WhenPressed);

        /**
         * All of the ControlPanel stuff goes here
         */
        controlPanel = new ControlPanel();
        controlPanel.init(portMan, telemetry);
        displayManager.addCP(controlPanel);
        RotateToColor dc = new RotateToColor(controlPanel);
        OI.getInstance().bind(dc, OI.LeftJoyButton2, OI.WhenPressed);

        /**
         * All of the Transport stuff goes here
         */
        /*
        transport = new Transport();
        transport.init(portMan);
        displayManager.addTransport(transport);

        TakeIn tc = new TakeIn(transport);
        OI.getInstance().bind(tc, OI.RightJoyButton4, OI.WhenPressed);

        PushOut pc = new PushOut(transport);
        OI.getInstance().bind(pc, OI.RightJoyButton3, OI.WhenPressed);
        */

        /**
         * All of the OneWheelShooter stuff goes here
         */
        oneWheelShooter = new OneWheelShooter();
        oneWheelShooter.init(portMan);
        OneWheelStop st = new OneWheelStop(oneWheelShooter);
        OI.getInstance().bind(st, OI.LeftJoyButton6, OI.WhenPressed);
        OneWheelShoot sh = new OneWheelShoot(oneWheelShooter);
        OI.getInstance().bind(sh, OI.LeftJoyButton7, OI.WhenPressed);

        /*
         * All of the TwoWheelShooter stuff goes here
         */
        twoWheelShooter = new TwoWheelShooter();
        twoWheelShooter.init(portMan);
        displayManager.addTwoWheelShooter(twoWheelShooter);

        Shoot sh2 = new Shoot(twoWheelShooter);
        OI.getInstance().bind(sh2, OI.LeftJoyButton4, OI.WhenPressed);
        Stop st2 = new Stop(twoWheelShooter);
        OI.getInstance().bind(st2, OI.LeftJoyButton5, OI.WhenPressed);

        /**
         * All of the Pixy Line stuff goes here
         */
        /*
        pixyLineCam = new PixyLineCam();
        pixyLineCam.init(portMan);
        displayManager.addPixyLineCam(pixyLineCam);

        PollPixyLine p = new PollPixyLine(pixyLineCam);
        OI.getInstance().bind(p, OI.LeftJoyButton1, OI.WhenPressed);
        */
        

    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public Climber getClimber() {
        return climber;
    }

    public Transport getTransport() {
        return transport;
    }

    private String getMACAddress() {

        InetAddress ip;
        StringBuilder sb = new StringBuilder();

        try {
            ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();

            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
            }
            return sb.toString();

        } catch (Exception e) {
            return null;
        }
    }
}
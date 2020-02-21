package frc.robot.subsystem;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Logger;

import frc.robot.OI;
import frc.robot.OzoneException;
import frc.robot.subsystem.climber.Climber;
import frc.robot.subsystem.controlpanel.ControlPanel;
import frc.robot.subsystem.controlpanel.commands.RotateToColor;
import frc.robot.subsystem.controlpanel.commands.SpinRotations;
import frc.robot.subsystem.controlpanel.commands.SpinnerRetract;
import frc.robot.subsystem.controlpanel.commands.SpinnerUp;
import frc.robot.subsystem.controlpanel.commands.Stop;
import frc.robot.subsystem.intake.Intake;
import frc.robot.subsystem.intake.commands.IntakeDown;
import frc.robot.subsystem.intake.commands.IntakeSpinBack;
import frc.robot.subsystem.intake.commands.IntakeSpinForward;
import frc.robot.subsystem.intake.commands.IntakeStop;
import frc.robot.subsystem.intake.commands.IntakeUp;
import frc.robot.subsystem.telemetry.Telemetry;
import frc.robot.subsystem.telemetry.commands.SquareSelf;
import frc.robot.subsystem.onewheelshooter.OneWheelShooter;
import frc.robot.subsystem.winch.Winch;
import frc.robot.subsystem.winch.commands.WinchUp;
import frc.robot.subsystem.onewheelshooter.commands.OneWheelReverse;
import frc.robot.subsystem.onewheelshooter.commands.OneWheelShoot;
import frc.robot.subsystem.onewheelshooter.commands.OneWheelStop;
import frc.robot.subsystem.pixylinecam.PixyLineCam;
import frc.robot.subsystem.pixylinecam.commands.PollPixyLine;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystem.climber.commands.Climb;
import frc.robot.subsystem.climber.commands.ClimberControl;
import frc.robot.subsystem.climber.commands.ClimberControlBack;
import frc.robot.subsystem.climber.commands.ClimberRetract;
import frc.robot.subsystem.commandgroups.CollectionMode;
import frc.robot.subsystem.commandgroups.ControlPanelMode;
//import frc.robot.subsystem.commandgroups.ControlPanelMode;
import frc.robot.subsystem.commandgroups.MoveMode;
import frc.robot.subsystem.commandgroups.ScoreLow;
import frc.robot.subsystem.commandgroups.ScoringHigh;
import frc.robot.subsystem.commandgroups.StartingConfiguration;
//import frc.robot.subsystem.commandgroups.StartingConfiguration;
import frc.robot.subsystem.transport.DumpTruck;
import frc.robot.subsystem.transport.Transport;
import frc.robot.subsystem.transport.commands.*;
import frc.robot.subsystem.transport.commands.TakeIn;
import frc.robot.subsystem.transport.commands.StopTransport;
import frc.robot.subsystem.swerve.DrivetrainSubsystem;

public class SubsystemFactory {

    private static SubsystemFactory me;

    static Logger logger = Logger.getLogger(SubsystemFactory.class.getName());

    private static String botName;
    private HashMap<String, String> allMACs; // will contain mapping of MACs to Bot Names

    private static DisplayManager displayManager;

    /**
     * keep all available subsystem declarations here.
     */

    private Transport transport;
    private ControlPanel controlPanel;
    private Climber climber;
    private OneWheelShooter oneWheelShooter;
    private Telemetry telemetry;
    private PixyLineCam pixyLineCam;
    private DrivetrainSubsystem driveTrain;
    private Intake intake;
    private Winch winch;
    
    private static ArrayList<SBInterface> subsystemInterfaceList;

    private SubsystemFactory() {
        // private constructor to enforce Singleton pattern
        botName = "unknown";
        allMACs = new HashMap<>();
        // add all the mappings from MACs to names here
        // as you add mappings here:
        // 1) update the select statement in the init method
        // 2) add the init method for that robot
        allMACs.put("00:80:2F:17:BD:76", "zombie"); // usb0
        allMACs.put("00:80:2F:17:BD:75", "zombie"); // eth0
        allMACs.put("00:80:2F:28:64:39", "plank"); //usb0
        allMACs.put("00:80:2F:28:64:38", "plank"); //eth0
        allMACs.put("00:80:2F:27:04:C7", "RIO3"); //usb0 
        allMACs.put("00:80:2F:27:04:C6", "RIO3"); //eth0
        allMACs.put("00:80:2F:17:D7:4B", "RIO2"); //eth0
        allMACs.put("00:80:2F:17:D7:4C", "RIO2"); //usb0
    }

    public static SubsystemFactory getInstance(boolean b) {

        if (me == null) {
            me = new SubsystemFactory();
        }

        return me;
    }

    public void init(DisplayManager dm, PortMan portMan) throws Exception {

        logger.info("initializing");

        botName = getBotName();

        logger.info("Running on " + botName);

        displayManager = dm;
        subsystemInterfaceList = new ArrayList<SBInterface>();

        try {

            // Note that you should update this switch statement as you add bots to the list
            // above
            switch (botName) {
            case "football":
                initFootball(portMan);
                break;
            case "plank":
                initFootball(portMan);
                break;
            case "zombie":
                initZombie(portMan);
                break;
            case "RIO2":
                initRio2(portMan);
                break;
            case "comp":
                initComp(portMan);
                break;
            default:
                initComp(portMan); // default to football if we don't know better
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

    private void initComp(PortMan portMan ) throws Exception {

        logger.info("initiatizing");
        /**
         * All of the Winch stuff goes here
         */
        winch = new Winch();
        winch.init(portMan);
        
        WinchUp w = new WinchUp(winch);
        OI.getInstance().bind(w, OI.LeftJoyButton8, OI.WhileHeld);
        
        driveTrain  = new DrivetrainSubsystem();
        driveTrain.init(portMan);

        /** * All of the Transport stuff goes here
         *
         */

        
        transport = new Transport();
        transport.init(portMan);
        displayManager.addTransport(transport);
        
        TakeIn tc = new TakeIn(transport);

        PushOut pc = new PushOut(transport);
        OI.getInstance().bind(pc, OI.AuxJoyButton7, OI.WhenPressed);

        SideGateOpen tu = new SideGateOpen(transport);
        OI.getInstance().bind(tu, OI.AuxJoyButton8, OI.WhenPressed);

        SideGateClose td = new SideGateClose(transport);
        OI.getInstance().bind(td, OI.AuxJoyButton9, OI.WhenPressed);

        TailGateUp tgu = new TailGateUp(transport);
        OI.getInstance().bind(tgu, OI.AuxJoyButton4, OI.WhenPressed);

        TailGateDown tgd = new TailGateDown(transport);
        OI.getInstance().bind(tgd, OI.AuxJoyButton5, OI.WhenPressed);

        StopTransport si = new StopTransport(transport);
        OI.getInstance().bind(si, OI.AuxJoyButton10, OI.WhenPressed);

        DumpTruck dt = new DumpTruck(transport);
        OI.getInstance().bind(dt, OI.button7, OI.WhenPressed);
        


        /**
         * All of the OneWheelShooter stuff goes here
         */
        
        oneWheelShooter = new OneWheelShooter();
        oneWheelShooter.init(portMan);
        displayManager.addShooter(oneWheelShooter);

        
        OneWheelShoot sh = new OneWheelShoot(oneWheelShooter);
        OI.getInstance().bind(sh, OI.RightJoyButton1, OI.WhenPressed);

        OneWheelReverse owr = new OneWheelReverse(oneWheelShooter);
        OI.getInstance().bind(owr, OI.RightJoyButton3, OI.WhenPressed);

        OneWheelStop st = new OneWheelStop(oneWheelShooter);
        OI.getInstance().bind(st, OI.RightJoyButton2, OI.WhenPressed);
        

         /**
         * All of Intake Stuff goes here
         */

        intake = new Intake();
        intake.init(portMan);
        displayManager.addIntake(intake);

        
        IntakeUp iu = new IntakeUp(intake);
        OI.getInstance().bind(iu,OI.RightJoyButton6, OI.WhenPressed);

        IntakeDown id = new IntakeDown(intake);
        OI.getInstance().bind(id,OI.RightJoyButton7, OI.WhenPressed);

        IntakeSpinForward isf = new IntakeSpinForward(intake);
        OI.getInstance().bind(isf,OI.RightJoyButton11, OI.WhenPressed);

        IntakeSpinBack isb = new IntakeSpinBack(intake);
        OI.getInstance().bind(isb,OI.RightJoyButton10, OI.WhenPressed);

        IntakeStop is = new IntakeStop(intake);
        OI.getInstance().bind(is,OI.RightJoyButton4, OI.WhenPressed);
        

         /**
         * All of the ControlPanel stuff goes here
         */

        controlPanel = new ControlPanel();
        controlPanel.init(portMan, telemetry);
        displayManager.addCP(controlPanel);

        
        RotateToColor dc = new RotateToColor(controlPanel, "Blue");
        OI.getInstance().bind(dc, OI.LeftJoyButton2, OI.WhenPressed);

        SpinRotations ss = new SpinRotations(controlPanel, 6);
        OI.getInstance().bind(ss, OI.LeftJoyButton3, OI.WhenPressed);

        SpinnerUp su = new SpinnerUp(controlPanel);
        OI.getInstance().bind(su, OI.LeftJoyButton4, OI.WhenPressed);

        SpinnerRetract sr = new SpinnerRetract(controlPanel);
        OI.getInstance().bind(sr, OI.LeftJoyButton5, OI.WhenPressed);

        Stop stop = new Stop(controlPanel);
        OI.getInstance().bind(stop, OI.LeftJoyButton1, OI.WhenPressed);
        
        /**
         * All of the Climber stuff goes here
         */
        climber = new Climber();
        climber.init(portMan);
        displayManager.addClimber(climber);

        
        Climb c = new Climb(climber);
        OI.getInstance().bind(c, OI.LeftJoyButton6, OI.WhenPressed);

        ClimberRetract cr = new ClimberRetract(climber);
        OI.getInstance().bind(cr, OI.LeftJoyButton7, OI.WhenPressed);

        ClimberControl cc = new ClimberControl(climber);
        OI.getInstance().bind(cc, OI.LeftJoyButton11,OI.WhileHeld);

        ClimberControlBack ccb = new ClimberControlBack(climber);
        OI.getInstance().bind(ccb,OI.LeftJoyButton10,OI.WhileHeld);


        //Command Groups
        CollectionMode collectionMode = new CollectionMode(transport, intake, controlPanel, oneWheelShooter);
        OI.getInstance().bind(collectionMode, OI.button1, OI.WhenPressed);
        
        StartingConfiguration startConfig = new StartingConfiguration(transport, intake, controlPanel, oneWheelShooter);
        OI.getInstance().bind(startConfig, OI.button2, OI.WhenPressed);
        
        MoveMode moveMode = new MoveMode(transport, intake, controlPanel);
        OI.getInstance().bind(moveMode, OI.button3, OI.WhenPressed);

        ScoreLow scoreLow = new ScoreLow(transport, intake, controlPanel);
        OI.getInstance().bind(scoreLow, OI.button4, OI.WhenPressed);

        ScoringHigh scoreHigh = new ScoringHigh(transport, intake, controlPanel, oneWheelShooter);
        OI.getInstance().bind(scoreHigh, OI.button5, OI.WhenPressed);

        ControlPanelMode controlPanelMode = new ControlPanelMode(transport, intake, controlPanel, oneWheelShooter);
        OI.getInstance().bind(controlPanelMode, OI.button6, OI.WhenPressed);
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
        Command sqs = new SquareSelf(telemetry, 10);
        OI.getInstance().bind(sqs, OI.LeftJoyButton10, OI.WhenPressed);
        /**
         * All of the Climber stuff goes here
         */
        
        climber = new Climber();
        climber.init(portMan);
        displayManager.addClimber(climber);
        Climb c = new Climb(climber);
        OI.getInstance().bind(c, OI.RightJoyButton11, OI.WhenPressed);
        ClimberRetract cr = new ClimberRetract(climber);
        OI.getInstance().bind(cr, OI.RightJoyButton10, OI.WhenPressed);
        ClimberControl cc = new ClimberControl(climber);
        OI.getInstance().bind(cc, OI.RightJoyButton8, OI.WhileHeld);
        ClimberControlBack ccb = new ClimberControlBack(climber);
        OI.getInstance().bind(ccb,OI.RightJoyButton6, OI.WhileHeld);
        
        

        /**
         * All of the ControlPanel stuff goes here
         */

        controlPanel = new ControlPanel();
        controlPanel.init(portMan, telemetry);
        displayManager.addCP(controlPanel);
        RotateToColor dc = new RotateToColor(controlPanel, "Blue");
        OI.getInstance().bind(dc, OI.LeftJoyButton2, OI.WhenPressed);
        SpinRotations ss = new SpinRotations(controlPanel, 6);
        OI.getInstance().bind(ss, OI.LeftJoyButton3, OI.WhenPressed);
        SpinnerUp su = new SpinnerUp(controlPanel);
        OI.getInstance().bind(su, OI.LeftJoyButton8, OI.WhenPressed);
        SpinnerRetract sr = new SpinnerRetract(controlPanel);
        OI.getInstance().bind(sr, OI.LeftJoyButton9, OI.WhenPressed);
        Stop stop = new Stop(controlPanel);
        OI.getInstance().bind(stop, OI.LeftJoyButton1, OI.WhenPressed);

        /**
         * All of the Transport stuff goes here
         */
        
        transport = new Transport();
        transport.init(portMan);
        displayManager.addTransport(transport);
        TakeIn tc = new TakeIn(transport);
        OI.getInstance().bind(tc, OI.RightJoyButton7, OI.WhenPressed);
        PushOut pc = new PushOut(transport);
        OI.getInstance().bind(pc, OI.RightJoyButton11, OI.WhenPressed);
        StopTransport si = new StopTransport(transport);
        OI.getInstance().bind(si, OI.RightJoyButton9, OI.WhenPressed);
        SideGateOpen tu = new SideGateOpen(transport);
        OI.getInstance().bind(tu, OI.AuxJoyButton8, OI.WhenPressed);
        SideGateClose td = new SideGateClose(transport);
        OI.getInstance().bind(td, OI.AuxJoyButton9, OI.WhenPressed);

        /**
         * All of the OneWheelShooter stuff goes here
         */
        
        oneWheelShooter = new OneWheelShooter();
        oneWheelShooter.init(portMan);
        displayManager.addShooter(oneWheelShooter);
        OneWheelStop st = new OneWheelStop(oneWheelShooter);
        OI.getInstance().bind(st, OI.LeftJoyButton6, OI.WhenPressed);
        OneWheelShoot sh = new OneWheelShoot(oneWheelShooter);
        OI.getInstance().bind(sh, OI.LeftJoyButton7, OI.WhenPressed);
        OneWheelReverse owr = new OneWheelReverse(oneWheelShooter);
        OI.getInstance().bind(owr, OI.LeftJoyButton8, OI.WhenPressed);
        OneWheelReverse shooterReverse = new OneWheelReverse(oneWheelShooter);
        OI.getInstance().bind(shooterReverse, OI.LeftJoyButton8, OI.WhenPressed);


        /**
         * All of the Pixy Line stuff goes here
         */
        
        pixyLineCam = new PixyLineCam();
        pixyLineCam.init(portMan);
        displayManager.addPixyLineCam(pixyLineCam);

        PollPixyLine p = new PollPixyLine(pixyLineCam);
        OI.getInstance().bind(p, OI.LeftJoyButton1, OI.WhenPressed);

         /**
         * All of Intake Stuff goes here
         */

        intake = new Intake();
        intake.init(portMan);
        displayManager.addIntake(intake);

        IntakeUp iu = new IntakeUp(intake);
        OI.getInstance().bind(iu,OI.RightJoyButton6, OI.WhenPressed);
        IntakeDown id = new IntakeDown(intake);
        OI.getInstance().bind(id,OI.RightJoyButton7, OI.WhenPressed);
        IntakeSpinForward isf = new IntakeSpinForward(intake);
        OI.getInstance().bind(isf,OI.RightJoyButton11, OI.WhenPressed);

        IntakeSpinBack isb = new IntakeSpinBack(intake);
        OI.getInstance().bind(isb,OI.RightJoyButton10, OI.WhenPressed);
        
    }

    private void initZombie(PortMan portMan) throws OzoneException {
        logger.info("Initializing Zombie");
    }

    private void initRio2(PortMan portMan) throws OzoneException {
        logger.info("Initializing RIO2");
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

    private String getBotName() throws Exception {

        Enumeration<NetworkInterface> networks;
            networks = NetworkInterface.getNetworkInterfaces();

            String activeMACs = "";
            for (NetworkInterface net : Collections.list(networks)) {
                String mac = formatMACAddress(net.getHardwareAddress());
                activeMACs += (mac+" ");
                logger.info("Network #"+net.getIndex()+" "+net.getName()+" "+mac);
                if (allMACs.containsKey(mac)) {
                    botName = allMACs.get(mac);
                    logger.info("   this MAC is for "+botName);
                }
            }

            return botName;
        }

    /**
     * Formats the byte array representing the mac address as more human-readable form
     * @param hardwareAddress byte array
     * @return string of hex bytes separated by colons
     */
    private String formatMACAddress(byte[] hardwareAddress) {
        if (hardwareAddress == null || hardwareAddress.length == 0) {
            return "";
        }
        StringBuilder mac = new StringBuilder(); // StringBuilder is a premature optimization here, but done as best practice
        for (int k=0;k<hardwareAddress.length;k++) {
            int i = hardwareAddress[k] & 0xFF;  // unsigned integer from byte
            String hex = Integer.toString(i,16);
            if (hex.length() == 1) {  // we want to make all bytes two hex digits 
                hex = "0"+hex;
            }
            mac.append(hex.toUpperCase());
            mac.append(":");
        }
        mac.setLength(mac.length()-1);  // trim off the trailing colon
        return mac.toString();
    }

}
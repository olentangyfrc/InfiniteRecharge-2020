package frc.robot.subsystem;

import java.net.InetAddress;
import java.util.logging.Logger;

import frc.robot.OI;
import frc.robot.subsystem.climber.Climber;
import frc.robot.subsystem.controlpanel.ControlPanel;
import frc.robot.subsystem.controlpanel.commands.DisplayColor;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.climber.commands.PatsCommand;
import frc.robot.subsystem.transport.Transport;
import frc.robot.subsystem.transport.commands.TakeIn;

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

        botMacAddress = InetAddress.getLocalHost().getHostAddress().trim();
        botMacAddress = footballMacAddress;

        logger.info("[" + botMacAddress + "]");

        displayManager = dm;

        try {

            if (botMacAddress.equals(footballMacAddress) || botMacAddress == null || botMacAddress.equals("")) {
                initFootball(portMan);
            } else {
                throw new Exception("Unrecognized MAC Address [" + botMacAddress + "]");
            }

            initCommon(portMan);

            // driverfeedback will create a shuffleboard tab that aggregates data from
            // subsystems.

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

     * init subsystems specific to Football

     */

    private void initFootball(PortMan portMan) throws Exception {
        logger.info("Initializing Football");


        /**
         * All of the Climber stuff goes here
         */
        Climber climber = new Climber();
        climber.init(portMan);
        displayManager.addClimber(climber);
        Command c = new PatsCommand(climber);
        OI.getInstance().bind(c, OI.LeftJoyButton1, OI.WhenPressed);


        /**
         * All of the ControlPanel stuff goes here
         */
        controlPanel = new ControlPanel();
        controlPanel.init(portMan);
        displayManager.addCP(controlPanel);
        DisplayColor dc = new DisplayColor(controlPanel);
        OI.getInstance().bind(dc, OI.LeftJoyButton2, OI.WhenPressed);


        /**
         * All of the Transport stuff goes here
         */
        transport  = new Transport();
        transport.init(portMan);
        displayManager.addTransport(transport);
        TakeIn tc    = new TakeIn(transport);
        OI.getInstance().bind(tc, OI.LeftJoyButton3, OI.WhenPressed);
    }

    public ControlPanel getControlPanel(){
        return controlPanel;
    }

    public Climber getClimber() {
        return climber;
    }

    public Transport getTransport() {
        return transport;
    }
}
package frc.robot.subsystem;

import java.io.StringWriter;
import java.net.InetAddress;
import java.util.logging.Logger;

import frc.robot.OI;
import frc.robot.subsystem.climber.Climber;
import frc.robot.subsystem.climber.ClimberSBTab;
import frc.robot.subsystem.controlpanel.ControlPanel;
import frc.robot.subsystem.controlpanel.commands.DisplayColor;
import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OzoneException;
import frc.robot.subsystem.climber.commands.PatsCommand;
import frc.robot.subsystem.transport.Transport;
import frc.robot.subsystem.transport.commands.TakeIn;

public class SubsystemFactory {

    private static SubsystemFactory me;

    static Logger logger = Logger.getLogger(SubsystemFactory.class.getName());

    private static String botMacAddress;

    private String footballMacAddress = "00:80:2F:17:D7:4B";

    private boolean isRoboRio;

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
            me.isRoboRio = b;
        }

        return me;
    }

    public void init(PortMan portMan) throws Exception {

        logger.info("initializing");

        botMacAddress = InetAddress.getLocalHost().getHostAddress().trim();
        botMacAddress = footballMacAddress;

        logger.info("[" + botMacAddress + "]");

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
        ClimberSBTab tab = new ClimberSBTab(climber);
        Command c = new PatsCommand(climber);
        OI.getInstance().bind(c, OI.LeftJoyButton1, OI.WhenPressed);


        /**
         * All of the ControlPanel stuff goes here
         */
        controlPanel = new ControlPanel();
        controlPanel.init(portMan);
        DisplayColor dc = new DisplayColor(controlPanel);
        OI.getInstance().bind(dc, OI.LeftJoyButton2, OI.WhenPressed);


        /**
         * All of the Transport stuff goes here
         */
        transport  = new Transport();
        transport.init(portMan);

        TakeIn tc    = new TakeIn(transport);
        OI.getInstance().bind(tc, OI.LeftJoyButton3, OI.WhenPressed);

        /**
         * if this is not the robot, just see if commands do what they are supposed to
         */
        if (!isRoboRio) {
            c.initialize(); c.execute();
            dc.initialize(); dc.execute();
            tc.initialize(); tc.execute();
        }
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
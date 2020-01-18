package frc.robot.subsystem;

import java.io.StringWriter;
import java.net.InetAddress;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.OzoneException;
import frc.robot.subsystem.climber.Climber;
import frc.robot.subsystem.climber.ClimberSBTab;
import frc.robot.subsystem.climber.commands.PatsCommand;
import frc.robot.subsystem.transport.Transport;

public class SubsystemFactory {

    private static SubsystemFactory me;

    static Logger logger = Logger.getLogger(SubsystemFactory.class.getName());

    private static String botMacAddress;

    private String protoMacAddress = "00:80:2F:22:D7:BC";
    private String blueMacAddress = "00:80:2F:27:1D:E9";
    private String zippyMacAddress = "00:80:2F:25:B4:CA";
    private String turboMacAddress = "00:80:2F:27:04:C6";
    private String footballMacAddress = "00:80:2F:17:D7:4B";
    private String newbieMacAddress = "00:80:2F:17:F8:3F";

    /**
     * keep all available subsystem declarations here.
     */

    private Transport transport;
    
    private SubsystemFactory() {
        // private constructor to enforce Singleton pattern
    }

    public static SubsystemFactory getInstance() {

        if (me == null) {
            me = new SubsystemFactory();
        }

        return me;
    }

    public void init() throws Exception {

        logger.info("initializing");

        botMacAddress = InetAddress.getLocalHost().getHostAddress().trim();

        logger.info("[" + botMacAddress + "]");

        try {

            if (botMacAddress.equals(protoMacAddress)) {
                initProto();

            } else if (botMacAddress.equals(footballMacAddress) || botMacAddress == null || botMacAddress.equals("")) {

                initFootball();

            } else if (botMacAddress.equals(blueMacAddress)) {

                initComp();

            } else if (botMacAddress.equals(zippyMacAddress)) {

                initZippy();

            } else if (botMacAddress.equals(turboMacAddress)) {

                initTurbo();

            } else if (botMacAddress.equals(newbieMacAddress)) {

                initNewbie();

            } else {

                throw new Exception("Unrecognized MAC Address [" + botMacAddress + "]");
            }

            initCommon();

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

    private void initCommon() {
        Climber climber = new Climber();
        ClimberSBTab tab = new ClimberSBTab(climber);
        Command c = new PatsCommand(climber);
        try {
            OI.getInstance().bind(c, OI.LeftJoyButton1, OI.WhenPressed);
        } catch (OzoneException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }



    
     /**

     * init subsytems specific to Proto

     */

    private void initProto() throws Exception{

        logger.info("initalizing Proto");


        /*
        elevator = new Elevator();

        elevator.init(portMan);



        nav = new Navigation();

        nav.init(portMan);

        

        intakeAdjuster = new IntakeAdjuster();

        intakeAdjuster.init(portMan);



        lineTracker = new LineTracker();

        lineTracker.init(portMan);



        roller = new Roller();

        roller.init(portMan);



        doubleWheel = new DoubleWheel();

        doubleWheel.init(portMan);



        shooterIntake = new Intake();

        shooterIntake.init(portMan);



        driveTrain = new SparkMecanum();

        driveTrain.init(portMan);



        unicornHorn = new UnicornHorn();

        unicornHorn.init(portMan);



        hook = new Hook();

        hook.init(portMan);



        climber = new Climber();

        climber.init(portMan);



        oi.bind(new KeepElevatorInPlace(), OI.LeftJoyButton1, OI.WhileHeld);

        

        oi.bind(new MoveHatch(4), OI.LeftJoyButton3, OI.WhenPressed);

        oi.bind(new MoveHatch(-4), OI.LeftJoyButton2, OI.WhenPressed);

        oi.bind(new MoveElevator(true), OI.LeftJoyButton11, OI.WhileHeld);

        oi.bind(new MoveElevator(false), OI.LeftJoyButton10, OI.WhileHeld);

    

        oi.bind(new Push(), OI.LeftJoyButton6, OI.WhenPressed);

        oi.bind(new Retract(), OI.LeftJoyButton7, OI.WhenPressed);



        oi.bind(new IntakeBackward(), OI.LeftJoyButton4, OI.ToggleWhenPressed);

        oi.bind(new IntakeBackwardSlower(), OI.LeftJoyButton5, OI.ToggleWhenPressed);



        oi.bind(new MoveHook(true), OI.LeftJoyButton8, OI.WhenPressed);

        oi.bind(new MoveHook(false), OI.LeftJoyButton9, OI.WhenPressed);



        oi.bind(new IntakeBall(), OI.RightJoyButton5, OI.WhileHeld);

        oi.bind(new OutTakeBall(), OI.RightJoyButton4, OI.ToggleWhenPressed);

        

        oi.bind(new MoveRollerBackward(), OI.RightJoyButton1, OI.WhileHeld);

        oi.bind(new MoveRollerSlowForward(), OI.RightJoyButton2, OI.WhileHeld);

        oi.bind(new MoveRollerForward(), OI.RightJoyButton3, OI.WhileHeld);

        oi.bind(new MoveIntakeAdjusterBackward(), OI.RightJoyButton10, OI.WhileHeld);

        oi.bind(new MoveIntakeAdjusterForward(), OI.RightJoyButton11, OI.WhileHeld);



        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.AuxJoyButton4, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_2), OI.AuxJoyButton8, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_3), OI.AuxJoyButton3, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_4), OI.AuxJoyButton7, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_5), OI.AuxJoyButton5, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_8), OI.AuxJoyButton6, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_7), OI.AuxJoyButton2, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.AuxJoyButton9, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.AuxJoyButton10, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL3), OI.AuxJoyButton11, OI.WhenPressed);

        oi.bind(new ChooseCamera(), OI.AuxJoyButton1, OI.WhenPressed);



        oi.bind(new MoveElevator(true), OI.button1, OI.WhileHeld);

        oi.bind(new MoveElevator(false), OI.button2, OI.WhileHeld);



        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.button3, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_2), OI.button4, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_3), OI.button5, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_4), OI.button6, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_5), OI.button7, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_8), OI.button8, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.button9, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_7), OI.button10, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL1), OI.button12, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.button13, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL3), OI.button14, OI.WhenPressed);

        oi.bind(new ChooseCamera(), OI.button15, OI.WhenPressed);
        */
    }

    

    /**

     * init subsytems specific to Proto

     */

    private void initComp() throws Exception{

        logger.info("initalizing Blue");


        /*
        elevator = new Elevator();

        elevator.init(portMan);



        nav = new Navigation();

        nav.init(portMan);

        

        intakeAdjuster = new IntakeAdjuster();

        intakeAdjuster.init(portMan);



        lineTracker = new LineTracker();

        lineTracker.init(portMan);



        roller = new Roller();

        roller.init(portMan);



        doubleWheel = new DoubleWheel();

        doubleWheel.init(portMan);



        shooterIntake = new Intake();

        shooterIntake.init(portMan);



        driveTrain = new SparkMecanum();

        driveTrain.init(portMan);



        unicornHorn = new UnicornHorn();

        unicornHorn.init(portMan);



        hook = new Hook();

        hook.init(portMan);



        climber = new Climber();

        climber.init(portMan);



        oi.bind(new KeepElevatorInPlace(), OI.LeftJoyButton1, OI.WhileHeld);

        

        oi.bind(new MoveHatch(4), OI.LeftJoyButton3, OI.WhenPressed);

        oi.bind(new MoveHatch(-4), OI.LeftJoyButton2, OI.WhenPressed);

        oi.bind(new MoveElevator(true), OI.LeftJoyButton11, OI.WhileHeld);

        oi.bind(new MoveElevator(false), OI.LeftJoyButton10, OI.WhileHeld);

    

        oi.bind(new Push(), OI.LeftJoyButton6, OI.WhenPressed);

        oi.bind(new Retract(), OI.LeftJoyButton7, OI.WhenPressed);



        oi.bind(new IntakeBackward(), OI.LeftJoyButton4, OI.ToggleWhenPressed);

        oi.bind(new IntakeBackwardSlower(), OI.LeftJoyButton5, OI.ToggleWhenPressed);



        oi.bind(new MoveHook(true), OI.LeftJoyButton8, OI.WhenPressed);

        oi.bind(new MoveHook(false), OI.LeftJoyButton9, OI.WhenPressed);



        oi.bind(new IntakeBall(), OI.RightJoyButton5, OI.WhileHeld);

        oi.bind(new OutTakeBall(), OI.RightJoyButton4, OI.ToggleWhenPressed);

        

        oi.bind(new MoveRollerBackward(), OI.RightJoyButton1, OI.WhileHeld);

        oi.bind(new MoveRollerSlowForward(), OI.RightJoyButton2, OI.WhileHeld);

        oi.bind(new MoveRollerForward(), OI.RightJoyButton3, OI.WhileHeld);

        oi.bind(new MoveIntakeAdjusterBackward(), OI.RightJoyButton10, OI.WhileHeld);

        oi.bind(new MoveIntakeAdjusterForward(), OI.RightJoyButton11, OI.WhileHeld);



        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.AuxJoyButton4, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_2), OI.AuxJoyButton8, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_3), OI.AuxJoyButton3, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_4), OI.AuxJoyButton7, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_5), OI.AuxJoyButton5, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_8), OI.AuxJoyButton6, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_7), OI.AuxJoyButton2, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.AuxJoyButton9, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.AuxJoyButton10, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL3), OI.AuxJoyButton11, OI.WhenPressed);

        oi.bind(new ChooseCamera(), OI.AuxJoyButton1, OI.WhenPressed);



        oi.bind(new MoveElevator(true), OI.button1, OI.WhileHeld);

        oi.bind(new MoveElevator(false), OI.button2, OI.WhileHeld);



        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.button3, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_2), OI.button4, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_3), OI.button5, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_4), OI.button6, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_5), OI.button7, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_8), OI.button8, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.button9, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_7), OI.button10, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL1), OI.button12, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.button13, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL3), OI.button14, OI.WhenPressed);

        oi.bind(new ChooseCamera(), OI.button15, OI.WhenPressed);
        */
    }



    

    /**

     * This was the blue code as it was on stop build

     */

    

    /**

     * init subsystems specific to Newbie

     * @throws Exception

     */

    private void initNewbie() throws Exception {

       logger.info("initializing Newbie");


       /*
       driveTrain = new TankDrive();

       driveTrain.init(portMan);

       pixyLineCam = new PixyLineCam();

       pixyLineCam.init();
       */

       

    }



    /**

     * init subsytems specific to Zippy

     */

    private void initZippy() throws Exception {

        logger.info("initalizing Zippy");

        /*
        driveTrain = new TalonMecanum();

        driveTrain.init(portMan);

        

        vision = new Vision();

        vision.init();



        nav = new Navigation();

        nav.init(portMan);

        

        oi.bind(new StrafeVision(), OI.LeftJoyButton1, OI.WhenPressed);
        */
    }

    

    /**

     * init subsytems specific to Turbo

     */

    private void initTurbo() throws Exception {

        logger.info("initalizing Turbo");
        /*
        driveTrain = new TurboTankDrive();

        driveTrain.init(portMan);



        oi.bind(new ActivateTurbo(), OI.RightJoyButton1, OI.WhenPressed);

        oi.bind(new DeactivateTurbo(), OI.RightJoyButton1, OI.WhenReleased);
        */
    }



    /**

     * init subsystems specific to Football

     */

    private void initFootball() throws Exception {

        logger.info("Initializing Football");
        /*
        oi.bind(new ChooseCamera(), OI.AuxJoyButton1, OI.WhenPressed);



        /*

        elevator = new Elevator();

        elevator.initSB();



        intakeAdjuster = new IntakeAdjuster();

        intakeAdjuster.init(portMan);



        oi.bind(new MoveElevator(true), OI.button1, OI.WhileHeld);

        oi.bind(new MoveElevator(false), OI.button2, OI.WhileHeld);



        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.button3, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_2), OI.button4, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_3), OI.button5, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_4), OI.button6, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_5), OI.button7, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_8), OI.button8, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.button9, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_7), OI.button10, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL1), OI.button12, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.button13, OI.WhenPressed);

        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL3), OI.button14, OI.WhenPressed);

        oi.bind(new ChooseCamera(), OI.button15, OI.WhenPressed);

        */



        /*

        nav    = new Navigation();

        nav.initSB();

        lineTracker = new LineTracker();

        lineTracker.initSB();

        elevator = new Elevator();

        elevator.initSB();

        intakeAdjuster = new IntakeAdjuster();

        intakeAdjuster.initSB();

        //nav.init(portMan);

        //elevator = new Elevator();

        //elevator.initSB();

        */
    }

}
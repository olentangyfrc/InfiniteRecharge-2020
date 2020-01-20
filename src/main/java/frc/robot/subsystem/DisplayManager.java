/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import frc.robot.subsystem.climber.Climber;
import frc.robot.subsystem.climber.ClimberSBTab;
import frc.robot.subsystem.controlpanel.ControlPanel;
import frc.robot.subsystem.controlpanel.ControlPanelSBTab;
import frc.robot.subsystem.transport.Transport;
import frc.robot.subsystem.transport.TransportSBTab;

import java.util.logging.Logger;

/**
 * Add your docs here.
 */
public class DisplayManager {
    private ControlPanelSBTab controlPanelDisplay;
    private TransportSBTab transportDisplay;
    private ClimberSBTab climberDisplay;

    private static Logger logger = Logger.getLogger(DisplayManager.class.getName());


    public DisplayManager(){
        
    }

    public void addCP(ControlPanel cp){
        controlPanelDisplay = new ControlPanelSBTab(cp);
    }

    public void addTransport(Transport t){
        transportDisplay = new TransportSBTab(t);
    }

    public void addClimber(Climber c){
        climberDisplay = new ClimberSBTab(c);
    }

    public void update(){
        logger.info("update");
        if(controlPanelDisplay != null)
            controlPanelDisplay.update();
        else if(transportDisplay != null)
            transportDisplay.update();
        else if(climberDisplay != null)
            climberDisplay.update();

    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.commandgroups;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystem.controlpanel.ControlPanel;
import frc.robot.subsystem.controlpanel.commands.SpinnerUp;
import frc.robot.subsystem.intake.Intake;
import frc.robot.subsystem.intake.commands.IntakeDown;
import frc.robot.subsystem.intake.commands.IntakeStop;
import frc.robot.subsystem.transport.Transport;
import frc.robot.subsystem.transport.commands.SideGateClose;
import frc.robot.subsystem.transport.commands.TailGateDown;
import frc.robot.subsystem.transport.commands.TakeIn;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ScoreLow extends SequentialCommandGroup {
  /**
   * Creates a new ScoreLow.
   */
  private static Logger logger = Logger.getLogger(CollectionMode.class.getName());
  
  public ScoreLow(Transport t, Intake i, ControlPanel cp) {
    super(new SideGateClose(t), new TailGateDown(t), new TakeIn(t), new IntakeDown(i), new IntakeStop(i), new SpinnerUp(cp));
    logger.info("Score Low called");
  }
}

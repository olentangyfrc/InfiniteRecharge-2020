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
import frc.robot.subsystem.controlpanel.commands.SpinRotations;
import frc.robot.subsystem.controlpanel.commands.SpinnerRetract;
import frc.robot.subsystem.controlpanel.commands.SpinnerUp;
import frc.robot.subsystem.intake.Intake;
import frc.robot.subsystem.intake.commands.IntakeDown;
import frc.robot.subsystem.intake.commands.IntakeSpinForward;
import frc.robot.subsystem.onewheelshooter.OneWheelShooter;
import frc.robot.subsystem.onewheelshooter.commands.OneWheelStop;
import frc.robot.subsystem.transport.Transport;
import frc.robot.subsystem.transport.commands.SideGateClose;
import frc.robot.subsystem.transport.commands.StopTransport;
import frc.robot.subsystem.transport.commands.TailGateUp;
import frc.robot.subsystem.transport.commands.TakeIn;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class CollectionMode extends SequentialCommandGroup {
  /**
   * Creates a new TestCommandGroup.
   */
  private static Logger logger = Logger.getLogger(CollectionMode.class.getName());

  //needs to add winch
  public CollectionMode(Transport t, Intake i, ControlPanel cp, OneWheelShooter s) {
    super(new SideGateClose(t), new TailGateUp(t), new IntakeDown(i), new IntakeSpinForward(i), new SpinnerRetract(cp), new StopTransport(t), new OneWheelStop(s));
  }
}

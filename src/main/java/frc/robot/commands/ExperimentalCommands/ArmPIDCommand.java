package frc.robot.commands.ExperimentalCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSystem;

public class ArmPIDCommand extends Command {
    public ArmSystem m_ArmSystem;
    public PIDController PID = new PIDController(0, 0, 0);

    public ArmPIDCommand(ArmSystem ArmSystem, double angle) {
        m_ArmSystem = ArmSystem;
        m_ArmSystem.setDesiredAngle(angle);
    }

    public void init() {

    }

    public void execute() {

    }

    public boolean isFinished() {
        return false;
    }

    public void end() {
        m_ArmSystem.stopSystem();
    }
}

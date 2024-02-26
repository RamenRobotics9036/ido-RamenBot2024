package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSystem;

public class PullBackCommand extends Command {
    private IntakeSystem m_intakeSystem;
    private double m_angle;

    public PullBackCommand(IntakeSystem intakeSystem) {
        m_intakeSystem = intakeSystem;
        addRequirements(m_intakeSystem);
    }

    @Override
    public void initialize() {
        m_angle = m_intakeSystem.getIntakeAngle();
        IntakeConstants.speed = IntakeConstants.pullBackSpeed;
    }

    @Override
    public void execute() {
        // In case the motor is stil spinning in wrong direction on startup
        m_angle = Math.min(m_angle, m_intakeSystem.getIntakeAngle());
    }

    @Override
    public boolean isFinished() {
        if (m_intakeSystem.getIntakeAngle() > m_angle + IntakeConstants.pullBackAmount) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSystem.stopSystem();
        IntakeConstants.speed = 0;
    }
}
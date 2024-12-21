package frc.robot.commands.ShootCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.subsystems.ShooterSystem;

public class ShootCommand extends Command {
    Timer m_timer;
    ShooterSystem m_ShooterSystem;

    public ShootCommand(/* IntakeSystem intake, */ ShooterSystem shooter) {
        m_timer = new Timer();
        m_ShooterSystem = shooter;
        addRequirements(m_ShooterSystem);

    }

    @Override
    public void initialize() {
        m_timer.restart();
    }

    @Override
    public void execute() {
        m_ShooterSystem.setShootSpeed(0.1);
    }

    @Override
    public boolean isFinished() {
        return (m_timer.get() > 2);
    }

    @Override
    public void end(boolean interrupted) {
        m_ShooterSystem.stopSystem();
    }
}

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HookConstants;
import frc.robot.commands.DefaultHookCommand;
import frc.robot.util.AppliedController;

public class HookSystem extends SubsystemBase {
    private CANSparkMax m_followerMotor = new CANSparkMax(HookConstants.rightHookCANId,
            MotorType.kBrushless);
    private CANSparkMax m_leaderMotor = new CANSparkMax(HookConstants.leftHookCANId,
            MotorType.kBrushless);
    @SuppressWarnings("MemberNameCheck")
    private RelativeEncoder m_lEncoder = m_leaderMotor.getEncoder();
    @SuppressWarnings("MemberNameCheck")
    private RelativeEncoder m_rEncoder = m_followerMotor.getEncoder();
    private AppliedController m_controller;

    public HookSystem(AppliedController controller) {
        m_controller = controller;
        m_leaderMotor.restoreFactoryDefaults();
        m_followerMotor.restoreFactoryDefaults();
        m_leaderMotor.setIdleMode(IdleMode.kBrake);
        m_followerMotor.setIdleMode(IdleMode.kBrake);

        m_leaderMotor.setSmartCurrentLimit(15);
        m_followerMotor.setSmartCurrentLimit(15);

        m_leaderMotor.setInverted(false);
        m_followerMotor.setInverted(false);

        // m_followerMotor.follow(m_leaderMotor);

        // initShuffleBoard();
        setDefaultCommand(new DefaultHookCommand(this, m_controller));
    }

    public double getLeadEncoderValue() {
        return m_leftEncoder.getPosition();
    }

    public double getFollowEncoderValue() {
        return m_rightEncoder.getPosition();
    }

    public void setHookSpeed(double speed) {
        speed = MathUtil
                .clamp(speed, -HookConstants.maxOutputPercent, HookConstants.maxOutputPercent);
        m_leaderMotor.set(speed);
    }

    public void setHookSpeedRight(double speed) {
        speed = MathUtil
                .clamp(speed, -HookConstants.maxOutputPercent, HookConstants.maxOutputPercent);
        m_leaderMotor.set(speed);
    }

    public void setHookSpeedLeft(double speed) {
        speed = MathUtil
                .clamp(speed, -HookConstants.maxOutputPercent, HookConstants.maxOutputPercent);
        m_followerMotor.set(speed);
    }

    public void setHookSpeedAdmin(double speed) {
        m_leaderMotor.set(speed);
    }

    public double getLeadMotorSpeed() {
        return m_leaderMotor.get();
    }

    public double getFollowMotorSpeed() {
        return m_followerMotor.get();
    }

    public void initShuffleBoard() {
        Shuffleboard.getTab("Hook").addDouble("Leader Hook Value: ", () -> getLeadEncoderValue());
        Shuffleboard.getTab("Hook")
                .addDouble("Follower Hook Value: ", () -> getFollowEncoderValue());
        Shuffleboard.getTab("Hook").addDouble("Leader Hook Speed: ", () -> getLeadMotorSpeed());
        Shuffleboard.getTab("Hook").addDouble("Follower Hook Speed: ", () -> getFollowMotorSpeed());

    }

    public void stopSystem() {
        m_leaderMotor.stopMotor();

    }

}

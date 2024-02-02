// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

/**
 * ArmSystem.
 */
public class ArmSystem extends SubsystemBase {

    public final CANSparkMax m_armMotor = new CANSparkMax(ArmConstants.armMotorID,
            MotorType.kBrushless);
    public final DutyCycleEncoder m_ArmEncoder = new DutyCycleEncoder(
            ArmConstants.armEncoderChannel);

    public ArmSystem() {
        initShuffleBoard();
    }

    public double getArmAngle() {
        return m_ArmEncoder.getAbsolutePosition();
    }

    public void setArmSpeed(double speed) {
        m_armMotor.set(speed);
    }

    @Override
    public void periodic() {
    }

    public void initShuffleBoard() {
        Shuffleboard.getTab("Arm").add("Arm Angle: ", m_ArmEncoder.getAbsolutePosition());
    }

    /**
     * Stop the arm system.
     */
    public void stopSystem() {
        m_armMotor.stopMotor();
    }
}

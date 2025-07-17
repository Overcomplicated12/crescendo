package org.frc6423.robot.subsystems.intake;

import static org.frc6423.robot.subsystems.intake.IntakeConstants.PIVOT_ABS_ENCODER_CAN_BUS;
import static org.frc6423.robot.subsystems.intake.IntakeConstants.PIVOT_ABS_ENCODER_CAN_ID;
import static org.frc6423.robot.subsystems.intake.IntakeConstants.PIVOT_CAN_BUS;
import static org.frc6423.robot.subsystems.intake.IntakeConstants.PIVOT_CAN_ID;
import static org.frc6423.robot.subsystems.intake.IntakeConstants.ROLLER_CAN_BUS;
import static org.frc6423.robot.subsystems.intake.IntakeConstants.ROLLER_CAN_ID;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.geometry.Rotation2d;

public class IntakeHardwareReal extends IntakeHardware {
    
    private final TalonFX pivot = new TalonFX(PIVOT_CAN_ID, PIVOT_CAN_BUS);
    private final TalonFX roller = new TalonFX(ROLLER_CAN_ID, ROLLER_CAN_BUS);

    private final CANcoder pivotAbsEncoder = new CANcoder(PIVOT_ABS_ENCODER_CAN_ID, PIVOT_ABS_ENCODER_CAN_BUS);
    
    private final TalonFXConfiguration pivotConf = new TalonFXConfiguration();
    private final TalonFXConfiguration rollerConf = new TalonFXConfiguration();

    private final PositionTorqueCurrentFOC poseReq = new PositionTorqueCurrentFOC(0.0);
    private final VoltageOut voltReq = new VoltageOut(0.0);
    private final VelocityTorqueCurrentFOC velReq = new VelocityTorqueCurrentFOC(0.0);

    public IntakeHardwareReal(){
        pivotConf.Audio.BeepOnBoot = true;
        pivotConf.Audio.BeepOnConfig = true;

        pivotConf.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        pivotConf.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        
        pivotConf.CurrentLimits.SupplyCurrentLimitEnable = true;
        pivotConf.CurrentLimits.SupplyCurrentLimit = 40.0;
        pivotConf.CurrentLimits.StatorCurrentLimitEnable = true;
        pivotConf.CurrentLimits.StatorCurrentLimit = 80.0;

        pivotConf.TorqueCurrent.PeakForwardTorqueCurrent = 80.0;
        pivotConf.TorqueCurrent.PeakReverseTorqueCurrent = 80.0;


        pivotConf.Feedback.FeedbackRemoteSensorID = PIVOT_ABS_ENCODER_CAN_ID;
        pivotConf.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;

        pivot.getConfigurator().apply(pivotConf);


        rollerConf.Audio.BeepOnBoot = true;
        rollerConf.Audio.BeepOnConfig = true;

        rollerConf.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        rollerConf.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        
        rollerConf.CurrentLimits.SupplyCurrentLimitEnable = true;
        rollerConf.CurrentLimits.SupplyCurrentLimit = 20.0;
        

        roller.getConfigurator().apply(rollerConf);






    }


    @Override
    public boolean noteDetected() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'noteDetected'");
    }

    @Override
    public Rotation2d getPivotAngle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPivotAngle'");
    }

    @Override
    public void setPivotVolts(double volts) {
        pivot.setControl(voltReq.withOutput(volts));
    }

    @Override
    public void setRollerVolts(double volts) {
        roller.setControl(voltReq.withOutput(volts));
    }

    @Override
    public void setPivotAngle(Rotation2d angle) {
        pivot.setControl(poseReq.withPosition(angle.getRotations()));
    }

    @Override
    public void setRollerSpeed(double speedRps) {
        roller.setControl(velReq.withVelocity(speedRps));
    }
    
}

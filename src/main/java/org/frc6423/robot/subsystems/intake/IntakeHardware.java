package org.frc6423.robot.subsystems.intake;

import edu.wpi.first.math.geometry.Rotation2d;

public abstract class IntakeHardware {

    //SETTERS

    public abstract void setPivotVolts(double volts);

    public abstract void setRollerVolts(double volts);

    public abstract void setPivotAngle(Rotation2d angle);

    public abstract void setRollerSpeed(double speedRps);
}

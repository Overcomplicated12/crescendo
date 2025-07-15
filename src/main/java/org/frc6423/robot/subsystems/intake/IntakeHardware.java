package org.frc6423.robot.subsystems.intake;

import edu.wpi.first.math.geometry.Rotation2d;

/**
 * Reperesents generalized intake subsystem hardware
 */
public abstract class IntakeHardware {

    /**
     * @return true if 1/3 limit switches are pressed
     */
    public abstract boolean noteDetected();

    /**
     * @return {@Link Rotation2d} representing the pivot motor angle
     */
    public abstract Rotation2d getPivotAngle();
    
    /**
     * Run pivot motor with specified voltage
     * 
     * @param volts desired voltage output
     */
    public abstract void setPivotVolts(double volts);

    /**
     * Run roller motor with specified voltage
     * 
     * @param volts desired voltage output
     */
    public abstract void setRollerVolts(double volts);

    /**
     * Run pivot motor to specified position
     * 
     * @param angle {@Link Rotation2d} representin desired angle
     */
    public abstract void setPivotAngle(Rotation2d angle);

    /**
     * Run roller motor at specified speed
     * 
     * @param speedRps desired speed in rotations per second
     */
    public abstract void setRollerSpeed(double speedRps);
    
}
// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package src.org.usfirst.frc6468.cubeMover2001.subsystems;

import src.org.usfirst.frc6468.cubeMover2001.commands.*;
import src.org.usfirst.frc6468.cubeMover2001.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class verticalLift extends Subsystem {

   private final SpeedController liftMotor = RobotMap.verticalLiftliftMotor;

   // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
   
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
   
    }
}


// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.



package src.org.usfirst.frc6468.cubeMover2001;

import src.org.usfirst.frc6468.cubeMover2001.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 *@author Will English
 *@author Aditya 
 */
public class OI {
	
    public OI() {
    	
    	Joystick joystick = new Joystick(1);
    	XboxController gamePad = new XboxController(0);
 

        double LStickY = gamePad.getY();
		boolean rightBump = gamePad.getBumper(GenericHID.Hand.kLeft);
        boolean leftBump = gamePad.getBumper(GenericHID.Hand.kRight);
        boolean joyTrig = joystick.getTrigger();
        boolean pressed = true;
    	
    	
    	new JoystickButton(joystick, 3).whenPressed(new climb());
        if(LStickY != 0) new driveStraight();
        if(rightBump == pressed) new slideRight();
        if(leftBump == pressed) new slideLeft();
        if(joystick.getY() > 0) new liftUp();  
        if(joystick.getY() < 0) new dropDown();
        if(joyTrig == pressed)

        
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous", new Autonomous());
        SmartDashboard.putData("closeClaw", new closeClaw());
        SmartDashboard.putData("driveStraight", new driveStraight());
        SmartDashboard.putData("slideLeft", new slideLeft());
        SmartDashboard.putData("slideRight", new slideRight());
        SmartDashboard.putData("moveWithSticks", new moveWithSticks());
        SmartDashboard.putData("liftUp", new liftUp());
        SmartDashboard.putData("dropDown", new dropDown());
        SmartDashboard.putData("climb", new climb());

    }
}


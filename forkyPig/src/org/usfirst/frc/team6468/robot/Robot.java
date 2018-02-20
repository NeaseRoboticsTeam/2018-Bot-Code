/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6468.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.*;
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kLeftAuto = "Left Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	private XboxController  gamePad = new XboxController(1);
	private Joystick joy = new Joystick(0);
	private JoystickButton button1 = new JoystickButton(joy, 1);
	private JoystickButton button2 = new JoystickButton(joy, 2);
	private Servo clawLeft = new Servo(1);
	private Servo clawRight = new Servo(0);
	WPI_TalonSRX _verticalMotor = new WPI_TalonSRX(6);
	WPI_TalonSRX _middleMotor = new WPI_TalonSRX(5);
	WPI_TalonSRX _leftSlave1 = new WPI_TalonSRX(1);
	WPI_TalonSRX _rightSlave1 = new WPI_TalonSRX(4);
	WPI_TalonSRX _frontLeftMotor = new WPI_TalonSRX(2); 
	WPI_TalonSRX _frontRightMotor = new WPI_TalonSRX(3);
	DifferentialDrive _drive = new DifferentialDrive(_frontLeftMotor, _frontRightMotor);

	private Timer timer = new Timer();
	DigitalInput microSwitch = new DigitalInput(0);
	
	@Override
	public void robotInit() 
	{
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("Left Side, kLeftAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		_rightSlave1.follow(_frontRightMotor);
    		_leftSlave1.follow(_frontLeftMotor);
	}

	public void closeClaw()
	{
		clawLeft.setAngle(0);
		clawRight.setAngle(180);
	}
	
	public void driveForward(double time)
	{
		_drive.tankDrive(.5, .5);
		Thread.sleep(time);		    
		_drive.tankDrive(0, 0);
	}
		public void turnLeft()
	{
		_drive.tankDrive(.5, .5);
		Thread.sleep(time);		    
		_drive.tankDrive(0, 0);
	}
				    
	public void openClaw()
	{
		clawLeft.setAngle(180);
		clawRight.setAngle(0);
	}
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() 
	{
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() 
	{
		
		switch (m_autoSelected) 
		{
			case kLeftAuto:
				_drive.tankDrive(.5, .5);
				Thread.sleep(1000);
				
				

				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() 
	{
		
		double clawDeg = claw.getAngle();
		double RTrig = gamePad.getTriggerAxis(GenericHID.Hand.kRight);
		double LTrig = gamePad.getTriggerAxis(GenericHID.Hand.kLeft);
		double RStickX = gamePad.getX(GenericHID.Hand.kRight);
		double LStickY = -gamePad.getY(GenericHID.Hand.kLeft);
		boolean RBump = gamePad.getBumper(GenericHID.Hand.kRight);
		boolean LBump = gamePad.getBumper(GenericHID.Hand.kLeft);
		double joyY = joy.getY();
		boolean joyTrig = joy.getRawButton(1);
		boolean joyButton = joy.getRawButton(2);
		boolean switchState = microSwitch.get();
		if(switchState == true && joyY < 0) _verticalMotor.set(0); 
		if(RBump == true && LBump == false) _middleMotor.set(.5);
		else if(RBump == false && LBump == true) _middleMotor.set(-.5);
		else _middleMotor.set(0);
		_drive.arcadeDrive(LStickY, RStickX);
		if(RBump==true) _frontLeftMotor.set(0);
		System.out.println(_frontLeftMotor.get());
		
	
		
		if(joyY != 0) _verticalMotor.set(joyY);
		else _verticalMotor.set(0);
		
		if(microSwitch.get() == true) 

		if(joyTrig == true) 

			
		

		
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() 
	{
	}
}

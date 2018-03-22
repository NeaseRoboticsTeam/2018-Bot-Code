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
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	private XboxController  gamePad = new XboxController(1);
	//private XboxController servocontroller = new XboxController(0);
	//private Joystick joy = new Joystick(0);
	//private JoystickButton button1 = new JoystickButton(joy, 1);
	//private JoystickButton button2 = new JoystickButton(joy, 2);
	private Servo clawLeft = new Servo(0);
	private Servo clawRight = new Servo(1);
	WPI_TalonSRX _verticalMotor = new WPI_TalonSRX(6);
	WPI_TalonSRX _leftSlave1 = new WPI_TalonSRX(1);
	WPI_TalonSRX _rightSlave1 = new WPI_TalonSRX(4);
	WPI_TalonSRX _frontLeftMotor = new WPI_TalonSRX(2); 
	WPI_TalonSRX _frontRightMotor = new WPI_TalonSRX(3);
	DifferentialDrive _drive = new DifferentialDrive(_frontLeftMotor, _frontRightMotor);
	private Timer timer = new Timer();
	DigitalInput microSwitch = new DigitalInput(1);
	
	int angle = 90;
	
	public void closeClaw() {
		//clawLeft.setAngle(clawLeft.getAngle()+5);
		/*clawLeft.setAngle(145);
		clawRight.setAngle(clawRight.getAngle()-5);
		System.out.println(clawRight.getAngle());
		*/
		angle++;
		clawLeft.setAngle(140);
		//clawRight.setAngle(73.5);
		
	}
	
	public void openClaw() {
		/*if(clawRight.getAngle() <= 120 && clawLeft.getAngle() >= 70) {
		clawLeft.setAngle(clawLeft.getAngle()-5);
		clawRight.setAngle(clawRight.getAngle()+5);
		System.out.println(clawRight.getAngle());
		}
		*/
		clawLeft.setAngle(90);
		clawRight.setAngle(90);
		}

	
	
	@Override
	public void robotInit() {
		//clawLeft.setAngle(180);
		//clawRight.setAngle(0);
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		_rightSlave1.follow(_frontRightMotor);
    	_leftSlave1.follow(_frontLeftMotor);
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
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
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
	public void teleopPeriodic() {
		boolean open = true;
		boolean closed = false;
		double clawState = clawLeft.getAngle();
		//double joyY = joy.getY();
		double clawDeg = clawLeft.getAngle();
		double RTrig = gamePad.getTriggerAxis(GenericHID.Hand.kRight);
		double LTrig = gamePad.getTriggerAxis(GenericHID.Hand.kLeft);
		double RStickX = gamePad.getX(GenericHID.Hand.kRight);
		double LStickY = -gamePad.getY(GenericHID.Hand.kLeft);
		boolean RBump = gamePad.getBumper(GenericHID.Hand.kRight);
		boolean LBump = gamePad.getBumper(GenericHID.Hand.kLeft);
		//boolean joyTrig = joy.getRawButton(1);
		//boolean joyButton = joy.getRawButton(2);
		boolean switchState = microSwitch.get();
		
		//VERTICAL SAFETY CONDITOIN
		//if(switchState == true && joyY < 0) _verticalMotor.set(0); 
		
		//DRIVE FUNCTION
		_drive.arcadeDrive(LStickY*.75, RStickX*.75);
		
		//if(RBump==true) _frontLeftMotor.set(0); 
		//System.out.println(_frontLeftMotor.get());
		
	//System.out.println(joyY);
		//_verticalMotor.set(joyY);
		
		//VERTICAL LIFT **TEST JOYSTICK**
		/*if(LTrig != 0 && RTrig == 0) _verticalMotor.set(LTrig);
		else if(RTrig != 0 && LTrig == 0) _verticalMotor.set(-RTrig);
		else _verticalMotor.set(0);
		System.out.println(_verticalMotor.get());
*/
		/*
		if(gamePad.getAButton() == true)
		{
			clawLeft.setAngle(180);
			clawRight.setAngle(0);
		}
		if(gamePad.getAButton() == true)
		{
			clawLeft.setAngle(0);
			clawRight.setAngle(180);
		}
		*/
		//System.out.println( clawLeft.get());
		if(LTrig != 0 ) openClaw();
		if(RTrig != 0 ) closeClaw();
		//else clawLeft.setAngle(clawLeft.getAngle());
		//if(clawState == open) openClaw();
		//if(clawState == closed) closeClaw();
	/*	for(double x = clawDeg; joyTrig == true && x < 180; x++) {
		    System.out.println("+++++++++++++++");
			System.out.println(joyTrig+" "+x);
			claw.setAngle(x);
			clawDeg = x;
		}
		for(double x = clawDeg; joyButton == true && x > 0; x--) {
			System.out8.println("--------------");
			System.out.println(joyButton+" "+x);
			claw.setAngle(x);
			clawDeg = x;
		}
		*/
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

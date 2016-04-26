package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.Loopable;
import org.usfirst.frc.team115.robot.controllers.Controller;
import org.usfirst.frc.team115.robot.controllers.FlywheelBangBangController;
import org.usfirst.frc.team115.robot.controllers.FlywheelPidController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class Flywheel extends Loopable {

	CANTalon flywheelMotor;
	String name;
	Controller controller;
	
	public Flywheel(String name, int talonId, boolean reverse) {
		this.flywheelMotor = new CANTalon(talonId);
		this.name = name;
		
		flywheelMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		flywheelMotor.reverseSensor(reverse);
		flywheelMotor.configEncoderCodesPerRev(4096);
		
		flywheelMotor.changeControlMode(TalonControlMode.Speed);
	}
	
	public String getName() {
		return name;
	}
	
	public void setSpeedPidSetpoint(double speed) {
		flywheelMotor.changeControlMode(TalonControlMode.Speed);
		if(!(controller instanceof FlywheelPidController))
			controller = new FlywheelPidController(flywheelMotor, 0.3, 0.0, 0.0, 0.2); //tune pidf
		((FlywheelPidController)(controller)).setSetpoint(speed);
	}
	
	public void setSpeedBangBangSetpoint(double speed) {
		System.out.println("setting setpoint: " + speed);
		flywheelMotor.changeControlMode(TalonControlMode.PercentVbus);
		if(!(controller instanceof FlywheelBangBangController))
			controller = new FlywheelBangBangController(flywheelMotor, 2.0, 1.0, -1.0); //2nd param = tolerance
		((FlywheelBangBangController)(controller)).setSetpoint(speed);
	}
	
	public void setOpenLoop(double speed) {
		controller = null;
		flywheelMotor.changeControlMode(TalonControlMode.PercentVbus);
		flywheelMotor.set(speed);
	}
	
	public double getSpeedRPM() {
		return flywheelMotor.getSpeed();
	}
	
	public void setSpeed(double speed) {
		flywheelMotor.changeControlMode(TalonControlMode.PercentVbus);
		flywheelMotor.set(speed);
	}
	
	public double getVoltage() {
		return flywheelMotor.getOutputVoltage() / flywheelMotor.getBusVoltage();
	}
	
	public void log() {
		System.out.println("tout: " + flywheelMotor.getOutputVoltage() / flywheelMotor.getBusVoltage());
		System.out.println("tspd: " + flywheelMotor.getSpeed());
		System.out.println("terr: " + flywheelMotor.getClosedLoopError());
		//System.out.println("ttrg: " + controller.getSetpoint());
	}
	
	public String getLog() {
		return "tout: " + getVoltage() + 
				", tspd: " + getSpeedRPM() + ", terr: " + flywheelMotor.getClosedLoopError();
				//", ttrg: " + controller.getSetpoint();
	}
	
	@Override
	public void update() {
		if(controller != null)
			controller.update();
		log();
	}

}

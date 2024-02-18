// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  CANSparkMax m_RightClimberMotor = new CANSparkMax(Constants.ClimberConstants.RightClimberID, MotorType.kBrushless);
  CANSparkMax m_LeftClimberMotor = new CANSparkMax(Constants.ClimberConstants.LeftClimberID, MotorType.kBrushless);

    private SparkPIDController m_ClimberPIDController;
    public double c_kP, c_kI, c_kD, c_kFF;
    

  public Climber() {
    c_kP = Constants.ClimberConstants.climber_P;
    c_kI = Constants.ClimberConstants.climber_I;
    c_kD = Constants.ClimberConstants.climber_D;
    c_kFF = Constants.ClimberConstants.climber_FF;

    m_RightClimberMotor.restoreFactoryDefaults();
    m_LeftClimberMotor.restoreFactoryDefaults();
    
    m_RightClimberMotor.setSmartCurrentLimit(40);
    m_LeftClimberMotor.setSmartCurrentLimit(40);

    m_ClimberPIDController = m_RightClimberMotor.getPIDController();
    m_ClimberPIDController = m_LeftClimberMotor.getPIDController();

    m_ClimberPIDController.setP(c_kP);

    m_ClimberPIDController.setI(c_kI);

    m_ClimberPIDController.setD(c_kD);

    m_ClimberPIDController.setFF(c_kFF);

    m_ClimberPIDController.setOutputRange(Constants.minMaxOutputConstants.kMinOutput, Constants.minMaxOutputConstants.kMaxOutput);
}
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void ClimbRight(Double climberRightSup){
        //set the velocity of the motor based on input value
        m_RightClimberMotor.set(climberRightSup);
 }
  public void ClimbLeft(Double climberLeftSup){
        //set the velocity of the motor based on input value
        m_LeftClimberMotor.set(climberLeftSup);
}

    public void stop(){
        //stop the motor
        m_ClimberPIDController.setReference(0,CANSparkBase.ControlType.kVelocity);
    }



}

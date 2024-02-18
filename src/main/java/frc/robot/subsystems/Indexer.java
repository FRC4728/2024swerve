package frc.robot.subsystems;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class Indexer extends SubsystemBase{

    double go = 0;
    double Timer = 0;
    double state = 1;
    Timer m_timer = new Timer();
    
    CANSparkMax m_topIndexMotor = new CANSparkMax(Constants.IndexerConstants.topIndexMotorID, MotorType.kBrushless);
    
    /*Digital input for photoeye */ 
    final DigitalInput m_photoswitch = new DigitalInput(Constants.IndexerConstants.photoswitchID);

    private SparkPIDController m_TopIndexerPIDController;
    public double s_kP, s_kI, s_kD, s_kFF;
    public boolean photoswitch;

    public Indexer() {
        s_kP = Constants.IndexerConstants.index_P;
        s_kI = Constants.IndexerConstants.index_I;
        s_kD = Constants.IndexerConstants.index_D;
        s_kFF = Constants.IndexerConstants.index_FF;

        m_topIndexMotor.restoreFactoryDefaults();

        m_TopIndexerPIDController = m_topIndexMotor.getPIDController();

        m_TopIndexerPIDController.setP(s_kP);

        m_TopIndexerPIDController.setI(s_kI);

        m_TopIndexerPIDController.setD(s_kD);

        m_TopIndexerPIDController.setFF(s_kFF);

        m_TopIndexerPIDController.setOutputRange(Constants.minMaxOutputConstants.kMinOutput, Constants.minMaxOutputConstants.kMaxOutput);
    }

    public void periodic(){
        //Output the velocity of the shooter motors to the dashboard
        SmartDashboard.putNumber("Top Indexer Motor Velocity:",m_topIndexMotor.getEncoder().getVelocity());

        /*Try to get the values of the photoswitch?? */
        photoswitch = m_photoswitch.get();
        SmartDashboard.putBoolean("Velo photoswitch",photoswitch);

    }

    public boolean getphotoswitch(){
        return photoswitch;
    }

    public void runIndexshoot(int Velo){
        //set the velocity of the motor based on input value
           
                m_TopIndexerPIDController.setReference(Velo,CANSparkBase.ControlType.kVelocity);
            }

    public void runIndex(int Velo){
        //set the velocity of the motor based on input value
            if (photoswitch) {
               new WaitCommand(5);
                m_TopIndexerPIDController.setReference(0,CANSparkBase.ControlType.kVelocity);
            }
            else {
                m_TopIndexerPIDController.setReference(Velo,CANSparkBase.ControlType.kVelocity);
            }

    }
    public void runIndexFWD(int VeloFWD){
        //set the velocity of the motor based on input value
        
            m_TopIndexerPIDController.setReference(VeloFWD,CANSparkBase.ControlType.kVelocity);
        
    }
    public void runIndexREV(int VeloREV){
        //set the velocity of the motor based on input value
      
            m_TopIndexerPIDController.setReference(VeloREV,CANSparkBase.ControlType.kVelocity);
        
        
    }
    public void stop(){
        //stop the motor
        m_TopIndexerPIDController.setReference(0,CANSparkBase.ControlType.kVelocity);
    }
}

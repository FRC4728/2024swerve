package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class ShootAmp extends Command{
    private Shooter s_Shooter;
    private Indexer s_Indexer;
    private int c_velocityTop, c_velocityBottom, c_Velo;


    public ShootAmp(Shooter s_Shooter, Indexer s_Indexer, int c_velocityTop, int c_velocityBottom, int c_Velo){
        this.s_Shooter = s_Shooter;
        this.s_Indexer = s_Indexer;
        this.c_velocityTop = c_velocityTop;
        this.c_velocityBottom = c_velocityBottom;
        this.c_Velo = c_Velo;
        addRequirements(s_Shooter, s_Indexer);
    }
    
    public void execute(){
        //Set the shooter to the desired speed
        s_Shooter.shootAmp(c_velocityTop,c_velocityBottom);
        s_Indexer.runIndexshoot(c_Velo);
    }
    public void isInterrupted(){
        //Stop shooter if command is interrupted
        s_Shooter.stop();
        s_Indexer.stop();

    }
    public void end(){
        //stop shooter when command ends
        s_Shooter.stop();
        s_Indexer.stop();
    }
    public boolean isFinished(){
        return false;
    }
}

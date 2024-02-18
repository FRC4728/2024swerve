package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class ShootSpeaker extends Command{
    private Shooter s_Shooter;
    private Indexer s_Indexer;
    private int c_velocity;
    private int c_Velo;

    public ShootSpeaker(Shooter s_Shooter,Indexer s_Indexer, int c_velocity, int c_Velo){
        this.s_Shooter = s_Shooter;
        this.s_Indexer = s_Indexer;
        this.c_velocity = c_velocity;
        this.c_Velo = c_Velo;
        addRequirements(s_Shooter, s_Indexer);
    }
    
    public void execute(){
        //Set the shooter to the desired speed
        s_Shooter.shootSpeaker(c_velocity);
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

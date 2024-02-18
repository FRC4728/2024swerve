package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;

public class RunIndexerREV extends Command{
    private Indexer s_Indexer;
    private int c_velocity;

   

    public RunIndexerREV(Indexer s_Indexer, int c_velocity){
        this.s_Indexer = s_Indexer;
        this.c_velocity = c_velocity;
        addRequirements(s_Indexer);
    }
    
    public void execute(){
        //Set the shooter to the desired speed
        s_Indexer.runIndexREV(c_velocity);
    }
    public void isInterrupted(){
        //Stop shooter if command is interrupted
        s_Indexer.stop();
    }
    public void end(){
        //stop shooter when command ends
        s_Indexer.stop();
    }
    public boolean isFinished(){
        return false;
    }
}

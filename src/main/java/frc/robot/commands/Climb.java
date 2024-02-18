package frc.robot.commands;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class Climb extends Command {
    private Climber s_Climber;
    private DoubleSupplier climberRightSup;
    private DoubleSupplier climberLeftSup;
  
  
  public Climb(Climber s_Climber, DoubleSupplier climberRightSup, DoubleSupplier climberLeftSup) {
    
    this.s_Climber = s_Climber;
    addRequirements(s_Climber);

    this.climberRightSup = climberRightSup;
    this.climberLeftSup = climberLeftSup;
    
  
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /* Get Values, Deadband*/
        double climberRight = MathUtil.applyDeadband(climberRightSup.getAsDouble(), Constants.stickDeadband);
        double climberLeft = MathUtil.applyDeadband(climberLeftSup.getAsDouble(), Constants.stickDeadband);
        
        /*Climb */
        s_Climber.ClimbRight(climberRight);
        s_Climber.ClimbLeft(climberLeft);
        
  }
  public void isInterrupted(){
    //Stop shooter if command is interrupted
    s_Climber.stop();
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stops Climber if command is interrupted
    s_Climber.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

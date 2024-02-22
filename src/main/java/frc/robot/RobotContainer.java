package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick operator = new Joystick(1);

    /* Drive Controls */
    private final int translationAxis = 1;
    private final int strafeAxis = 0;
    private final int rotationAxis = 4;

    /* Driver Buttons */
    private final JoystickButton runIndex = new JoystickButton(driver,1); //A
    private final JoystickButton shootSpeaker = new JoystickButton(driver,2); //LB
    private final JoystickButton shootAmp = new JoystickButton(driver,3); //RB
    private final JoystickButton ShootREV = new JoystickButton(driver, 4); //Y
    private final JoystickButton swerveLowSpeed = new JoystickButton(driver,5); //Shoulder Left
    private final JoystickButton swerveHighSpeed = new JoystickButton(driver,6);
    private final JoystickButton robotCentric = new JoystickButton(driver,7); //Start
    private final JoystickButton zeroGyro = new JoystickButton(driver,8); //Back
    
    /* Operator Controls */
    private final int climberRight = 1;
    private final int climberLeft = 5;

    /* Operator Buttons */
    private final JoystickButton runIndexFWD = new JoystickButton(operator,2); //B
    private final JoystickButton runIndexREV = new JoystickButton(operator,4); //X

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final Shooter s_Shooter = new Shooter();
    private final Indexer s_Indexer = new Indexer();
    private final Climber s_Climber = new Climber();

    private final SendableChooser<Command> autoChooser;


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis)*translationMultiplier, 
                () -> -driver.getRawAxis(strafeAxis)*strafeMultiplier, 
                () -> -driver.getRawAxis(rotationAxis)*rotateMultiplier, 
                () -> zeroGyro.getAsBoolean()

            )
        );

        s_Climber.setDefaultCommand(
            new Climb(
                s_Climber,
                () -> operator.getRawAxis (climberRight)*1,
                () -> operator.getRawAxis (climberLeft)*-1
            )
        );

    NamedCommands.registerCommand("ShootSpeaker", ShootSpeaker());
    NamedCommands.registerCommand("RunIndex", RunIndex());
    NamedCommands.registerCommand("StopIndex", StopIndex());

    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Mode", autoChooser);

    configureBindings();
    // Configure the button bindings
    configureButtonBindings();
    }

    private void configureBindings(){
        SmartDashboard.putData("Right Auto",new PathPlannerAuto("Shoot-Pick-Shoot-Auto"));
        SmartDashboard.putData("Middle Auto",new PathPlannerAuto("MiddleAuto"));
        SmartDashboard.putData("Left Auto",new PathPlannerAuto("LeftAuto"));
    }
    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        robotCentric.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));

        /*Create binding for shooting speaker */
        shootSpeaker.whileTrue(new ShootSpeaker(s_Shooter, s_Indexer,Constants.ShooterConstants.combined_shooterVelo,Constants.IndexerConstants.indexVelo));
        shootSpeaker.whileFalse(new ShootSpeaker(s_Shooter,s_Indexer, 0,0));

        /*Create binding for shooting amp */
        shootAmp.whileTrue(new ShootAmp(s_Shooter, s_Indexer,Constants.ShooterConstants.top_shooterVelo,Constants.ShooterConstants.bottom_shooterVelo, Constants.IndexerConstants.indexVelo));
        shootAmp.whileFalse(new ShootAmp(s_Shooter,s_Indexer, 0,0,0));

        /*Create binding for shooter running in reverse */
        ShootREV.whileTrue(new ShootRev(s_Shooter,Constants.ShooterConstants.rev_shooterVelo));
        ShootREV.whileFalse(new ShootRev(s_Shooter,0));
        
        /*Create binding for running indexer */
        runIndex.whileTrue(new RunIndexer(s_Indexer, Constants.IndexerConstants.indexVelo));
        runIndex.whileFalse(new RunIndexer(s_Indexer, 0));
        
        runIndexFWD.whileTrue(new RunIndexerFWD(s_Indexer, Constants.IndexerConstants.IndexVeloFWD));
        runIndexFWD.whileFalse(new RunIndexerFWD(s_Indexer, 0));
        
        runIndexREV.whileTrue(new RunIndexerREV(s_Indexer, Constants.IndexerConstants.indexVeloREV));
        runIndexREV.whileFalse(new RunIndexerREV(s_Indexer, 0));

        /*Create binding for swerve speed */
        
        swerveHighSpeed.whileTrue(new InstantCommand(() -> Contstants.speedMultiplierConstants.HStranslationMultiplier));
        swerveHighSpeed.whileTrue(new InstantCommand(() -> Contstants.speedMultiplierConstants.HSstrafeMultiplier));
        swerveHighSpeed.whileTrue(new InstantCommand(() -> Contstants.speedMultiplierConstants.HSrotationMultiplier));

        swerveLowSpeed.whileTrue(new InstantCommand(() -> Contstants.speedMultiplierConstants.LStranslationMultiplier));
        swerveLowSpeed.whileTrue(new InstantCommand(() -> Contstants.speedMultiplierConstants.LSstrafeMultiplier));
        swerveLowSpeed.whileTrue(new InstantCommand(() -> Contstants.speedMultiplierConstants.LSrotationMultiplier));
      
    }

    /**P
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return autoChooser.getSelected();
    }

    public Command ShootSpeaker(){
        return new SequentialCommandGroup(
                    new ShootSpeaker(s_Shooter,s_Indexer, Constants.ShooterConstants.combined_shooterVelo,Constants.IndexerConstants.indexVelo),
                    new WaitCommand(1),
                    new StopShooter(s_Shooter));
    }

    public Command RunIndex(){
        return new SequentialCommandGroup(
                    new RunIndexer(s_Indexer, Constants.IndexerConstants.indexVelo)
        );
    }

    public Command StopIndex(){
        return new SequentialCommandGroup(
                    new RunIndexer(s_Indexer, 0)            
        );
    }
}

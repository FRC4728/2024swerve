package frc.robot;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.COTSTalonFXSwerveConstants;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {
    public static final double stickDeadband = 0.1;

    public static final class Swerve {
        public static final int pigeonID = 0;

        public static final COTSTalonFXSwerveConstants chosenModule =  //TODO: This must be tuned to specific robot
        COTSTalonFXSwerveConstants.SDS.MK4i.Falcon500(COTSTalonFXSwerveConstants.SDS.MK4i.driveRatios.L2);

        /* Drivetrain Constants */
        public static final double trackWidth = Units.inchesToMeters(20.5); //TODO: This must be tuned to specific robot
        public static final double wheelBase = Units.inchesToMeters(20.5); //TODO: This must be tuned to specific robot
        public static final double wheelCircumference = chosenModule.wheelCircumference;

        /* Swerve Kinematics 
         * No need to ever change this unless you are not doing a traditional rectangular/square 4 module swerve */
         public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

        /* Module Gear Ratios */
        public static final double driveGearRatio = chosenModule.driveGearRatio;
        public static final double angleGearRatio = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final InvertedValue angleMotorInvert = chosenModule.angleMotorInvert;
        public static final InvertedValue driveMotorInvert = chosenModule.driveMotorInvert;

        /* Angle Encoder Invert */
        public static final SensorDirectionValue cancoderInvert = chosenModule.cancoderInvert;

        /* Swerve Current Limiting */
        public static final int angleCurrentLimit = 25;//25
        public static final int angleCurrentThreshold = 40;//40
        public static final double angleCurrentThresholdTime = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveCurrentLimit = 30;//35
        public static final int driveCurrentThreshold = 40; //60
        public static final double driveCurrentThresholdTime = 0.1;
        public static final boolean driveEnableCurrentLimit = true;

        /* These values are used by the drive falcon to ramp in opebluen loop and closed loop driving.
         * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
        public static final double openLoopRamp = 0.25;
        public static final double closedLoopRamp = 0.0;

        /* Angle Motor PID Values */
        public static final double angleKP = chosenModule.angleKP;
        public static final double angleKI = chosenModule.angleKI;
        public static final double angleKD = chosenModule.angleKD;

        /* Drive Motor PID Values */
        public static final double driveKP = 0.12; //TODO: This must be tuned to specific robot
        public static final double driveKI = 0.0;
        public static final double driveKD = 0.0;
        public static final double driveKF = 0.0;

        /* Drive Motor Characterization Values From SYSID */
        public static final double driveKS = 0.32; //TODO: This must be tuned to specific robot
        public static final double driveKV = 1.51;
        public static final double driveKA = 0.27;

        /* Swerve Profiling Values */
        /** Meters per Second */
        public static final double maxSpeed = 4.5; //4.5 original value
        /** Radians per Second */
        public static final double maxAngularVelocity = 2 * Math.PI; //10.0 original value

        /* Neutral Modes */
        public static final NeutralModeValue angleNeutralMode = NeutralModeValue.Coast;
        public static final NeutralModeValue driveNeutralMode = NeutralModeValue.Brake;


        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 1;
            public static final int angleMotorID = 11;
            public static final int canCoderID = 0;
            public static final boolean isAngleMotorInverted = true;
            public static final boolean isDriveMotorInverted = true;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(142);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset, isAngleMotorInverted, isDriveMotorInverted);
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID =2;
            public static final int angleMotorID = 12;
            public static final int canCoderID = 1;
            public static final boolean isAngleMotorInverted = false;
            public static final boolean isDriveMotorInverted = false;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(140);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset, isAngleMotorInverted, isDriveMotorInverted);
        }
        
        /* Back Left Module - Module 2 */
        public static final class Mod2 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 3;
            public static final int angleMotorID = 13;
            public static final int canCoderID = 2;
            public static final boolean isAngleMotorInverted = true;
            public static final boolean isDriveMotorInverted = false;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(156);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset,isAngleMotorInverted, isDriveMotorInverted);
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 4;
            public static final int angleMotorID = 14;
            public static final int canCoderID = 3;
            public static final boolean isAngleMotorInverted = true;
            public static final boolean isDriveMotorInverted = false;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(210);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset, isAngleMotorInverted, isDriveMotorInverted);
        }
    }

    public static final class AutoConstants { //TODO: The below constants are used in the example auto, and must be tuned to specific robot
        public static final double kMaxSpeedMetersPerSecond = 1;
        public static final double kMaxAccelerationMetersPerSecondSquared = 1;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        public static final double kPXController = 1;
        public static final double kPYController = 1;
        public static final double kPThetaController = 1;
    
        /* Constraint for the motion profilied robot angle controller */
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
    }

    public static final class ShooterConstants {
        public static final int topShooterMotorID = 20;
        public static final int bottomShooterMotorID = 30;
        public static final double shooter_P = 6e-5;
        public static final double shooter_I = 0.0;
        public static final double shooter_D = 0.0;
        public static final double shooter_FF = 0.00019;
        public static final int top_shooterVelo = -800; // -800
        public static final int bottom_shooterVelo = -2400; // -2400
        public static final int combined_shooterVelo = -4250; // -2400
        public static final int rev_shooterVelo = -800; // -800

    }
    public static final class IndexerConstants {
        public static final int topIndexMotorID = 10;
        public static final double index_P = .00005;//.00005
        public static final double index_I = 0.0;
        public static final double index_D = 0.0;
        public static final double index_FF = 0.00015;//.000015
        public static final int photoswitchID = 4;
        public static final int indexVelo = 2000;
        public static final int IndexVeloFWD = 1500;
        public static final int indexVeloREV = -1500;
    }

    public static final class ClimberConstants {
        public static final int RightClimberID = 40;
        public static final int LeftClimberID = 50;
        public static final double climber_P = 6e-5;
        public static final double climber_I = 0.0;
        public static final double climber_D = 0.0;
        public static final double climber_FF = 0.00015;
        public static final int climberVelo = 3500;

    }

    public static final class minMaxOutputConstants {
        /* Define max and min outputs */
        public static final int maxOutputRPM = 5700;
        public static final int kMaxOutput = 1;
        public static final int kMinOutput = -1;
    }
}

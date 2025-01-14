package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Arclength;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Pose2dDual;
import com.acmerobotics.roadrunner.PosePath;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "RoadRunnerExample")
@Config
public final class RoadRunnerExample extends LinearOpMode {
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;


    public static double DEPOSIT_POSITION_X = 22;
    public static double DEPOSIT_POSITION_Y = 5*24+4;
    public static double DEPOSIT_POSITION_HEADING = 135;
    public static double DEPOSIT_POSITION_DIRECTION = 75;
    public void runOpMode() throws InterruptedException {
        lift = hardwareMap.get(DcMotor.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setTargetPosition(0);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armTurn = hardwareMap.get(DcMotor.class, "armTurn");
        armTurn.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armTurn.setTargetPosition(0);
        armTurn.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armTurn.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake = hardwareMap.get(CRServo.class, "intake");


        Pose2d beginPose = new Pose2d(8, 4*24+8.5, Math.toRadians(90));
        Pose2d depositPose = new Pose2d(DEPOSIT_POSITION_X, DEPOSIT_POSITION_Y, Math.toRadians(DEPOSIT_POSITION_HEADING));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        waitForStart();
        telemetry.addLine("Starting");
        telemetry.update();
        Actions.runBlocking(
            new SequentialAction(
                    new ParallelAction(
                            new MotorAction(lift, -1700, 1),
                            new MotorAction(armTurn, -1490, 0.5),
                            drive.actionBuilder(beginPose)
                                    .setTangent(0)
                                    .splineToLinearHeading(depositPose, Math.toRadians(DEPOSIT_POSITION_DIRECTION))
                                    .build()
                    ),
                    new CRServoAction(intake, 0.25),
                    new SleepAction(2)
                )
        );
        telemetry.addLine("Finished");
        telemetry.update();
    }
}

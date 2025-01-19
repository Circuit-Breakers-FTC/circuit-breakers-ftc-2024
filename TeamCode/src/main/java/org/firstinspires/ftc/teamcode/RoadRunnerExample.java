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
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "RoadRunnerExample")
@Config
public final class RoadRunnerExample extends LinearOpMode {
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;


    public static double DEPOSIT_POSITION_X = 21;
    public static double DEPOSIT_POSITION_Y = 127.5;
    public static double DEPOSIT_POSITION_HEADING = 135;
    public static double DEPOSIT_POSITION_DIRECTION = 75;
    public static double PICKUP_POSITION_X = 48;
    public static double PICKUP_POSITION_Y = 105;
    public static double PICKUP_POSITION_HEADING = 90;
    public static double PICKUP_POSITION_DIRECTION = 90;
    public static double PICKUP_POSITION_Y2 = 115;
    public static double PUSH_POSITION_END_X = 50;
    public static double PUSH_POSITION_END_Y = 115;
    public static double PUSH_POSITION_HEADING = 80;
    public static double PUSH_POSITION_DIRECTION = 180;
    public static double PICKUP_POSITION_Y3 = 123;
    public static int ARM_PICKUP = -2755;
    public static int ARM_BIN =-1490;
    public static double ARM_POWER = 1.0;
    public static double LIFT_POWER = 1.0;
    public static double DEPOSIT_SLEEP_TIME = 1.5;

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
        Pose2d pickupPose = new Pose2d(PICKUP_POSITION_X,PICKUP_POSITION_Y,Math.toRadians((PICKUP_POSITION_HEADING)));
        Pose2d pickupPose2 = new Pose2d(PICKUP_POSITION_X,PICKUP_POSITION_Y2,Math.toRadians((PICKUP_POSITION_HEADING)));
        Pose2d pushPose = new Pose2d(PUSH_POSITION_END_X,PUSH_POSITION_END_Y,Math.toRadians((PUSH_POSITION_HEADING)));
        Pose2d pickupPose3 = new Pose2d(PICKUP_POSITION_X,PICKUP_POSITION_Y3,Math.toRadians((PICKUP_POSITION_HEADING)));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        waitForStart();
        telemetry.addLine("Starting");
        telemetry.update();
        Actions.runBlocking(
            new SequentialAction(
                    new ParallelAction(
                            new MotorAction(armTurn,ARM_BIN, ARM_POWER),
                            new MotorAction(lift, -1700, LIFT_POWER),
                            drive.actionBuilder(beginPose)
                                    .setTangent(0)
                                    .splineToLinearHeading(depositPose, Math.toRadians(DEPOSIT_POSITION_DIRECTION))
                                    .build()
                    ),
                    new CRServoAction(intake, 0.35),
                    new SleepAction(DEPOSIT_SLEEP_TIME),
                    drive.actionBuilder(depositPose)
                            .setTangent(-90)
                            .splineToLinearHeading(pushPose, Math.toRadians(PUSH_POSITION_DIRECTION))
                            .build(),
                    new ParallelAction(
                           new MotorAction(armTurn, -2510, 0.5),
                           new MotorAction(lift, 0, 0.30),
                            drive.actionBuilder(pushPose)
                                    .setTangent(-135)
                                    .splineToLinearHeading(pickupPose, Math.toRadians(PICKUP_POSITION_DIRECTION))
                                    .build()
                    ),
                    new CRServoAction(intake, -1),
                    new MotorAction(armTurn, ARM_PICKUP, 0.5),
                    new SleepAction(1),
                    drive.actionBuilder(pickupPose)
                        .setTangent(90)
                        .splineToLinearHeading(pickupPose2, Math.toRadians(PICKUP_POSITION_DIRECTION))
                        .build(),
                    new SleepAction(0.5),
                    new ParallelAction(
                            new MotorAction(armTurn,ARM_BIN, 0.5),
                            new MotorAction(lift, -1700, 0.5),
                            drive.actionBuilder(pickupPose2)
                                    .setTangent(180)
                                    .splineToLinearHeading(depositPose, Math.toRadians(DEPOSIT_POSITION_DIRECTION))
                                    .build()
                    ),
                    new CRServoAction(intake, 0.35),
                    new SleepAction(2),
                    new ParallelAction(
                            new MotorAction(armTurn, -2510, 0.5),
                            new MotorAction(lift, 0, 0.30),
                            drive.actionBuilder(depositPose)
                                    .setTangent(-30)
                                    .splineToLinearHeading(pickupPose, Math.toRadians(PICKUP_POSITION_DIRECTION))
                                    .build()
                    ),
                    new CRServoAction(intake, -1),
                    new MotorAction(armTurn, ARM_PICKUP, 0.5),
                    drive.actionBuilder(pickupPose)
                            .setTangent(90)
                            .splineToLinearHeading(pickupPose3, Math.toRadians(PICKUP_POSITION_DIRECTION))
                            .build(),
                    new SleepAction(1),
                    new ParallelAction(
                        new MotorAction(armTurn,ARM_BIN, 0.5),
                        new MotorAction(lift, -1700, 0.5),
                        drive.actionBuilder(pickupPose2)
                           .setTangent(180)
                          .splineToLinearHeading(depositPose, Math.toRadians(DEPOSIT_POSITION_DIRECTION))
                           .build()
                    ),
                    new CRServoAction(intake, 0.35),
                    new SleepAction(2)
            )
        );

        telemetry.addLine("Finished");
        telemetry.update();
    }
}

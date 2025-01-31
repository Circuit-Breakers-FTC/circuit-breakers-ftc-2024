package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Roadrunner4HighBin")
@Config
public final class Roadrunner4High extends LinearOpMode {
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;


    public static double MAX_ARM_ERROR = 20;
    public static double MAX_LIFT_ERROR = 20;
    public static double DEPOSIT_POSITION_X = 21;
    public static double DEPOSIT_POSITION_Y = 127.5;
    public static double DEPOSIT_POSITION_HEADING = 135;
    public static double DEPOSIT_POSITION_DIRECTION = 75;
    // deposit inputs
    public static double PUSH_POSITION_END_X = 50;
    public static double PUSH_POSITION_END_Y = 115;
    public static double PUSH_POSITION_HEADING = 80;
    public static double PUSH_POSITION_DIRECTION = 180;
    // push inputs
    public static double PICKUP_POSITION_X = 48;
    public static double PICKUP_POSITION_Y = 105;
    public static double PICKUP_POSITION_HEADING = 90;
    public static double PICKUP_POSITION_DIRECTION = 90;
    // pickup inputs
    public static double PICKUP_POSITION_Y2 = 115;
    // pickup 2
    public static double PICKUP_POSITION_Y3 = 123;
    // pickup 3
    public static int ARM_PICKUP = -2805;
    public static int ARM_BIN =-1490;
    public static double ARM_POWER = 1.0;
    public static int ARM_START = 0;
    public static int ARM_MIDDLE = -1000;
    public static int LIFT_START = -5;
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
        // drop off position (above)
        Pose2d pickupPose = new Pose2d(PICKUP_POSITION_X,PICKUP_POSITION_Y,Math.toRadians((PICKUP_POSITION_HEADING)));
        // pickup position 1 (start)
        Pose2d pickupPose2 = new Pose2d(PICKUP_POSITION_X,PICKUP_POSITION_Y2,Math.toRadians((PICKUP_POSITION_HEADING)));
        // pickup position 2 (1 + forward a little)
        Pose2d pushPose = new Pose2d(PUSH_POSITION_END_X,PUSH_POSITION_END_Y,Math.toRadians((PUSH_POSITION_HEADING)));
        // push first sample away
        Pose2d pickupPose3 = new Pose2d(PICKUP_POSITION_X,PICKUP_POSITION_Y3,Math.toRadians((PICKUP_POSITION_HEADING)));
        // pickup position 3 (1 + a lot)
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        waitForStart();
        telemetry.addLine("Starting");
        telemetry.update();
        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                new MotorActionTargetPosition(armTurn,ARM_BIN, ARM_POWER, MAX_ARM_ERROR),
                                new MotorActionTargetPosition(lift, -1700, LIFT_POWER, MAX_LIFT_ERROR),
                                drive.actionBuilder(beginPose)
                                        .setTangent(0)
                                        .splineToLinearHeading(depositPose, Math.toRadians(DEPOSIT_POSITION_DIRECTION))
                                        .waitSeconds(0.5)
                                        .setTangent(Math.toRadians(-90))
                                        .splineToLinearHeading(pushPose, Math.toRadians(PUSH_POSITION_DIRECTION))
                                        .build(),
                               new SequentialAction(new SleepAction(0.5), new CRServoAction(intake, 0.35))

                        ),
                        //first sample (above)

                        new ParallelAction(
                                new MotorAction(armTurn, -2510, 0.5),
                                new MotorAction(lift, LIFT_START, 0.30),
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
                                new SequentialAction(
                                        new SleepAction(0.5),
                                        new ParallelAction(
                                                new MotorAction(armTurn, -2510, 0.5),
                                                new MotorAction(lift, LIFT_START, 0.30)
                                        )
                                ),
                                drive.actionBuilder(depositPose)
                                        .setTangent(Math.toRadians(-30))
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
                        new SleepAction(2),
                        new ParallelAction(
                                new MotorAction(armTurn, ARM_MIDDLE, 0.5),
                                new MotorAction(lift,LIFT_START, 0.5)
                        ),
                        new MotorAction(armTurn, ARM_START, 0.5)
                )               //reset robot
        );

        telemetry.addLine("Finished");
        telemetry.update();
    }
}

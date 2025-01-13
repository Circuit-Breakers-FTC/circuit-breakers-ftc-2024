package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "RoadRunnerExample")
@Config
public final class RoadRunnerExample extends LinearOpMode {
    public class MotorAction implements Action {
        private String motorName;
        private DcMotor motor;
        private double power;
        private int targetPosition;
        private boolean initialized = false;
        MotorAction(String motorName, DcMotor motor, int targetPosition, double power) {
            this.motorName = motorName;
            this.motor = motor;
            this.targetPosition = targetPosition;
            this.power = power;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                initialized = true;
                motor.setTargetPosition(targetPosition);
                motor.setPower(power);
            }
            telemetry.addData(motorName + " position", motor.getCurrentPosition());
            telemetry.addData(motorName + " target position", motor.getTargetPosition());
            telemetry.addData(motorName + " isBusy", motor.isBusy());
            telemetry.update();
            return motor.isBusy();
        }
    }
    public class ServoAction implements Action {
        private String servoName;
        private CRServo servo;
        private double power;
        private boolean initialized = false;

        ServoAction(String servoName, CRServo servo, double power) {
            this.servoName = servoName;
            this.servo = servo;
            this.power = power;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                initialized = true;
                servo.setPower(power);
            }
            telemetry.addData(servoName + " power", servo.getPower());
            telemetry.update();
            return false;
        }
    }
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;


    public static double DEPOSIT_POSITION_X = 20;
    public static double DEPOSIT_POSITION_Y = 5*24+6;
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
                            new MotorAction("lift", lift, -1700, 1),
                            new MotorAction("armTurn", armTurn, -1490, 0.5),
                            drive.actionBuilder(beginPose)
                                    .setTangent(0)
                                    .splineToLinearHeading(depositPose, Math.toRadians(DEPOSIT_POSITION_DIRECTION))
                                    .build()
                    ),
                    new ServoAction("intake", intake, 0.25),
                    new SleepAction(2)
                )
        );
        telemetry.addLine("Finished");
        telemetry.update();
    }
}

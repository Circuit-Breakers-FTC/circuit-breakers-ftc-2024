package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Vector2d;



@Autonomous(name = "RoadrunnerAuto")
public class RoadrunnerAutohighbin extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;

    private ElapsedTime runtime = new ElapsedTime();

    public class LiftUp implements Action {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        // actions are formatted via telemetry packets as below
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // powers on motor, if it is not on
            if (!initialized) {
                lift.setPower(-0.8);
                initialized = true;
            }

            // checks lift's current position
            double pos = lift.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < -1850) {
                // true causes the action to rerun
                return true;
            } else {
                // false stops action rerun
                lift.setPower(0);
                return false;
            }
        }   // overall, the action powers the lift until it surpasses
    }
    public Action liftUp () {
        return new LiftUp();
    }
    public class LiftDown implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                lift.setPower(0.8);
                initialized = true;
            }

            double pos = lift.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 0) {
                return true;
            } else {
                lift.setPower(0);
                return false;
            }
        }
    }
    public Action liftDown () {
        return new LiftDown();
    }
    public class ArmUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                armTurn.setPower(-0.8);
                initialized = true;
            }

            double pos = armTurn.getCurrentPosition();
            packet.put("armPos", pos);
            if (pos > -1125) {
                return true;
            } else {
                armTurn.setPower(0);
                return false;
            }
        }
    }
    public Action armUp() {
        return new ArmUp();
    }
    public class ArmVertUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                armTurn.setPower(-0.8);
                initialized = true;
            }

            double pos = armTurn.getCurrentPosition();
            packet.put("armPos", pos);
            if (pos >-890) {
                return true;
            } else {
                armTurn.setPower(0);
                return false;
            }
        }
    }

    public Action  armVertUp() {
        return new ArmVertUp();
    }
    public class ArmVertDown implements Action {
        private boolean initialized = false;

        @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        if (!initialized) {
            armTurn.setPower(0.8);
            initialized = true;
        }

        double pos = armTurn.getCurrentPosition();
        packet.put("armPos", pos);
        if (pos <-890) {
            return true;
        } else {
            armTurn.setPower(0);
            return false;
        }
    }
}

public Action armVertDown () {
    return new ArmVertDown();
}
    public class ArmBack implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                armTurn.setPower(0.8);
                initialized = true;
            }

            double pos = armTurn.getCurrentPosition();
            packet.put("armPos", pos);
            if (pos > 0) {
                return true;
            } else {
                armTurn.setPower(0);
                return false;
            }
        }
    }
    public Action armBack() {
        return new ArmBack();
    }

    public class Outtake implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.setPower(0.5);
            return false;
        }
    }
    public Action outtake () {
        return new Outtake();
    }
    public class Intake implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.setPower(-1.0);
            return false;
        }
    }
    public Action intake () {
        return new Intake();
    }
    public class StopIntake implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.setPower(0);
            return false;
        }
    }
    public Action stopIntake () {
        return new StopIntake();
    }
    private Action strafe() {
        Pose2d beginPose = new Pose2d(108, 144, Math.toRadians(45));
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
            return
                    drive.actionBuilder(beginPose)
//                            .lineToY(-22)
//                            .lineToX(4)
                            .strafeTo(new Vector2d(112, 122))
                            .setTangent(Math.toRadians(45))
                            .build()
        ;

    }





    @Override
    public void runOpMode () {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        lift = hardwareMap.get(DcMotor.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armTurn = hardwareMap.get(DcMotor.class, "armTurn");
        armTurn.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armTurn.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armTurn.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake = hardwareMap.get(CRServo.class, "intake");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry.addData("position", lift.getCurrentPosition());
        telemetry.update();
        waitForStart();
        runtime.reset();


        Actions.runBlocking(
                new SequentialAction(
                        strafe(),
                        armVertUp(),
                        liftUp(),
                        armUp(),
                        outtake(),
                        new SleepAction(2),
                        stopIntake(),
                        armVertDown(),
                        liftDown(),
                        armBack()
                )
        );
    }
}




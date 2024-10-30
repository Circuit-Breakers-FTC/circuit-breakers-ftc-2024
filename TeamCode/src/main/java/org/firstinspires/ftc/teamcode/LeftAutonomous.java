package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name = "RightAutonomous")
public class LeftAutonomous extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        runtime.reset();
        driveLeft();
        extendArm();
        sleep(1000);
        jackson();
        stopMotors();
    }

   // private void driveLeft() {
       // frontRight.setPower(-0.5);
     //   frontLeft.setPower(0.5);
       // backLeft.setPower(0.5);
     //   backRight.setPower(-0.5);
       // sleep(1000);
    //just removed for now when whole goal is score preloaded sample and level 1 ascent
    }
    private void extendArm(){
        lift.setTargetPosition(-14000);
        armTurn.setTargetPosition(-1125);

    }


    private void jackson(){

        intake.setPower(-0.5);

        sleep(1000);
    }


    private void stopMotors() {
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backLeft.setPower(0);
    }


}
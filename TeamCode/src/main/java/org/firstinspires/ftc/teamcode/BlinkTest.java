package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp

public class BlinkTest extends LinearOpMode {
    RevBlinkinLedDriver blinkinLedDriver;
    RevBlinkinLedDriver.BlinkinPattern pattern;

    @Override
    public void runOpMode() {
        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        waitForStart();


        while (opModeIsActive()) {
            if(gamepad1.x){
                pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE;
                blinkinLedDriver.setPattern(pattern);
            }

            if(gamepad1.y){
                pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
                blinkinLedDriver.setPattern(pattern);
            }

            if (gamepad1.b){
                pattern = RevBlinkinLedDriver.BlinkinPattern.RED;
                blinkinLedDriver.setPattern(pattern);
            }

            if (gamepad1.a){
                pattern = RevBlinkinLedDriver.BlinkinPattern.VIOLET;
                blinkinLedDriver.setPattern(pattern);
            }



        }
    }
}
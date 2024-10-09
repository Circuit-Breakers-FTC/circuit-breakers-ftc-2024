package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public abstract class HelloWorld extends OpMode {
    @Override
    public void init() {


        String myName = "Gabi Stein";
        telemetry.addData("Hello", myName);


    }


}


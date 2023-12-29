package xyz.linyh.maker.cil.pattern;

public class Main {
    public static void main(String[] args) {

//        Device tv = new Device("电视1");
//        tv.turnOff();


//        turnOffCommand.execute();

//        通过遥控控制，然后执行命令
        Device tv2 = new Device("电视2");

        TurnOffCommand turnOffCommand = new TurnOffCommand(tv2);
//        统一发送命令，可以用来存储历史记录等等信息
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(turnOffCommand);
        remoteControl.execute();

        TurnOnCommand turnOnCommand = new TurnOnCommand(tv2);
        remoteControl.setCommand(turnOnCommand);
        remoteControl.execute();

        remoteControl.getNum();



    }
}

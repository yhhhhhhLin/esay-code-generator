package xyz.linyh.cil.pattern;

/**
 * 统一调度
 * @author lin
 */
public class RemoteControl {

    private Command command;

    private Integer num = 0;

    public void setCommand(Command command){
        this.command = command;
    }

    public void execute(){
        command.execute();
        num++;
    }

    public void getNum(){
        System.out.println("执行了"+num+"次");
    }
}

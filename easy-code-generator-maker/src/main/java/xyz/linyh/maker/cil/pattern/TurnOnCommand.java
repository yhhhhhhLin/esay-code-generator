package xyz.linyh.maker.cil.pattern;

/**
 * 打开指令
 *
 * @author lin
 */
public class TurnOnCommand implements Command {


    /**
     * 这个命令绑定的设备
     */
    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();

    }
}

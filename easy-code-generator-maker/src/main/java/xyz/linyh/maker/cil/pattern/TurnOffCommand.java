package xyz.linyh.maker.cil.pattern;

/**
 * 关闭命令
 *
 * @author lin
 */
public class TurnOffCommand implements Command {

    /**
     * 这个命令绑定的设备
     */
    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }

}

package cn.ksmcbrigade.fn.modules;

import cn.ksmcbrigade.vmr.module.Config;
import cn.ksmcbrigade.vmr.module.Module;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;

public class Timer extends Module {
    public Timer() throws IOException {
        super("hack.name.tr");
        JsonObject object = new JsonObject();
        object.addProperty("timer",2.500F);
        setConfig(new Config(new File("Timer"),object));
    }
}

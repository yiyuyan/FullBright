package cn.ksmcbrigade.fn.modules;

import cn.ksmcbrigade.fn.Option;
import cn.ksmcbrigade.vmr.module.Config;
import cn.ksmcbrigade.vmr.module.Module;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class FullNightModule extends Module {

    public double gamma = 0.0D;

    public FullNightModule() throws IOException {
        super("hack.name.fn",false, KeyEvent.VK_C,new Config(new File("FullNight"),new JsonObject()));
        JsonObject object = new JsonObject();
        object.addProperty("gamma",3000.0D);
        setConfig(new Config(new File("FullNight"),object));
    }

    @Override
    public void enabled(Minecraft MC) {
        this.gamma = MC.options.gamma().get();
    }

    @Override
    public void playerTick(Minecraft MC, @Nullable Player player) {
        JsonElement element = getConfig().get("gamma");
        Option<Double> option = Option.get(MC.options.gamma());
        option.set(element!=null?element.getAsDouble():3000.0D);
    }

    @Override
    public void disabled(Minecraft MC) {
        Option<Double> option = Option.get(MC.options.gamma());
        option.set(this.gamma);
    }
}

package cn.ksmcbrigade.fn;

import cn.ksmcbrigade.fn.modules.*;
import cn.ksmcbrigade.vmr.command.Command;
import cn.ksmcbrigade.vmr.module.Module;
import cn.ksmcbrigade.vmr.uitls.CommandUtils;
import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import com.google.gson.JsonPrimitive;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FullNight.MODID)
public class FullNight {

    public static final String MODID = "fn";

    public FullNight() throws IOException {
        MinecraftForge.EVENT_BUS.register(this);

        ModuleUtils.add(new FullNightModule());
        ModuleUtils.add(new NoFall());
        ModuleUtils.add(new CreativeFlight());
        ModuleUtils.add(new Timer());
        ModuleUtils.add(new Killaura());
        ModuleUtils.add(new AirJump());

        CommandUtils.add(new Command("gamma",1){
            @Override
            public void onCommand(Minecraft MC, Player player, String[] args) {
                try {
                    Module module = ModuleUtils.get("hack.name.fn");
                    if (module != null) {
                        module.getConfig().set("gamma",new JsonPrimitive(Double.parseDouble(args[0])));
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    if(player!=null){
                        player.sendSystemMessage(Component.nullToEmpty("Can't execute the command: "+e.getMessage()));
                    }
                }
            }
        });

        CommandUtils.add(new Command("timer",1){
            @Override
            public void onCommand(Minecraft MC, Player player, String[] args) {
                try {
                    Module module = ModuleUtils.get("hack.name.tr");
                    if (module != null) {
                        module.getConfig().set("timer",new JsonPrimitive(Float.parseFloat(args[0])));
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    if(player!=null){
                        player.sendSystemMessage(Component.nullToEmpty("Can't execute the command: "+e.getMessage()));
                    }
                }
            }
        });
    }
}

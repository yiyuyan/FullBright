package cn.ksmcbrigade.fn.modules;

import cn.ksmcbrigade.vmr.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class AirJump extends Module {
    public AirJump() {
        super("hack.name.aj");
    }

    @Override
    public void playerTick(Minecraft MC, @Nullable Player player) {
        if(MC.options.keyJump.isDown() && player!=null){
           player.jumpFromGround();
        }
    }
}

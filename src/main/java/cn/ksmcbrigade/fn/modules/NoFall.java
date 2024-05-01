package cn.ksmcbrigade.fn.modules;

import cn.ksmcbrigade.vmr.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class NoFall extends Module {
    public NoFall() {
        super("hack.name.nf");
    }

    @Override
    public void playerTick(Minecraft MC, @Nullable Player player) {
        if(player!=null && player.fallDistance>3.0F){
            player.level().sendPacketToServer(new ServerboundMovePlayerPacket.StatusOnly(true));
        }
    }
}

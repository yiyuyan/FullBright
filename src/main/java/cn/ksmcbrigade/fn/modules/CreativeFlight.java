package cn.ksmcbrigade.fn.modules;

import cn.ksmcbrigade.vmr.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundPlayerAbilitiesPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import org.jetbrains.annotations.Nullable;

import java.awt.event.KeyEvent;

public class CreativeFlight extends Module {

    public CreativeFlight() {
        super("hack.name.cf",false, KeyEvent.VK_G);
    }

    @Override
    public void enabled(Minecraft MC) {
        if(MC.player!=null){
            MC.player.getAbilities().mayfly = true;
            MC.player.level().sendPacketToServer(new ServerboundPlayerAbilitiesPacket(MC.player.getAbilities()));
        }
    }

    @Override
    public void playerTick(Minecraft MC, @Nullable Player player) throws Exception {
        if(MC.player!=null){
            MC.player.getAbilities().mayfly = true;
        }
    }

    @Override
    public void disabled(Minecraft MC) throws Exception {
        if(MC.player!=null && MC.gameMode!=null && (MC.gameMode.getPlayerMode().isSurvival() || MC.gameMode.getPlayerMode().equals(GameType.ADVENTURE))){
            MC.player.getAbilities().mayfly = false;
            MC.player.level().sendPacketToServer(new ServerboundPlayerAbilitiesPacket(MC.player.getAbilities()));
        }
    }
}

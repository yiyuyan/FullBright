package cn.ksmcbrigade.fn.modules;

import cn.ksmcbrigade.vmr.module.Config;
import cn.ksmcbrigade.vmr.module.Module;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Killaura extends Module {

    public int tick = 0;
    public Random random = new Random();

    public Killaura() throws IOException {
        super("hack.name.ka",false, KeyEvent.VK_R);
        JsonObject object = new JsonObject();
        object.addProperty("disa",6);
        object.addProperty("sleep",10);
        setConfig(new Config(new File("Killaura"),object));
    }

    @Override
    public void playerTick(Minecraft MC, @Nullable Player player) {
        if(tick!=0){
            tick--;
            return;
        }
        if(player!=null && MC.gameMode!=null){
            Vec3 pos = player.getPosition(0);
            JsonElement disa = getConfig().get("disa");
            player.level().getEntitiesOfClass(Entity.class,new AABB(pos,pos).inflate(disa!=null?disa.getAsInt():6)).stream()
                    .filter(e -> e!=player)
                    .filter(e -> e!=MC.player)
                    .filter(e -> e.getId()!=player.getId())
                    .filter(e -> e.getId()!=MC.player.getId())
                    .filter(Entity::isAttackable)
                    .toList()
                    .forEach(e -> MC.gameMode.attack(player,e));
        }
        JsonElement disa = getConfig().get("sleep");
        tick = disa==null?10:disa.getAsInt();
        if(random.nextBoolean()){
            tick+=random.nextInt(0,4);
        }
        else{
            tick-=random.nextInt(0,4);
        }
    }
}

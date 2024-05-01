package cn.ksmcbrigade.fn.mixin;

import cn.ksmcbrigade.vmr.module.Config;
import cn.ksmcbrigade.vmr.module.Module;
import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import com.google.gson.JsonElement;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "getTickTargetMillis",at = @At("RETURN"), cancellable = true)
    public void getTimer(float p_311597_, CallbackInfoReturnable<Float> cir){
        if(ModuleUtils.enabled("hack.name.tr")){
            Module module = ModuleUtils.get("hack.name.tr");
            if(module!=null){
                JsonElement config = module.getConfig().get("timer");
                if(config!=null){
                    cir.setReturnValue(cir.getReturnValue()/config.getAsFloat());
                }
            }
        }
    }
}

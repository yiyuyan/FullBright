package cn.ksmcbrigade.fn.mixin;

import cn.ksmcbrigade.fn.Option;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;
import java.util.function.Consumer;

@Mixin(OptionInstance.class)
public abstract class OptionMixin<T> implements Option<T> {
    @Shadow
    T value;

    @Shadow @Final private Consumer<T> onValueUpdate;

    @Mutable
    @Shadow @Final private T initialValue;

    @Override
    public void set(T p_231515_){
        if (!Minecraft.getInstance().isRunning()) {
            this.value = p_231515_;
        }
        if (!Objects.equals(this.value, p_231515_)) {
            this.value = p_231515_;
            this.onValueUpdate.accept(this.value);
        }
    }
}

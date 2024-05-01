package cn.ksmcbrigade.fn;

import net.minecraft.client.OptionInstance;

public interface Option<T> {
    public void set(T newValue);

    @SuppressWarnings("unchecked")
    public static <T> Option<T> get(OptionInstance<T> option)
    {
        return (Option<T>)(Object)option;
    }
}

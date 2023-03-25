package me.bush7root.anarchyhelper.func.addons;

import me.bush7root.anarchyhelper.conf.widgetConfManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class Widget {
    public String name;
    public String info;
    public boolean toggled;
    public Minecraft mc = Minecraft.getInstance();

    public Widget(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
        new widgetConfManager().save();
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void toggle() {
        toggled = !toggled;

        if (toggled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }
}
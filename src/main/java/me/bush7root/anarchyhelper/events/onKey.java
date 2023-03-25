package me.bush7root.anarchyhelper.events;

import me.bush7root.anarchyhelper.AnarchyHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class onKey {
    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent e) {
        if (e.getAction() == 1) {
            if (e.getKey() == 344 && Minecraft.getInstance().screen == null) {
                  Minecraft.getInstance().setScreen(AnarchyHelper.anarchyMainGui);
            }
        }
    }
}

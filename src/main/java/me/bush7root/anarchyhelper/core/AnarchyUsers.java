package me.bush7root.anarchyhelper.core;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;

public class AnarchyUsers {
    public static ArrayList<String> premiumUsers = new ArrayList<>();
    public static ArrayList<String> umpalumpas = new ArrayList<>();
    @SubscribeEvent
    public void onRender(RenderNameplateEvent e) {
        if (premiumUsers.contains(e.getEntity().getName().getString())) {
            e.setContent(new StringTextComponent("§c✔ " + e.getEntity().getName().getString()));
        }
    }

    @SubscribeEvent
    public void forUmpalumpas(RenderPlayerEvent e) {
        if (umpalumpas.contains(e.getEntity().getName().getString())) {
            e.setCanceled(true);
            try {
                if (e.getEntity() != Minecraft.getInstance().player) {
                    e.getEntity().remove();
                }
            } catch (Exception ignored) {}
        }
    }
}

package me.bush7root.anarchyhelper.func.addons;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AutoSpawn extends Widget {
    public AutoSpawn() {
        super("AutoSpawn", "Авто-ТП к спавну при падении");
    }

    @SubscribeEvent
    public void onUpdate(RenderWorldLastEvent e) {
        if (mc.player != null) {
            if (mc.player.fallDistance >= 15) {
                mc.player.chat("/spawn");
            }
        }
    }
}

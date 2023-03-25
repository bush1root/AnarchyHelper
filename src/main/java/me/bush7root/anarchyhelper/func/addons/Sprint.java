package me.bush7root.anarchyhelper.func.addons;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Sprint extends Widget {
    public Sprint() {
        super("Sprint", "Авто-ускорение");
    }

    @SubscribeEvent
    public void onChat(TickEvent.PlayerTickEvent e) {
        if (mc.player != null) {
            try {
                if (!mc.player.horizontalCollision && mc.player.input.hasForwardImpulse()) {
                    mc.player.setSprinting(true);
                }
            } catch (Exception ignored) {}
        }
    }
}

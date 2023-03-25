package me.bush7root.anarchyhelper.func.addons;

import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Particles extends Widget {
    public Particles() {
        super("Particles", "Мне нужно больше партиклов!!!");
    }

    @SubscribeEvent
    public void onAttack(AttackEntityEvent e) {
        for (int i = 1; i <= 12; i++) {
            mc.player.magicCrit(e.getTarget());
        }
    }
}

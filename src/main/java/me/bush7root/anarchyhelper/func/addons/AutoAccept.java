package me.bush7root.anarchyhelper.func.addons;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AutoAccept extends Widget {
    public AutoAccept() {
        super("AutoAccept", "Принимайте ТП Автоматически!");
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e) {
        if (e.getMessage().getString().contains("/tpaccept") &&
                (e.getMessage().getString().toLowerCase().contains("принятия") ||
                        e.getMessage().getString().toLowerCase().contains("принять") ||
                        e.getMessage().getString().toLowerCase().contains("accept")) ) {
           mc.player.chat("/tpaccept");
        }
    }
}

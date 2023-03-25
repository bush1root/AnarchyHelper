package me.bush7root.anarchyhelper.func;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;

public class ConsoleManager {
    public static ArrayList<String> chatMessages = new ArrayList<>();

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e) {
        if (chatMessages.size() >= 100) {
            chatMessages.subList(60, 101).clear();
        }
        chatMessages.add(0, e.getMessage().getString());
    }
}

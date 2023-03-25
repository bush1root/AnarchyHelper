package me.bush7root.anarchyhelper.func.addons;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ChatCalc extends Widget {
    public ChatCalc() {
        super("ChatCalc", "Калькулятор в вашем чате");
    }

    @SubscribeEvent
    public void chatCalc(ClientChatEvent e) {
        String[] calcArgs = {"*", "+", "-", "/"};

        try {
            for (String msg : e.getMessage().split(" ")) {
                for (String s : calcArgs) {
                    if (msg.contains(s)) {
                        if (s == "*") {
                            e.setMessage(e.getMessage().replace(msg,
                                    Integer.toString(Integer.parseInt(msg.split("\\*")[0])*Integer.parseInt(msg.split("\\*")[1]))));
                        } if (s == "+") {
                            e.setMessage(e.getMessage().replace(msg,
                                    Integer.toString(Integer.parseInt(msg.split("\\+")[0])+Integer.parseInt(msg.split("\\+")[1]))));
                        } if (s == "-") {
                            e.setMessage(e.getMessage().replace(msg,
                                    Integer.toString(Integer.parseInt(msg.split("-")[0])-Integer.parseInt(msg.split("-")[1]))));
                        } if (s == "/") {
                            e.setMessage(e.getMessage().replace(msg,
                                    Integer.toString(Integer.parseInt(msg.split("/")[0])/Integer.parseInt(msg.split("/")[1]))));
                        }
                    }
                }
            }
        } catch (Exception ignored) {}
    }
}

package me.bush7root.anarchyhelper.listeners;

import me.bush7root.anarchyhelper.gui.anarchyMainGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class draggableListener {
    private static boolean draggable;
    private static int mouseX, mouseY;

    public static int getMouseY() {
        return mouseY;
    }

    public static void setMouseY(int mouseY) {
        draggableListener.mouseY = mouseY;
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static void setMouseX(int mouseX) {
        draggableListener.mouseX = mouseX;
    }

    public static boolean isDraggable() {
        return draggable;
    }

    public static void setDraggable(boolean draggable) {
        draggableListener.draggable = draggable;
    }

    @SubscribeEvent
    public void check(RenderGameOverlayEvent e) {
        Minecraft mc = Minecraft.getInstance();

        setDraggable(mc.screen instanceof anarchyMainGui);
    }
}

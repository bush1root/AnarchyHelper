package me.bush7root.anarchyhelper.func.addons;

import me.bush7root.anarchyhelper.gui.helpers.HoverHelper;
import me.bush7root.anarchyhelper.helpers.ColorHelper;
import me.bush7root.anarchyhelper.helpers.RenderHelper;
import me.bush7root.anarchyhelper.listeners.draggableListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NearWidget extends Widget {
    public static int x, y;
    public NearWidget() {
        super("NearWidget", "Виджет, который показывает есть ли рядом игроки");

        x = 10;
        y = 10;
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent e) {
        int i = 0;

        for (PlayerEntity playerEntity : mc.level.players()) {
            if (playerEntity != mc.player) {
                i++;
            }
        }
        String text = "§fPlayers near: §c" + i;


        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            RenderHelper.drawSmoothRect(e.getMatrixStack(), x, y, x + mc.font.width(text) + 10, y + 20, ColorHelper.mainColor.getRGB());
            mc.font.draw(e.getMatrixStack(), text, x + 2, y + 4, -1);
        }

        if (draggableListener.isDraggable()) {
            if (HoverHelper.hovered(draggableListener.getMouseX(), draggableListener.getMouseY(), x - 10, y - 10, x + mc.font.width(text) + 10 + 10, y + 20 + 10)) {
                x = draggableListener.getMouseX();
                y = draggableListener.getMouseY();
            }
        }
    }
}

package me.bush7root.anarchyhelper.func.addons;

import me.bush7root.anarchyhelper.gui.helpers.HoverHelper;
import me.bush7root.anarchyhelper.helpers.ColorHelper;
import me.bush7root.anarchyhelper.helpers.RenderHelper;
import me.bush7root.anarchyhelper.listeners.draggableListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FPSIndicator extends Widget {
    public static int x, y;
    public FPSIndicator() {
        super("FPSWidget", "Узнайте кол-во вашего фпс");

        x = 10;
        y = 40;
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent e) {
        int i = 0;

        for (PlayerEntity playerEntity : mc.level.players()) {
            if (playerEntity != mc.player) {
                i++;
            }
        }
        String text = "§fFPS: §c" + mc.fpsString.split("fps")[0];


        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            RenderHelper.drawSmoothRect(e.getMatrixStack(), x, y, x + 100, y + 20, ColorHelper.mainColor.getRGB());
            mc.font.draw(e.getMatrixStack(), text, x + 2, y + 4, -1);
            RenderHelper.drawSmoothRect(e.getMatrixStack(), x + 3, y + 16, x + 100 - 3, y + 20 - 2, ColorHelper.elementColor.getRGB());

            try {
                RenderHelper.drawSmoothRect(e.getMatrixStack(), x + 2, y + 17,
                        x + ((Integer.parseInt(mc.fpsString.split(" fps")[0]) / 10) >= 1000 ?
                                100 : (Integer.parseInt(mc.fpsString.split(" fps")[0]) / 10) - 4), y + 20 - 3, ColorHelper.mainThemeColor.getRGB());
            } catch (Exception ex) {}
        }

        if (draggableListener.isDraggable()) {
            if (HoverHelper.hovered(draggableListener.getMouseX(), draggableListener.getMouseY(), x - 10, y - 10, x + 100 + 10, y + 20 + 10)) {
                x = draggableListener.getMouseX();
                y = draggableListener.getMouseY();
            }
        }
    }
}

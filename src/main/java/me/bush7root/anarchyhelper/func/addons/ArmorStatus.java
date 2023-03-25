package me.bush7root.anarchyhelper.func.addons;

import me.bush7root.anarchyhelper.gui.helpers.HoverHelper;
import me.bush7root.anarchyhelper.helpers.ColorHelper;
import me.bush7root.anarchyhelper.helpers.RenderHelper;
import me.bush7root.anarchyhelper.listeners.draggableListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class ArmorStatus extends Widget {
    public static int x,y;
    public ArmorStatus() {
        super("ArmorStatus", "Статус вашей брони");
        x = 12;
        y = 128;
    }

    @SubscribeEvent
    public void onUpdate(RenderGameOverlayEvent e) {
        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            RenderHelper.drawSmoothRect(e.getMatrixStack(), x - 3, y - (13*5) + 15, x + 80 - 3, y + (13*5) + 3 - 30, ColorHelper.mainColor.getRGB());

            for (int i = 0; i <= 4; i++) {
                try {
                    if (!Objects.equals(mc.player.inventory.getArmor(i).toString().split(" ")[1], "air")) {
                        Minecraft.getInstance().getTextureManager().bind(new ResourceLocation("textures/item/" + mc.player.inventory.getArmor(i).toString().split(" ")[1] + ".png"));
                        AbstractGui.blit(e.getMatrixStack(), x, y - (i * 15), 0, 0, 15, 15, 15, 15);
                        mc.font.draw(e.getMatrixStack(), (mc.player.inventory.getArmor(i).getMaxDamage() - mc.player.inventory.getArmor(i).getDamageValue())
                                + "/" + mc.player.inventory.getArmor(i).getMaxDamage(), x + 17, y - (i * 13), ColorHelper.mainThemeColor.getRGB());
                    }
                } catch (Exception ignored) {}
            }

            if (mc.player.getMainHandItem().getItem().getItemCategory() == ItemGroup.TAB_COMBAT) {
                Minecraft.getInstance().getTextureManager().bind(new ResourceLocation("textures/item/" + mc.player.getMainHandItem().getItem().toString() + ".png"));
                AbstractGui.blit(e.getMatrixStack(), x, y + (15), 0, 0, 15, 15, 15, 15);

                mc.font.draw(e.getMatrixStack(), (mc.player.getMainHandItem().getMaxDamage() - mc.player.getMainHandItem().getDamageValue())
                        + "/" + mc.player.getMainHandItem().getMaxDamage(), x + 17, y + (13) + 4, ColorHelper.mainThemeColor.getRGB());
            }
        }

        if (draggableListener.isDraggable()) {
            if (HoverHelper.hovered(draggableListener.getMouseX(), draggableListener.getMouseY(), x - 3 - 10, y - (13*5) + 15 - 10, x + 80 - 3 + 10, y + (13*5) + 3 - 30 + 10)) {
                x = draggableListener.getMouseX();
                y = draggableListener.getMouseY();
            }
        }
    }
}

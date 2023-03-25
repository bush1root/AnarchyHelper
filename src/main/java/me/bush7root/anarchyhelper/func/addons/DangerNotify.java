package me.bush7root.anarchyhelper.func.addons;

import me.bush7root.anarchyhelper.gui.helpers.HoverHelper;
import me.bush7root.anarchyhelper.listeners.draggableListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;

public class DangerNotify extends Widget {
    public static int waterX = 100;
    public static int waterY = 20;

    public static int tntX = 125;
    public static int tntY = 20;

    public static int lowX = 150;
    public static int lowY = 20;

    public static int bowX = 175;
    public static int bowY = 20;

    public DangerNotify() {
        super("Notify", "Узнайте, есть ли рядом опасность...");
    }

    @SubscribeEvent
    public void waterNotify(RenderGameOverlayEvent e) {
        int x = waterX;
        int y = waterY;

        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            if ((Minecraft.getInstance().player.isUnderWater() && Minecraft.getInstance().player.getHealth() != Minecraft.getInstance().player.getMaxHealth()) || draggableListener.isDraggable()) {
                Minecraft.getInstance().getTextureManager().bind(new ResourceLocation("water.png"));
                AbstractGui.blit(e.getMatrixStack(), x + 2, y - 2, 0, 0, 20, 20, 20, 20);

                if (draggableListener.isDraggable()) {
                    if (HoverHelper.hovered(draggableListener.getMouseX(), draggableListener.getMouseY(), x + 1 - 10, y - 6 - 10, 20 + x + 10, y + 20 + 10)) {
                        waterX = draggableListener.getMouseX();
                        waterY = draggableListener.getMouseY();
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void tntNotify(RenderGameOverlayEvent e) {
        int x = tntX;
        int y = tntY;

        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            ArrayList<PlayerEntity> playerEntitiesWithBow = new ArrayList<>();

            for (PlayerEntity playerEntity : Minecraft.getInstance().level.players()) {
                if (playerEntity != Minecraft.getInstance().player) {
                    if (playerEntity.getMainHandItem().getItem().getDescriptionId().equals("item.minecraft.tnt")) {
                        playerEntitiesWithBow.add(playerEntity);
                    }
                }
            }

            if (!playerEntitiesWithBow.isEmpty() || draggableListener.isDraggable()) {
                Minecraft.getInstance().getTextureManager().bind(new ResourceLocation("textures/block/tnt_side.png"));
                AbstractGui.blit(e.getMatrixStack(), x + 2, y - 2, 0, 0, 20, 20, 20, 20);

                if (draggableListener.isDraggable()) {
                    if (HoverHelper.hovered(draggableListener.getMouseX(), draggableListener.getMouseY(), x + 1 - 10, y - 6 - 10, 20 + x + 10, y + 20 + 10)) {
                        tntX = draggableListener.getMouseX();
                        tntY = draggableListener.getMouseY();
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void lowNotify(RenderGameOverlayEvent e) {
        int x = lowX;
        int y = lowY;

        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            if (((int) Minecraft.getInstance().player.getHealth() <= (int) Minecraft.getInstance().player.getMaxHealth() - 16) || draggableListener.isDraggable()) {
                Minecraft.getInstance().getTextureManager().bind(new ResourceLocation("low.png"));
                AbstractGui.blit(e.getMatrixStack(), x + 2, y - 2, 0, 0, 20, 20, 20, 20);

                if (draggableListener.isDraggable()) {
                    if (HoverHelper.hovered(draggableListener.getMouseX(), draggableListener.getMouseY(), x + 1 - 10, y - 6 - 10, 20 + x + 10, y + 20 + 10)) {
                        lowX = draggableListener.getMouseX();
                        lowY = draggableListener.getMouseY();
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void bowNotify(RenderGameOverlayEvent e) {
        int x = bowX;
        int y = bowY;

        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            ArrayList<PlayerEntity> playerEntitiesWithBow = new ArrayList<>();

            for (PlayerEntity playerEntity : Minecraft.getInstance().level.players()) {
                if (playerEntity != Minecraft.getInstance().player) {
                    if (playerEntity.getMainHandItem().getItem().getDescriptionId().equals("item.minecraft.bow")) {
                        playerEntitiesWithBow.add(playerEntity);
                    }
                }
            }

            if (!playerEntitiesWithBow.isEmpty() || draggableListener.isDraggable()) {
                Minecraft.getInstance().getTextureManager().bind(new ResourceLocation("textures/item/bow.png"));
                AbstractGui.blit(e.getMatrixStack(), x + 2, y - 2, 0, 0, 20, 20, 20, 20);

                if (draggableListener.isDraggable()) {
                    if (HoverHelper.hovered(draggableListener.getMouseX(), draggableListener.getMouseY(), x + 1 - 10, y - 6 - 10, 20 + x + 10, y + 20 + 10)) {
                        bowX = draggableListener.getMouseX();
                        bowY = draggableListener.getMouseY();
                    }
                }
            }
        }
    }
}

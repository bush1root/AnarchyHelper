package me.bush7root.anarchyhelper.gui;

import me.bush7root.anarchyhelper.AnarchyHelper;
import me.bush7root.anarchyhelper.conf.coreConf;
import me.bush7root.anarchyhelper.core.AnarchyUsers;
import me.bush7root.anarchyhelper.func.ConsoleManager;
import me.bush7root.anarchyhelper.func.addons.Widget;
import me.bush7root.anarchyhelper.gui.components.catButton;
import me.bush7root.anarchyhelper.gui.components.widgetButton;
import me.bush7root.anarchyhelper.gui.helpers.HoverHelper;
import me.bush7root.anarchyhelper.helpers.ColorHelper;
import me.bush7root.anarchyhelper.helpers.RenderHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import me.bush7root.anarchyhelper.listeners.draggableListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.Objects;

public class anarchyMainGui extends Screen {
    public static ArrayList<catButton> buttonsList = new ArrayList<>();
    public static ArrayList<widgetButton> widgetButtonList = new ArrayList<>();
    public int x, dragX;
    public int y, dragY;
    public boolean dragging;
    public anarchyMainGui() {
        super(new StringTextComponent("coded with love by bushr00tix"));

        x = 10;
        y = 10;

        buttonsList.add(new catButton(120,45, 12, "Console"));
        // padding += 45+5 (50)
        buttonsList.add(new catButton(170,45, 12, "Players"));
        buttonsList.add(new catButton(220,45, 12, "Addons"));
        //buttonsList.add(new catButton(270, 30, 12, "Bot"));
        // buttonsList.add(new catButton(270,45, 12, "Server")); like exploit
        buttonsList.add(new catButton(270,45, 12, "Profile"));

        int yy = this.y + 20;
        for (Widget widget : AnarchyHelper.widgets) {
            widgetButtonList.add(new widgetButton(yy, 60, 12, widget));
            yy += 14;
        }
    }

    @Override
    public boolean keyPressed(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
        return super.keyPressed(p_231046_1_, p_231046_2_, p_231046_3_);
    }

    @Override
    public void mouseMoved(double p_212927_1_, double p_212927_3_) {
        super.mouseMoved(p_212927_1_, p_212927_3_);
    }

    @Override
    public boolean mouseDragged(double x, double y, int mouse, double p_231045_6_, double p_231045_8_) {
        if (mouse == 1) {

            draggableListener.setMouseX((int) x);

            draggableListener.setMouseY((int) y);

        }
        return super.mouseDragged(x, y, mouse, p_231045_6_, p_231045_8_);
    }

    @Override
    public boolean mouseReleased(double p_231048_1_, double p_231048_3_, int p_231048_5_) {
        dragging = false;

        return super.mouseReleased(p_231048_1_, p_231048_3_, p_231048_5_);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (HoverHelper.hovered((int) mouseX, (int) mouseY, x, y, x + 430, y + 250)) {
            if (mouseButton == 0) {
                dragX = (int) (mouseX - x);
                dragY = (int) (mouseY - y);
                dragging = true;
            }
        }

        for (catButton categoryButton : buttonsList) {
            categoryButton.mouseClicked((int) mouseX, (int) mouseY, mouseButton);
        } for (widgetButton widgetButton : widgetButtonList) {
            widgetButton.mouseClicked((int) mouseX, (int) mouseY, mouseButton);
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public void render(MatrixStack m, int x, int y, float partialTicks) {
        this.renderBackground(m);

        if (dragging) {
            this.x = x - dragX;
            this.y = y - dragY;
        }

        RenderHelper.drawRoundedRect(m, this.x, this.y, 430, 250, 10, ColorHelper.mainColor); // RenderHelper.drawRoundedRect(m, getMouseX(), getMouseY(), 430, 250, 10, ColorHelper.mainColor);
        Minecraft.getInstance().font.draw(m, "§cAnarchy§fHelper v" + coreConf.version, this.x + 10, this.y + 10, -1);
        Minecraft.getInstance().font.draw(m, "§cAnarchy§fHelper v", this.x + 10, this.y + 10, -1);
        if (coreConf.needUpdate) {
            Minecraft.getInstance().font.draw(m, "§fПожалуйста, скачайте §cобновление§f тут: §c@AnarchyHelper_BOT §f(Telegram)", this.x + 10, this.y + 240, -1);
        }

        for (catButton categoryButton : buttonsList) {
            categoryButton.drawScreen(m, this.x, this.y, partialTicks);
        }

        if (isCategoryToggled("Console")) {
            RenderHelper.drawSmoothRect(m, this.x + 20, this.y + 30, this.x + 410, this.y + 235, ColorHelper.elementColor.getRGB());

            int yy = this.y + 235 - 10;

            for (String msg : ConsoleManager.chatMessages) {
                if (yy > this.y + 25) {
                    String ss = msg;

                    if (ss.length() >= 65) {
                        ss = ss.substring(0, 65) + "...";
                    }
                    Minecraft.getInstance().font.draw(m, ss, this.x + 22, yy, -1);
                    yy -= 10;
                }
            }
        } else if (isCategoryToggled("Players")) {
            int yy = this.y + 25;
            int ff = this.y + 25; // mne len prosto norm kod segodna pisat tak chto poxui

            for (PlayerEntity playerEntity : Minecraft.getInstance().level.players()) {
                if (playerEntity != Minecraft.getInstance().player) {
                    if (yy < this.y + 235 - 10) {
                        RenderHelper.drawSmoothRect(m, this.x + 10, yy, this.x + 135, yy + 40, ColorHelper.elementColor.getRGB());
                        Minecraft.getInstance().font.draw(m, "User: " + playerEntity.getName().getString(), this.x + 12, yy + 5, -1);
                        Minecraft.getInstance().font.draw(m, "HP: " + (int) playerEntity.getHealth(), this.x + 12, yy + 15, -1);
                        Minecraft.getInstance().font.draw(m, "Distance: " + (int) Minecraft.getInstance().player.distanceTo(playerEntity), this.x + 12, yy + 25, -1);
                        yy += 50;
                    } else {
                        if (ff < (this.y + 235 - 10)) {
                            RenderHelper.drawSmoothRect(m, this.x + 10 + 145, ff, this.x + 135 + 145, ff + 40, ColorHelper.elementColor.getRGB());
                            Minecraft.getInstance().font.draw(m, "User: " + playerEntity.getName().getString(), this.x + 12 + 145, ff + 5, -1);
                            Minecraft.getInstance().font.draw(m, "HP: " + (int) playerEntity.getHealth(), this.x + 12 + 145, ff + 15, -1);
                            Minecraft.getInstance().font.draw(m, "Distance: " + (int) Minecraft.getInstance().player.distanceTo(playerEntity), this.x + 12 + 145, ff + 25, -1);
                            ff += 50;
                        }
                    }
                }
            }
        } else if (isCategoryToggled("Addons")) {
            for (widgetButton widget : widgetButtonList) {
                widget.drawScreen(m, this.x + 10, this.y, partialTicks);
            }
        } else if (isCategoryToggled("Profile")) {
            Minecraft.getInstance().font.draw(m, "User: " + Minecraft.getInstance().player.getName().getString(), this.x + 10, this.y + 40, -1);
            Minecraft.getInstance().font.draw(m, "Code: none", this.x + 10, this.y + 50, -1);
            Minecraft.getInstance().font.draw(m, "Premium: " + (AnarchyUsers.premiumUsers.contains(Minecraft.getInstance().player.getName().getString()) ? "✔" : "✖"), this.x + 10, this.y + 60, -1);

            Minecraft.getInstance().font.draw(m, "Author: " + String.join(" ", coreConf.author).replace(" ", ""), this.x + 10, this.y + 90, -1);
        }

        super.render(m, x, y, partialTicks);
    }

    public boolean isCategoryToggled(String categoryName) {
        for (catButton categoryButton : buttonsList) {
            if (Objects.equals(categoryButton.text, categoryName)) {
                return categoryButton.isToggled;
            }
        }
        return false;
    }
}

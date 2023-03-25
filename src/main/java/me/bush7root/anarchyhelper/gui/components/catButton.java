package me.bush7root.anarchyhelper.gui.components;

import me.bush7root.anarchyhelper.gui.anarchyMainGui;
import me.bush7root.anarchyhelper.gui.helpers.HoverHelper;
import me.bush7root.anarchyhelper.helpers.ColorHelper;
import me.bush7root.anarchyhelper.helpers.RenderHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;

public class catButton {
    public Minecraft mc = Minecraft.getInstance();

    public int x, y, padding, width, height;
    public String text;
    public boolean isToggled;

    public catButton(int padding, int width, int height, String text) {
        this.padding = padding;
        this.width = width;
        this.height = height;
        this.text = text;

        this.isToggled = false;
    }

    public void drawScreen(MatrixStack m, int x, int y, float partialTicks) {
        this.x = x + padding;
        this.y = y + 7;

        RenderHelper.drawSmoothRect(m, this.x, this.y, this.x + width, this.y + height, isToggled ?
                ColorHelper.mainThemeColor.getRGB() : ColorHelper.elementColor.getRGB());
        mc.font.draw(m, text, this.x + 2, this.y + 3, -1);
    }

    protected void keyTyped(int keyCode) {}

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (HoverHelper.hovered(mouseX, mouseY, x, y, x + width, y + height)) {
            if (mouseButton == 0) {
                for (catButton categoryButton : anarchyMainGui.buttonsList) {
                    if (categoryButton != this) {
                        categoryButton.isToggled = false;
                    }
                }
                isToggled = true;
            }
        }
    }
}

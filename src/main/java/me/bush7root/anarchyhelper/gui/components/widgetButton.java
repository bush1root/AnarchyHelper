package me.bush7root.anarchyhelper.gui.components;

import me.bush7root.anarchyhelper.func.addons.Widget;
import me.bush7root.anarchyhelper.gui.helpers.HoverHelper;
import me.bush7root.anarchyhelper.helpers.ColorHelper;
import me.bush7root.anarchyhelper.helpers.RenderHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;

public class widgetButton {
    public Minecraft mc = Minecraft.getInstance();

    public int x, y, padding, width, height;
    public Widget widget;

    public widgetButton(int padding, int width, int height, Widget widget) {
        this.padding = padding;
        this.width = width;
        this.height = height;
        this.widget = widget;
    }

    public void drawScreen(MatrixStack m, int x, int y, float partialTicks) {
        this.x = x;
        this.y = y + 7 + padding;

        RenderHelper.drawSmoothRect(m, this.x, this.y, this.x + width, this.y + height,
                widget.isToggled() ? ColorHelper.mainThemeColor.getRGB() : ColorHelper.elementColor.getRGB());
        RenderHelper.drawSmoothRect(m, this.x + 1, this.y + 1, this.x + width - 1, this.y + height - 1, ColorHelper.elementColor.getRGB());

        mc.font.draw(m, widget.name, this.x + 2, this.y + 3, -1);
    }

    protected void keyTyped(int keyCode) {}

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (HoverHelper.hovered(mouseX, mouseY, x, y, x + width, y + height)) {
            if (mouseButton == 0) {
                widget.toggle();
            }
        }
    }
}

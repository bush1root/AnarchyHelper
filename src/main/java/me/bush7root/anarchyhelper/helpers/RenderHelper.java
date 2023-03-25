package me.bush7root.anarchyhelper.helpers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.IngameGui;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class RenderHelper {
    public static void drawSmoothRect(MatrixStack m, float left, float top, float right, float bottom, int color) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        IngameGui.fill(m, (int) left, (int) top, (int) right, (int) bottom, color);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        IngameGui.fill(m, (int) (left * 2 - 1), (int) (top * 2), (int) (left * 2), (int) (bottom * 2 - 1), color);
        IngameGui.fill(m, (int) (left * 2), (int) (top * 2 - 1), (int) (right * 2), (int) (top * 2), color);
        IngameGui.fill(m, (int) (right * 2), (int) (top * 2), (int) (right * 2 + 1), (int) (bottom * 2 - 1), color);
        IngameGui.fill(m, (int) (left * 2), (int) (bottom * 2 - 1), (int) (right * 2), (int) (bottom * 2), color);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glScalef(2F, 2F, 2F);
    }

    public static void drawRoundedRect(MatrixStack m, double x, double y, int width, int height, int radius, Color color) {

        int x2 = (int) (x + ((radius / 2F) + 0.5F));
        int y2 = (int) (y + ((radius / 2F) + 0.5F));
        int width2 = (int) (width - ((radius / 2F) + 0.5F));
        int height2 = (int) (height - ((radius / 2F) + 0.5F));

        IngameGui.fill(m, x2, y2, x2 + width2, y2 + height2, color.getRGB());

        polygon(x - 1, y - 1, radius * 2, 360, true, color);
        polygon(x + width2 - radius + 1.2 - 1, y - 1, radius * 2, 360, true, color);

        polygon(x + width2 - radius + 1.2 - 1, y + height2 - radius + 1 - 1, radius * 2, 360, true, color);
        polygon(x - 1, y + height2 - radius + 1 - 1, radius * 2, 360, true, color);

        GL11.glColor4f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
        IngameGui.fill(m, (int) (x2 - radius / 2 - 0.5F), y2 + radius / 2, x2 + width2, y2 + height2 - radius / 2, color.getRGB());
        IngameGui.fill(m,x2, y2 + radius / 2, (int) (x2 + width2 + radius / 2 + 0.5f), y2 + height2 - radius / 2, color.getRGB());
        IngameGui.fill(m,x2 + radius / 2, (int) (y2 - radius / 2 - 0.5F), x2 + width2 - radius / 2, (int) (y + height2 - radius / 2), color.getRGB());
        IngameGui.fill(m,x2 + radius / 2, y2, x2 + width2 - radius / 2, (int) (y2 + height2 + radius / 2 + 0.5f), color.getRGB());
    }

    public static void polygon(double x, double y, double sideLength, double amountOfSides, boolean filled, Color color) {
        sideLength /= 2;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GlStateManager._disableAlphaTest();
        GL11.glColor4f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
        if (!filled) {
            GL11.glLineWidth(1);
        }
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        glBegin(filled ? GL11.GL_TRIANGLE_FAN : GL11.GL_LINE_STRIP);

        for (double i = 0; i <= amountOfSides; i++) {
            double angle = i * (Math.PI * 2) / amountOfSides;
            GL11.glVertex2d(x + (sideLength * Math.cos(angle)) + sideLength, y + (sideLength * Math.sin(angle)) + sideLength);
        }

        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GlStateManager._enableAlphaTest();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawFilledCircle(int xx, int yy, float radius, Color color) {
        int sections = 50;
        double dAngle = 6.283185307179586D / sections;
        GL11.glPushAttrib(8192);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        glBegin(6);
        for (int i = 0; i < sections; i++) {
            float x = (float) (radius * Math.sin(i * dAngle));
            float y = (float) (radius * Math.cos(i * dAngle));
            GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F,
                    color.getAlpha() / 255.0F);
            glVertex2f(xx + x, yy + y);
        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnd();
        GL11.glPopAttrib();
    }
}

package me.bush7root.anarchyhelper.gui.helpers;

public class HoverHelper {
    public static boolean hovered(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX > x && mouseY > y && mouseX < width && mouseY < height;
    }
}

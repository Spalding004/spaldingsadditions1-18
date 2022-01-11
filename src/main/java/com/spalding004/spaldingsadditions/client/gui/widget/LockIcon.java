package com.spalding004.spaldingsadditions.client.gui.widget;

import java.awt.Rectangle;

public class LockIcon {

    public final Rectangle rect;

    public LockIcon(int x, int y, int width, int height) {
        this.rect = new Rectangle(x, y, width, height);
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return rect.contains(mouseX, mouseY);
    }

}

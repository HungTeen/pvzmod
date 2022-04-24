package com.hungteen.pvz.client.gui.widget;

import com.hungteen.pvz.utils.MathUtil;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 11:29
 **/
public class DisplayField {

    private final int x;
    private final int y;
    private final int texX;
    private final int texY;
    private final int width;
    private final int height;
    protected final List<Component> components;

    public DisplayField(int x, int y, int texX, int texY, int width, int height, List<Component> text) {
        this.x = x;
        this.y = y;
        this.texX = texX;
        this.texY = texY;
        this.width = width;
        this.height = height;
        this.components = text;
    }

    public boolean isInField(int posX, int posY) {
        return MathUtil.isInArea(posX, posY, this.x, this.y, this.width, this.height);
    }

    public List<Component> getTexts() {
        return this.components;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getTexX() {
        return this.texX;
    }

    public int getTexY() {
        return this.texY;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public static class TipField extends DisplayField {

        public TipField(int x, int y, List<Component> text) {
            super(x, y, 0, 0, 12, 12, text);
        }

        public void setTips(List<Component> tips){
            this.components.clear();

            tips.forEach(text -> this.components.add(text));
        }

    }

}

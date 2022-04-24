package com.hungteen.pvz.client.gui.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-23 11:00
 **/
public class PVZScreen extends Screen {

    protected int leftPos;
    protected int topPos;
    protected int imageWidth = 176;
    protected int imageHeight = 166;

    public PVZScreen() {
        super(new TextComponent(""));
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
    }
}

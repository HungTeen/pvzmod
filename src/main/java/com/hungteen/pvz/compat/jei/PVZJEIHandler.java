package com.hungteen.pvz.compat.jei;

import com.hungteen.pvz.client.ClientProxy;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.RecipeManager;

import java.util.Objects;

public class PVZJEIHandler {

    public static RecipeManager getRecipeManager(){
        ClientWorld world = Objects.requireNonNull(ClientProxy.MC.level);
        return world.getRecipeManager();
    }

}

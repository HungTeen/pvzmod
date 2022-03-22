package com.hungteen.pvz.compat.jei;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.common.recipe.RadiationRecipe;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.Objects;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-21 21:13
 **/
public class PVZJEIHandler {

    public static final RecipeType<RadiationRecipe> RADIATION_RECIPE = PVZJEIHandler.create("radiation", RadiationRecipe.class);

    public static RecipeManager getRecipeManager(){
        ClientLevel world = Objects.requireNonNull(ClientProxy.MC.level);
        return world.getRecipeManager();
    }

    public static <T> RecipeType<T> create(String path, Class<T> c){
        return RecipeType.create(PVZMod.MOD_ID, path, c);
    }

}

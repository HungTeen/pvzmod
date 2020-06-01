package com.hungteen.pvzmod.render.entities.plants.fight;

import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeRock;
import com.hungteen.pvzmod.model.entities.plants.fight.ModelSpikeRock;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpikeRock extends RenderPlantBase<EntitySpikeRock>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/fight/spike_rock1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/fight/spike_rock2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/fight/spike_rock3.png");
    private static final ResourceLocation TEXTURE4 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/fight/spike_rock4.png");
    
    public RenderSpikeRock(RenderManager renderManager)
    {
        super(renderManager, new ModelSpikeRock(), 0.5F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySpikeRock entity)
    {
    	if(entity.getSpikeNum()<entity.getMaxSpikeNum()/3) {//0 1 2
    		return TEXTURE1;
    	}else if(entity.getSpikeNum()<entity.getMaxSpikeNum()*2/3) {//3 4 5
    		return TEXTURE2;
    	}else if(entity.getSpikeNum()<entity.getMaxSpikeNum()) {//6 7 8
    		return TEXTURE3;
    	}
        return TEXTURE4;
    }
}
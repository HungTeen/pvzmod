package com.hungteen.pvzmod.render.entities.zombies.grassnight;

import com.hungteen.pvzmod.entities.zombies.grassnight.EntityDancingZombie;
import com.hungteen.pvzmod.model.entities.zombies.grassnight.ModelDancingZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.render.layers.LayerButter;
import com.hungteen.pvzmod.render.layers.LayerCold;
import com.hungteen.pvzmod.render.layers.LayerDanceLight;
import com.hungteen.pvzmod.render.layers.LayerIceBlock;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDancingZombie extends RenderZombieBase<EntityDancingZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/dance/dancing_zombie.png");

    public RenderDancingZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelDancingZombie(), 0.5F);//size
    }

    @Override
    protected void addNewLayer() {
    	this.addLayer(new LayerDanceLight());
    	super.addNewLayer();
    }
    @Override
    protected ResourceLocation getEntityTexture(EntityDancingZombie entity)
    {
        return TEXTURE;
    }
}

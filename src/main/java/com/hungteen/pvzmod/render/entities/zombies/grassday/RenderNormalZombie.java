package com.hungteen.pvzmod.render.entities.zombies.grassday;

import com.hungteen.pvzmod.entities.zombies.grassday.EntityNormalZombie;
import com.hungteen.pvzmod.model.entities.zombies.grassday.ModelNormalZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.render.layers.LayerZombieBeard;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNormalZombie extends RenderZombieBase<EntityNormalZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/normal/normal_zombie.png");

    public RenderNormalZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelNormalZombie(), 0.5F);//size
    }
    
    @Override
    protected void addNewLayer() {
    	this.addLayer(new LayerZombieBeard(this));
    	super.addNewLayer();
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityNormalZombie entity) {
		return TEXTURE;
	}
}
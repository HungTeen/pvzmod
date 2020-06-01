package com.hungteen.pvzmod.render.entities.zombies.normal;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.normal.EntityFlagZombie;
import com.hungteen.pvzmod.entities.zombies.normal.EntityNormalZombie;
import com.hungteen.pvzmod.model.entities.zombies.ModelFlagZombie;
import com.hungteen.pvzmod.model.entities.zombies.normal.ModelNormalZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.render.layers.LayerButter;
import com.hungteen.pvzmod.render.layers.LayerCold;
import com.hungteen.pvzmod.render.layers.LayerIceBlock;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.render.layers.LayerZombieBeard;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFlagZombie extends RenderZombieBase<EntityFlagZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/normal/flag_zombie.png");

    public RenderFlagZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelFlagZombie(), 0.5F);//size
    }

    @Override
    protected void addNewLayer() {
    	this.addLayer(new LayerZombieBeard(this));
    	super.addNewLayer();
    }
    
	@Override
	protected ResourceLocation getEntityTexture(EntityFlagZombie entity) {
		return TEXTURE;
	}

}

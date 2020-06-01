package com.hungteen.pvzmod.render.entities.zombies.normal;

import com.hungteen.pvzmod.entities.zombies.normal.EntityBucketHeadZombie;
import com.hungteen.pvzmod.model.entities.zombies.normal.ModelBucketHeadZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.render.layers.LayerZombieBeard;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBucketHeadZombie extends RenderZombieBase<EntityBucketHeadZombie>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/normal/bucket_head/buckethead_zombie1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/normal/bucket_head/buckethead_zombie2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/normal/bucket_head/buckethead_zombie3.png");
    private static final ResourceLocation TEXTURE4 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/normal/bucket_head/buckethead_zombie4.png");

    public RenderBucketHeadZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelBucketHeadZombie(), 0.5F);//size
    }

    @Override
    protected void addNewLayer() {
    	this.addLayer(new LayerZombieBeard(this));
    	super.addNewLayer();
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityBucketHeadZombie entity) {
		double life=entity.getHealth();
		double max=entity.getMaxHealth();
    	if(life*4>=max*3) return TEXTURE1;  
    	else if(life*2>=max) return TEXTURE2;
    	else if(life*4>=max) return TEXTURE3;
    	else return TEXTURE4;
	}
}
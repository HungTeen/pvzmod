package com.hungteen.pvzmod.render.entities.drops;

import com.hungteen.pvzmod.entities.drops.EntityCoin;
import com.hungteen.pvzmod.model.entities.ModelCoin;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCoin extends RenderLiving<EntityCoin>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/drop/copper_coin.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/drop/silver_coin.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/drop/gold_coin.png");
    private static final ResourceLocation TEXTURE4 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/drop/jewel.png");

    public RenderCoin(RenderManager renderManager)
    {
        super(renderManager, new ModelCoin(), 0F);//size
    }
    
    @Override
    protected void preRenderCallback(EntityCoin entity, float partialTickTime)
    {
    	EntityCoin.CoinType type=entity.getCoinType();
        if(type==EntityCoin.CoinType.COPPER) GlStateManager.scale(0.1f,0.1f,0.1f);
        else if(type==EntityCoin.CoinType.SILVER) GlStateManager.scale(0.13f,0.13f,0.13f);
        else if(type==EntityCoin.CoinType.GOLD) GlStateManager.scale(0.15f,0.15f,0.15f);
        else if(type==EntityCoin.CoinType.JEWEL) GlStateManager.scale(0.21f,0.21f,0.21f);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityCoin entity)
    {
    	EntityCoin.CoinType type=entity.getCoinType();
    	if(type==EntityCoin.CoinType.COPPER) return TEXTURE1;
    	else if(type==EntityCoin.CoinType.SILVER) return TEXTURE2;
    	else if(type==EntityCoin.CoinType.GOLD) return TEXTURE3;
    	else if(type==EntityCoin.CoinType.JEWEL) return TEXTURE4;
        return TEXTURE1;
    }

    @Override
    public void doRender(EntityCoin entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}


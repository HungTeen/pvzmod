package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.night.EntityBackupDancer;
import com.hungteen.pvzmod.model.entities.zombies.ModelBackupDancer;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBackupDancer extends RenderZombieBase<EntityBackupDancer>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/dance/backup_dancer.png");

    public RenderBackupDancer(RenderManager renderManager)
    {
        super(renderManager, new ModelBackupDancer(), 0.5F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBackupDancer entity)
    {
        return TEXTURE;
    }
}


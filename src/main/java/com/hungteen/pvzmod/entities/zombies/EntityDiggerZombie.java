package com.hungteen.pvzmod.entities.zombies;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDiggerZombie extends EntityZombieBase{

    public EntityDiggerZombie(World worldIn)
    {
        super(worldIn);
        this.setSize(0.4F, 0.8F);
    }

    /**
     * Tries to move the entity towards the specified location.
     */
    public void move(MoverType type, double x, double y, double z)
    {
        super.move(type, x, y, z);
        this.doBlockCollisions();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.noClip = true;
        super.onUpdate();
        this.noClip = false;
    }

    protected void initEntityAI()
    {
        super.initEntityAI();
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D);
    }

    public static void registerFixesVex(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityVex.class);
    }

//
//    @SideOnly(Side.CLIENT)
//    public int getBrightnessForRender()
//    {
//        return 15728880;
//    }
//
//    /**
//     * Gets how bright this entity is.
//     */
//    public float getBrightness()
//    {
//        return 1.0F;
//    }
}

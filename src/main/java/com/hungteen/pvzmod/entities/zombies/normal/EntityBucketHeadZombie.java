package com.hungteen.pvzmod.entities.zombies.normal;

import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.client.renderer.entity.RenderIllusionIllager;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityBucketHeadZombie extends EntityNormalZombie{

	
	public EntityBucketHeadZombie(World worldIn) {
		super(worldIn);
		this.setSize(0.8f, 2.3f);
	}
	
	@Override
	protected void setSmallSize() {
		this.setSize(0.3f, 0.6f);
	}
	
//	@Override
//	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
//		this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Item.getItemFromBlock(BlockRegister.POLE)));
//		return super.onInitialSpawn(difficulty, livingdata);
//	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(130.0D);
    }
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundsHandler.METAL_HURT;
	}
}

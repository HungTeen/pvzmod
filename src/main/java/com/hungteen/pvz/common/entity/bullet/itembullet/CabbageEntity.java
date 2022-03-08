package com.hungteen.pvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.entity.EntityRegister;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.IRendersAsItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class CabbageEntity extends PultBulletEntity implements IRendersAsItem {

	public CabbageEntity(EntityType<?> type, Level worldIn) {
		super(type, worldIn);
	}
	
	public CabbageEntity(Level worldIn, LivingEntity shooter) {
		super(EntityRegister.CABBAGE.get(), worldIn, shooter);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ItemRegister.CABBAGE.get());
	}

	protected void dealDamage(Entity target) {
		target.hurt(PVZEntityDamageSource.cabbage(this, this.getThrower()), this.getAttackDamage());
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5F, 0.5F);
	}

}

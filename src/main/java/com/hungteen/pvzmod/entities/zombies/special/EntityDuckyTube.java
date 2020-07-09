package com.hungteen.pvzmod.entities.zombies.special;

import com.hungteen.pvzmod.entities.zombies.base.EntityWaterToolBase;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase.Type;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityDuckyTube extends EntityWaterToolBase{
	
	public EntityDuckyTube(World worldIn) {
		super(worldIn);
		this.setSize(0.2f, 0.2f);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(!this.world.isRemote) {
			Block block1=this.world.getBlockState(new BlockPos(posX,posY-1,posZ)).getBlock();
			Block block2=this.world.getBlockState(new BlockPos(posX,posY,posZ)).getBlock();
			if(block1!=Blocks.WATER&&block2!=Blocks.WATER) {
				//if(this.ticksExisted%20==0) System.out.println(block);
			    for(Entity entity:this.getPassengers()) {
			    	entity.dismountRidingEntity();
			    }
			}
		}
	}

	@Override
	public float getLife() {
		return 20;
	}
}

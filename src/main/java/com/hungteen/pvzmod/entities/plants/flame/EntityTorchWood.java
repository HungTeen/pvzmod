package com.hungteen.pvzmod.entities.plants.flame;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityTorchWood extends EntityPlantBase{
	
	public EntityTorchWood(World worldIn) {
		super(worldIn);
		setSize(1f, 2f);
	}
	
	@Override
    protected void initEntityAI()
    {
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
    }
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		spawnFire();
	}
	
	private void spawnFire()
	{
		if(this.isPlantInSuperMode()) {
			Main.proxy.spawnParticle(PVZParticleType.BLUE_FIRE, this.posX, this.posY+1.2, this.posZ, 0, 0, 0);
		}
		else {
			Main.proxy.spawnParticle(PVZParticleType.YELLOW_FIRE, this.posX, this.posY+1.2, this.posZ, 0, 0, 0);
		}
	}

	@Override
	public int getSuperTimeLength() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 24000;
		else if(lvl<=13) return 48000;
		else if(lvl<=20) return 72000;
		return 24000;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.TORCH_WOOD;
	}
}

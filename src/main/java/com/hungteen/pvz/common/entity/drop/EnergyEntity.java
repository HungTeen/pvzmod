package com.hungteen.pvz.common.entity.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class EnergyEntity extends DropEntity{

	private int changeVTime = 50;
	public EnergyEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		this.setAmount(1);//always 1
		this.setNoGravity(true);
	}

	@Override
	public void playerTouch(PlayerEntity entityIn) {
		if(!this.level.isClientSide) {
			PlayerUtil.addResource(entityIn, Resources.ENERGY_NUM, this.getAmount());
		}else {
			this.level.playSound(entityIn, blockPosition(), SoundRegister.JEWEL_PICK.get(), SoundCategory.NEUTRAL, 1f, 1f);
		}
		this.remove();
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.liveTime % this.changeVTime == 0) {
			double mult = 0.2f;
//			System.out.println(this.getMotion().y);
			Vector3d v = new Vector3d(this.random.nextInt(1000) - 500, this.random.nextInt(1000) - 500, this.random.nextInt(1000) - 500).normalize();
			v = v.scale(mult);
			this.setDeltaMovement(v.x ,v.y / 5, v.z);
		}
	}
	
	@Override
	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.EnergyLiveTick.get();
	}

}
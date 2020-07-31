package com.hungteen.pvz.entity.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EnergyEntity extends DropEntity{

	private int changeVTime = 100;
	public EnergyEntity(EntityType<? extends Entity> type, World worldIn) {
		super(type, worldIn);
		this.setAmount(1);//always 1
		this.setNoGravity(true);
	}

	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {
		if(!this.world.isRemote) {
			PlayerUtil.addPlayerStats(entityIn, Resources.ENERGY_NUM, this.getAmount());
		}
		this.remove();
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.liveTime%this.changeVTime==0) {
			double mult=0.2f;
//			System.out.println(this.getMotion().y);
			Vec3d v=new Vec3d(this.rand.nextInt(1000),this.rand.nextInt(1000),this.rand.nextInt(1000)).normalize();
			this.setMotion(v.scale(mult));
		}
	}
	
	@Override
	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.ENTITY_SETTINGS.dropLiveTick.energyLiveTick.get();
	}

}

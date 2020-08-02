package com.hungteen.pvz.entity.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class SunEntity extends DropEntity{
	
	private float fall_speed = 0.03f;
	
	public SunEntity(EntityType<? extends Entity> type, World worldIn) {
		super(type, worldIn);
		this.setAmount(25);//default sun amount (nature spawn)
		this.setNoGravity(true);
	}

	@Override
	public void tick() {
		super.tick();
		if(!this.onGround) {
			this.setMotion(0, -fall_speed, 0);
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		//25 0.6 
		int amount=this.getAmount();
		float w=amount*1f/200+0.3f,h=amount*1f/75+0.1f;
		return new EntitySize(w, h, false); //max (0.8w,1.5h) min(0.4w,0.3h)
	}
	
	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {
		if(!this.world.isRemote) {
			PlayerUtil.addPlayerStats(entityIn, Resources.SUN_NUM, this.getAmount());
//			this.world.playSound(null, getPosition(), SoundRegister.SUN_PICK.get(), SoundCategory.NEUTRAL, 1f,1f);
//			this.playSound(SoundRegister.SUN_PICK.get(), 1f, 1f);
//			ChickenEntity
		}else {
//			System.out.println(this.isSilent());
			this.world.playSound(entityIn, getPosition(), SoundRegister.SUN_PICK.get(), SoundCategory.NEUTRAL, 1f,1f);
//			this.playSound(SoundRegister.SUN_PICK.get(), 1f, 1f);
		}
		this.remove();
	}
	
	@Override
	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.ENTITY_SETTINGS.dropLiveTick.sunLiveTick.get();
	}
	
	
}

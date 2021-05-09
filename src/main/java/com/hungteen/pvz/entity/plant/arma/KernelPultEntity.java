package com.hungteen.pvz.entity.plant.arma;

import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.ButterEntity;
import com.hungteen.pvz.entity.bullet.KernelEntity;
import com.hungteen.pvz.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.entity.plant.base.PlantPultEntity;
import com.hungteen.pvz.item.tool.card.ImitaterCardItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class KernelPultEntity extends PlantPultEntity {

	private static final DataParameter<Integer> CURRENT_BULLET = EntityDataManager.defineId(KernelPultEntity.class, DataSerializers.INT);
	private static final int BUTTER_CHANCE = 10;
	
	public KernelPultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CURRENT_BULLET, CornTypes.KERNEL.ordinal());
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getPultRange(), 11, 10));
	}
	
	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if(! level.isClientSide && hand == Hand.MAIN_HAND && ! EntityUtil.checkCanEntityAttack(this, player)) {
			ItemStack stack = player.getItemInHand(hand);
			if(stack.getItem() instanceof PlantCardItem) {
				PlantCardItem item = (PlantCardItem) stack.getItem();
				if(item.plantType == Plants.COB_CANNON) {
					Optional<KernelPultEntity> pult = this.getNearByPult(player);
					if(pult.isPresent()) {
						PlantCardItem.checkSunAndSummonPlant(player, stack, item, blockPosition(), (plantEntity) -> {
							this.onPlantUpgrade(plantEntity);
							plantEntity.plantSunCost += pult.get().plantSunCost;
							pult.get().remove();
						});
					}
				} else if(item instanceof ImitaterCardItem && ((ImitaterCardItem) item).isPlantTypeEqual(stack, Plants.COB_CANNON)) {
					Optional<KernelPultEntity> pult = this.getNearByPult(player);
					if(pult.isPresent()) {
					    ImitaterCardItem.checkSunAndSummonImitater(player, stack, item, blockPosition(), (imitater) -> {
						    imitater.targetPlantEntity = Optional.of(this);
						    imitater.plantSunCost += pult.get().plantSunCost;
						    pult.get().remove();
					    });
					}
				}
			}
		}
		return super.interactAt(player, vec3d, hand);
	}
	
	private Optional<KernelPultEntity> getNearByPult(PlayerEntity player){
		float range = 1.5F;
		List<KernelPultEntity> list = level.getEntitiesOfClass(KernelPultEntity.class, EntityUtil.getEntityAABB(this, range, range), (pult) -> {
			return ! pult.is(this) && pult.getPlantEnumName() == Plants.KERNEL_PULT && ! EntityUtil.checkCanEntityAttack(pult, player);
		});
		if(list.isEmpty()) return Optional.empty();
		return Optional.ofNullable(list.get(0));
	}
	
	@Override
	public void startPultAttack() {
		super.startPultAttack();
		this.changeBullet();
	}
	
	protected void changeBullet() {
		if(this.isPlantInSuperMode() && ! this.isSuperOut) {
			this.setCurrentBullet(CornTypes.BUTTER);
			return ;
		}
		this.setCurrentBullet(this.getRandom().nextInt(BUTTER_CHANCE) == 0 ? CornTypes.BUTTER : CornTypes.KERNEL);
	}
	
	@Override
	public void pultBullet() {
		LivingEntity target = this.getTarget();
		this.pultKernel(target, false);
	}
	
	@Override
	protected void doSuperAttackToTarget(LivingEntity target) {
		this.pultKernel(target, true);
	}
	
	protected Optional<PultBulletEntity> pultKernel(LivingEntity target, boolean isSuper) {
		if(target == null) return Optional.empty();
		PultBulletEntity bullet = null;
		if(isSuper || this.getCurrentBullet() == CornTypes.BUTTER) {
			bullet = new ButterEntity(level, this);
		} else if(this.getCurrentBullet() == CornTypes.KERNEL) {
			bullet = new KernelEntity(level, this);
		} 
		if(bullet == null) {
			System.out.println("ERROR : Wrong kernel Pult Throw Bullet Type !");
			return Optional.empty();
		}
		bullet.setPos(this.getX(), this.getY() + 1.7f, this.getZ());
		bullet.shootPultBullet(target);
        this.level.addFreshEntity(bullet);
        EntityUtil.playSound(this, SoundRegister.PLANT_THROW.get());
        this.setCurrentBullet(CornTypes.KERNEL); 
        return Optional.of(bullet);
	}
	
	public EffectInstance getButterEffect() {
		return new EffectInstance(EffectRegister.BUTTER_EFFECT.get(), this.getButterDuration(), 1, false, false);
	}
	
	public int getButterDuration() {
		return 100;
	}
	
	public float getKernelDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 18) {
			int now = (lvl -1) / 2;
			return 2 + 0.25f * now;
		}
		if(lvl <= 19) return 4.25f;
		return 4.5f;
	}
	
	public float getButterDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 3.75F + 0.25F * lvl;
		return 9;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8F, 1F);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("current_bullet_type")) {
			this.setCurrentBullet(CornTypes.values()[compound.getInt("current_bullet_type")]);
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("current_bullet_type", this.getCurrentBullet().ordinal());
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.KERNEL_PULT;
	}
	
	public void setCurrentBullet(CornTypes type) {
		this.entityData.set(CURRENT_BULLET, type.ordinal());
	}
	
	public CornTypes getCurrentBullet() {
		return CornTypes.values()[this.entityData.get(CURRENT_BULLET)];
	}

	public static enum CornTypes{
		KERNEL,
		BUTTER,
		ROCKET
	}
	
}

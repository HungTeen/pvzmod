package com.hungteen.pvzmod.entities.npcs;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Enums;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTreeMan extends EntityTrader{

	public static final float entityWidth = 1.0f;

	public EntityTreeMan(World world) {
		super(world, entityWidth, 3.1f);
	}

//	@Nullable
//    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
//    {
//        livingdata = super.onInitialSpawn(difficulty, livingdata);
//        if (this.world.rand.nextInt(1) == 0)
//        {
//            EntitySkeleton entityskeleton = new EntitySkeleton(this.world);
//            entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
//            entityskeleton.onInitialSpawn(difficulty, (IEntityLivingData)null);
//            this.world.spawnEntity(entityskeleton);
//            entityskeleton.startRiding(this);
//        }
//        return livingdata;
//    }
	
	@Override
	protected double getBaseMaxHealth() {
		return 100;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

//	@Nullable
//	@Override
//	protected ResourceLocation getLootTable() {
//		return LootSystemRegister.entityAssassin;
//	}

	@Override
	protected Enums.Guis getTraderGui() {
		return Enums.Guis.TRADE;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
//		ItemStack heldStack = player.getHeldItem(hand);
//
//		if (heldStack.getItem() == ItemRegister.rockBones) {
//			if (!world.isRemote)
//				player.setHeldItem(hand, ItemRegister.millenniumUpgrader.newValidStack());
//
//			return true;
//		}

		return super.processInteract(player, hand);
	}

//	@Override
//	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
//		newTradesList.add(new EntityTraderRecipe(new ItemStack(ItemInit.GOLD_COIN, 64), new ItemStack(ItemInit.JEWEL, 1)));
//		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableGooBall, 3)));
//		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 3), new ItemStack(WeaponRegister.throwableChakram, 2)));
//		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableHellfire, 1)));
//		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 3), new ItemStack(WeaponRegister.throwableVulkram, 2)));
//		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(ItemRegister.metalSlug, 2)));
//		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(ItemRegister.bulletLimonite, 3)));
//	}

	@Override
	protected void getTradesList(NonNullList<EntityTraderRecipe> newTradesList) {
//		newTradesList.add(new EntityTraderRecipe(new ItemStack(ItemRegister.ALUMINUM_INGOT, 64), new ItemStack(ItemRegister.BARRIER_HELMET, 1)));
		
	}

	@Override
	protected SoundEvent getAmbientSound()
    {
        return SoundsHandler.CRAZYDAVE_AMBIENT;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundsHandler.CRAZYDAVE_HURT;
    }
}

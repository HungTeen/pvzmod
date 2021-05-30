package com.hungteen.pvz.common.entity.npc;

import com.hungteen.pvz.common.container.shop.DaveShopContainer;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class CrazyDaveEntity extends AbstractDaveEntity {

	public CrazyDaveEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if (!level.isClientSide && player instanceof ServerPlayerEntity) {
			NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {

				@Override
				public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_,
						PlayerEntity p_createMenu_3_) {
					return new DaveShopContainer(p_createMenu_1_, p_createMenu_3_);
				}

				@Override
				public ITextComponent getDisplayName() {
					return new TranslationTextComponent("gui.pvz.dave_shop.show");
				}
			});
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9f, 2.6f);
	}

}

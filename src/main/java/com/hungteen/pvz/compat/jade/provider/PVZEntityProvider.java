package com.hungteen.pvz.compat.jade.provider;

import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.compat.jade.JadeRegister;
import com.hungteen.pvz.utils.EntityUtil;

import mcp.mobius.waila.api.IEntityAccessor;
import mcp.mobius.waila.api.IEntityComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import snownee.jade.Jade;

public class PVZEntityProvider implements IEntityComponentProvider {

	public static final PVZEntityProvider INSTANCE = new PVZEntityProvider();
	
	@Override
	public void appendBody(List<ITextComponent> tooltip, IEntityAccessor accessor, IPluginConfig config) {
		if(config.get(JadeRegister.CONFIG_SHOW_DEFENCE_HEALTH)) {
		    appendDefenceHealth(accessor.getEntity(), tooltip);
		}
		//hide info need shift to check.
		if(accessor.getPlayer().getPose() == Pose.CROUCHING) {
			if(config.get(JadeRegister.CONFIG_SHOW_OWNER)) {
				appendOwner(accessor.getWorld(), accessor.getEntity(), tooltip);
			}
			if(config.get(JadeRegister.CONFIG_SHOW_LEVEL)) {
			    appendLevel(accessor.getEntity(), tooltip);
			}
			if(config.get(JadeRegister.CONFIG_SHOW_ZOMBIE_REDUCTION)) {
			    appendZombieHurtReduction(accessor.getEntity(), tooltip);
			}
		}
	}
	
	private void appendOwner(World world, Entity entity, List<ITextComponent> tooltip) {
		if(! (entity instanceof IHasOwner)) {//only use for IHasOwner.
			return ;
		}
		((IHasOwner) entity).getOwnerUUID().ifPresent(uuid -> {
			Optional.ofNullable(world.getPlayerByUUID(uuid)).ifPresent(player -> {
				tooltip.add(new TranslationTextComponent("tooltip.pvz.owner").append(" : " + player.getName().getString()).withStyle(TextFormatting.GREEN));
			});
		});
	}
	
	private void appendLevel(Entity entity, List<ITextComponent> tooltip) {
		if(! (entity instanceof LivingEntity)) {//only use for living.
			return ;
		}
		int lvl = EntityUtil.getEntityLevel((LivingEntity) entity);
		if(lvl == 0) {//no need to render if there is no level.
			return ;
		}
		tooltip.add(new TranslationTextComponent("tooltip.pvz.level").append(" : " + lvl).withStyle(TextFormatting.YELLOW));
	}
	
	private void appendDefenceHealth(Entity entity, List<ITextComponent> tooltip) {
		if(! (entity instanceof LivingEntity)) {//only use for living.
			return ;
		}
		float health = EntityUtil.getCurrentDefenceHealth((LivingEntity) entity);
		if(health == 0) {//no need to render if there is no defence health.
			return ;
		}
		tooltip.add(new TranslationTextComponent("tooltip.pvz.defence").append(" : " + String.format("%s", Jade.dfCommas.format(health))).withStyle(TextFormatting.RED));
	}
	
	private void appendZombieHurtReduction(Entity entity, List<ITextComponent> tooltip) {
		if(! (entity instanceof PVZZombieEntity)) {//only use for zombie.
			return ;
		}
		float percent = ((PVZZombieEntity) entity).getHurtReduction();
		if(percent == 1) {//no reduction.
			return ;
		}
		tooltip.add(new TranslationTextComponent("tooltip.pvz.reduction").append(" : " + Math.floor(100 * (1 - percent)) + "%").withStyle(TextFormatting.GRAY));
	}
	
}

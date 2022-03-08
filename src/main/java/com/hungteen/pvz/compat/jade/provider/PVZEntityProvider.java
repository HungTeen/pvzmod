package com.hungteen.pvz.compat.jade.provider;

import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.compat.jade.JadeRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.StringUtil;
import mcp.mobius.waila.api.IEntityAccessor;
import mcp.mobius.waila.api.IEntityComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import snownee.jade.Jade;

import java.util.List;
import java.util.Optional;

public class PVZEntityProvider implements IEntityComponentProvider {

	public static final PVZEntityProvider INSTANCE = new PVZEntityProvider();
	
	@Override
	public void appendBody(List<Component> tooltip, IEntityAccessor accessor, IPluginConfig config) {
		if(config.get(JadeRegister.CONFIG_SHOW_DEFENCE_HEALTH)) {
		    appendDefenceHealth(accessor.getEntity(), tooltip);
		}
		if(config.get(JadeRegister.CONFIG_SHOW_OWNER)) {
			appendOwner(accessor.getWorld(), accessor.getEntity(), tooltip);
		}
		if(config.get(JadeRegister.CONFIG_SHOW_SKILLS)) {
			appendSkills(accessor.getWorld(), accessor.getEntity(), tooltip);
		}
		//hide info need shift to check.
		if(accessor.getPlayer().getPose() == Pose.CROUCHING) {

		}
	}
	
	private void appendOwner(Level world, Entity entity, List<Component> tooltip) {
		if(! (entity instanceof IHasOwner)) {//only use for IHasOwner.
			return ;
		}
		((IHasOwner) entity).getOwnerUUID().ifPresent(uuid -> {
			Optional.ofNullable(world.getPlayerByUUID(uuid)).ifPresent(player -> {
				tooltip.add(new TranslatableComponent("tooltip.pvz.owner").append(" : " + player.getName().getString()).withStyle(ChatFormatting.GREEN));
			});
		});
	}
	
	private void appendDefenceHealth(Entity entity, List<Component> tooltip) {
		if(! (entity instanceof LivingEntity)) {//only use for living.
			return ;
		}
		double health = EntityUtil.getCurrentDefenceHealth((LivingEntity) entity);
		if(health == 0) {//no need to render if there is no defence health.
			return ;
		}
		tooltip.add(new TranslatableComponent("tooltip.pvz.defence").append(" : " + String.format("%s", Jade.dfCommas.format(health))).withStyle(ChatFormatting.RED));
	}

	private void appendSkills(Level world, Entity entity, List<Component> tooltip) {
		if(entity instanceof AbstractPAZEntity){
			final IPAZType type = ((AbstractPAZEntity) entity).getPAZType();
			final CompoundTag nbt = ((AbstractPAZEntity) entity).getSkills();
			type.getSkills().forEach(skillType -> {
				if(nbt != null){
					final int lvl = SkillTypes.getSkillLevel(nbt, skillType);
					if(lvl > 0){
						tooltip.add(skillType.getText().append(StringUtil.getRomanString(lvl)).withStyle(ChatFormatting.DARK_PURPLE));
					}
				}
			});
		}
	}
	
}

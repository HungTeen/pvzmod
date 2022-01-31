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
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import snownee.jade.Jade;

import java.util.List;
import java.util.Optional;

public class PVZEntityProvider implements IEntityComponentProvider {

	public static final PVZEntityProvider INSTANCE = new PVZEntityProvider();
	
	@Override
	public void appendBody(List<ITextComponent> tooltip, IEntityAccessor accessor, IPluginConfig config) {
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
	
	private void appendDefenceHealth(Entity entity, List<ITextComponent> tooltip) {
		if(! (entity instanceof LivingEntity)) {//only use for living.
			return ;
		}
		double health = EntityUtil.getCurrentDefenceHealth((LivingEntity) entity);
		if(health == 0) {//no need to render if there is no defence health.
			return ;
		}
		tooltip.add(new TranslationTextComponent("tooltip.pvz.defence").append(" : " + String.format("%s", Jade.dfCommas.format(health))).withStyle(TextFormatting.RED));
	}

	private void appendSkills(World world, Entity entity, List<ITextComponent> tooltip) {
		if(entity instanceof AbstractPAZEntity){
			final IPAZType type = ((AbstractPAZEntity) entity).getPAZType();
			final CompoundNBT nbt = ((AbstractPAZEntity) entity).getSkills();
			type.getSkills().forEach(skillType -> {
				if(nbt != null){
					final int lvl = SkillTypes.getSkillLevel(nbt, skillType);
					if(lvl > 0){
						tooltip.add(skillType.getText().append(StringUtil.getRomanString(lvl)).withStyle(TextFormatting.DARK_PURPLE));
					}
				}
			});
		}
	}
	
}

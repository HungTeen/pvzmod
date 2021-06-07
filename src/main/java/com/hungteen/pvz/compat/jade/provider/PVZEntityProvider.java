package com.hungteen.pvz.compat.jade.provider;

import java.util.List;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.compat.jade.JadeRegister;
import com.hungteen.pvz.utils.EntityUtil;

import mcp.mobius.waila.addons.core.PluginCore;
import mcp.mobius.waila.api.IEntityAccessor;
import mcp.mobius.waila.api.IEntityComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.RenderableTextComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import snownee.jade.Jade;
import snownee.jade.Renderables;

public class PVZEntityProvider implements IEntityComponentProvider {

	public static final PVZEntityProvider INSTANCE = new PVZEntityProvider();
	
	@Override
	public void appendBody(List<ITextComponent> tooltip, IEntityAccessor accessor, IPluginConfig config) {
		if(accessor.getEntity() instanceof LivingEntity) {
			if(config.get(JadeRegister.CONFIG_SHOW_DEFENCE_HEALTH)) {
			    appendDefenceHealth((LivingEntity) accessor.getEntity(), tooltip);
			}
			if(accessor.getEntity() instanceof PVZZombieEntity) {
				if(config.get(JadeRegister.CONFIG_SHOW_ZOMBIE_REDUCTION)) {
				    appendZombieHurtReduction((PVZZombieEntity) accessor.getEntity(), tooltip);
				}
			}
			if(config.get(JadeRegister.CONFIG_SHOW_LEVEL) && accessor.getPlayer().getPose() == Pose.CROUCHING) {
				appendLevel((LivingEntity) accessor.getEntity(), tooltip);
			}
		}
	}
	
	private void appendLevel(LivingEntity living, List<ITextComponent> tooltip) {
		int lvl = EntityUtil.getEntityLevel(living);
		if(lvl == 0) {//no need to render if there is no level.
			return ;
		}
		RenderableTextComponent text = Renderables.offsetText("Level : " + lvl, 1, 0);
		tooltip.add(text);
	}
	
	private void appendDefenceHealth(LivingEntity living, List<ITextComponent> tooltip) {
		float health = EntityUtil.getCurrentDefenceHealth(living);
		if(health == 0) {//no need to render if there is no defence health.
			return ;
		}
		CompoundNBT healthData = new CompoundNBT();
		healthData.putFloat("health", 1);
		healthData.putFloat("max", 1);
		RenderableTextComponent icon = new RenderableTextComponent(PluginCore.RENDER_ENTITY_HEALTH, healthData);
		RenderableTextComponent text = Renderables.offsetText(String.format("%s", Jade.dfCommas.format(health)), 5, 0);
		tooltip.add(Renderables.of(icon, text));
	}
	
	private void appendZombieHurtReduction(PVZZombieEntity zombie, List<ITextComponent> tooltip) {
		float percent = zombie.getHurtReduction();
		if(percent == 1) {//no reduction.
			return ;
		}
		CompoundNBT armorData = new CompoundNBT();
		armorData.putFloat("armor", 1);
		RenderableTextComponent icon = new RenderableTextComponent(PluginCore.RENDER_ENTITY_ARMOR, armorData);
		RenderableTextComponent text = Renderables.offsetText(String.format("%s", Jade.dfCommas.format(1 - percent)), 0, 0);
		tooltip.add(Renderables.of(icon, text));
	}
	
}

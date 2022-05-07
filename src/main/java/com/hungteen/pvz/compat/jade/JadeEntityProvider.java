package com.hungteen.pvz.compat.jade;

import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.PVZPAZ;
import com.hungteen.pvz.common.impl.type.SkillTypes;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.StringUtil;
import mcp.mobius.waila.api.EntityAccessor;
import mcp.mobius.waila.api.IEntityComponentProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-07 16:47
 **/
public class JadeEntityProvider implements IEntityComponentProvider {

    public static final JadeEntityProvider INSTANCE = new JadeEntityProvider();

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig config) {
        if(config.get(JadeRegister.CONFIG_SHOW_OWNER)){
            appendOwner(accessor.getLevel(), accessor.getEntity(), tooltip);
        }
        if(config.get(JadeRegister.CONFIG_SHOW_SKILLS)){
            appendSkills(accessor.getLevel(), accessor.getEntity(), tooltip);
        }
    }

    private void appendOwner(Level world, Entity entity, ITooltip tooltip) {
        Optional.ofNullable(EntityUtil.getEntityOwner(world, entity)).ifPresent(player -> {
            tooltip.add(new TranslatableComponent("tooltip.pvz.owner", player.getName().getString()).withStyle(ChatFormatting.GREEN));
        });
    }

    private void appendSkills(Level world, Entity entity, ITooltip tooltip) {
        if(entity instanceof PVZPAZ){
            final IPAZType type = ((PVZPAZ) entity).getPAZType();
            final CompoundTag nbt = ((PVZPAZ) entity).getSkills();
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

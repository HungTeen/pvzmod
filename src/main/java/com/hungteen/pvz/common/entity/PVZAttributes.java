package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.PVZMod;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-01-19 15:56
 **/
public class PVZAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, PVZMod.MOD_ID);

    public static final RegistryObject<Attribute> INNER_DEFENCE_HP = ATTRIBUTES.register("inner_defence_hp", () -> new RangedAttribute("attribute.name.pvz.inner_defence_hp", 0D, 0D, 1000000D).setSyncable(true));
    public static final RegistryObject<Attribute> OUTER_DEFENCE_HP = ATTRIBUTES.register("outer_defence_hp", () -> new RangedAttribute("attribute.name.pvz.outer_defence_hp", 0D, 0D, 1000000D).setSyncable(true));

}

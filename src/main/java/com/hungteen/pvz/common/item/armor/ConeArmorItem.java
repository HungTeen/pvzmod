package com.hungteen.pvz.common.item.armor;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.item.ConeArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-05 17:24
 **/
public class ConeArmorItem extends PVZArmorItem {

    public ConeArmorItem() {
        super(PVZArmorMaterials.CONE, EquipmentSlot.HEAD);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return PREFIX + "cone.png";
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(ConeArmorRender.INSTANCE);
    }

    private static class ConeArmorRender implements IItemRenderProperties{

        private static final ConeArmorRender INSTANCE = new ConeArmorRender();

        @Nullable
        @Override
        public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
            EntityModelSet models = Minecraft.getInstance().getEntityModels();
            ModelPart root = models.bakeLayer(armorSlot == EquipmentSlot.LEGS ? PVZModelLayers.CONE_INNER_ARMOR : PVZModelLayers.CONE_OUTER_ARMOR);
            final ConeArmorModel model = new ConeArmorModel(root);
            return model;
        }

    }

}

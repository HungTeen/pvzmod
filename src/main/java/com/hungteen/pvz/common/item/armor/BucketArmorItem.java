package com.hungteen.pvz.common.item.armor;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.item.BucketArmorModel;
import com.hungteen.pvz.common.item.PVZItemTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-18 13:33
 **/
public class BucketArmorItem extends PVZArmorItem{

    public BucketArmorItem() {
        super(PVZArmorMaterials.BUCKET, EquipmentSlot.HEAD);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return PREFIX + "bucket.png";
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(BucketArmorRender.INSTANCE);
    }

    private static class BucketArmorRender implements IItemRenderProperties{

        private static final BucketArmorRender INSTANCE = new BucketArmorRender();

        @Nullable
        @Override
        public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
            EntityModelSet models = Minecraft.getInstance().getEntityModels();
            ModelPart root = models.bakeLayer(armorSlot == EquipmentSlot.LEGS ? PVZModelLayers.BUCKET_INNER_ARMOR : PVZModelLayers.BUCKET_OUTER_ARMOR);
            final BucketArmorModel model = new BucketArmorModel(root);
            return model;
        }

    }

}

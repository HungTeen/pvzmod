package com.hungteen.pvz.client;

import com.hungteen.pvz.client.render.layer.fullskin.ColdLayer;
import com.hungteen.pvz.common.CommonProxy;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.block.others.SteelLadderBlock;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.armor.BucketArmorItem;
import com.hungteen.pvz.common.item.armor.ConeArmorItem;
import com.hungteen.pvz.common.item.armor.FootballArmorItem;
import com.hungteen.pvz.common.item.armor.GigaArmorItem;
import com.hungteen.pvz.common.item.tool.GardenCompassItem;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.command.impl.LocateCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

import java.util.Optional;

import static com.hungteen.pvz.common.world.biome.BiomeRegister.ZEN_GARDEN;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    public static final Minecraft MC = Minecraft.getInstance();

    @Override
    public void init() {
    }

    ;

    @Override
    public void postInit() {
        this.addLayersForRender();
    }

    ;

    @Override
    public void setUpClient() {
        ConeArmorItem.initArmorModel();
        BucketArmorItem.initArmorModel();
        FootballArmorItem.initArmorModel();
        GigaArmorItem.initArmorModel();
        KeyBindRegister.init();
        ItemModelsProperties.register(ItemRegister.SCREEN_DOOR.get(), StringUtil.prefix("blocking"), (stack, world, entity) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
        });
        ItemModelsProperties.register(ItemRegister.GARDEN_COMPASS.get(), StringUtil.prefix("angle"), new IItemPropertyGetter() {
            private final Angle wobble = new Angle();
            private final Angle wobbleRandom = new Angle();
            private BlockPos pos = null;

            public float call(ItemStack p_call_1_, @Nullable ClientWorld p_call_2_, @Nullable LivingEntity p_call_3_) {
                Entity entity = p_call_3_ != null ? p_call_3_ : p_call_1_.getEntityRepresentation();
                if (entity == null) {
                    return 0.0F;
                } else {
                    if (p_call_2_ == null && entity.level instanceof ClientWorld) {
                        p_call_2_ = (ClientWorld) entity.level;
                    }

                    pos = this.getPointPosition(p_call_1_);
                    long i = p_call_2_.getGameTime();
                    if (pos != null && !(entity.position().distanceToSqr((double) pos.getX() + 0.5D, entity.position().y(), (double) pos.getZ() + 0.5D) < (double) 1.0E-5F)) {
                        boolean flag = p_call_3_ instanceof PlayerEntity && ((PlayerEntity) p_call_3_).isLocalPlayer();
                        double d1 = 0.0D;
                        if (flag) {
                            d1 = p_call_3_.yRot;
                        } else if (entity instanceof ItemFrameEntity) {
                            d1 = this.getFrameRotation((ItemFrameEntity) entity);
                        } else if (entity instanceof ItemEntity) {
                            d1 = 180.0F - ((ItemEntity) entity).getSpin(0.5F) / ((float) Math.PI * 2F) * 360.0F;
                        } else if (p_call_3_ != null) {
                            d1 = p_call_3_.yBodyRot;
                        }

                        d1 = MathHelper.positiveModulo(d1 / 360.0D, 1.0D);
                        double d2 = this.getAngleTo(Vector3d.atCenterOf(pos), entity) / (double) ((float) Math.PI * 2F);
                        double d3;
                        if (flag) {
                            if (this.wobble.shouldUpdate(i)) {
                                this.wobble.update(i, 0.5D - (d1 - 0.25D));
                            }

                            d3 = d2 + this.wobble.rotation;
                        } else {
                            d3 = 0.5D - (d1 - 0.25D - d2);
                        }

                        return MathHelper.positiveModulo((float) d3, 1.0F);
                    } else {
                        if (this.wobbleRandom.shouldUpdate(i)) {
                            this.wobbleRandom.update(i, Math.random());
                        }

                        double d0 = this.wobbleRandom.rotation + (double) ((float) p_call_1_.hashCode() / 2.14748365E9F);
                        return MathHelper.positiveModulo((float) d0, 1.0F);
                    }
                }
            }

            @Nullable
            private BlockPos getPointPosition(ItemStack itemstack) {
                return ((GardenCompassItem) itemstack.getItem()).pos;
            }

            private double getFrameRotation(ItemFrameEntity p_239441_1_) {
                Direction direction = p_239441_1_.getDirection();
                int i = direction.getAxis().isVertical() ? 90 * direction.getAxisDirection().getStep() : 0;
                return MathHelper.wrapDegrees(180 + direction.get2DDataValue() * 90 + p_239441_1_.getRotation() * 45 + i);
            }

            private double getAngleTo(Vector3d p_239443_1_, Entity p_239443_2_) {
                return Math.atan2(p_239443_1_.z() - p_239443_2_.getZ(), p_239443_1_.x() - p_239443_2_.getX());
            }
        });
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void addLayersForRender() {
        MC.getEntityRenderDispatcher().renderers.values().forEach(r -> {
            if (r instanceof LivingRenderer) {
                ((LivingRenderer) r).addLayer(new ColdLayer<>((LivingRenderer) r));
            }
        });
    }

    @Override
    public void climbUp() {
        final PlayerEntity player = this.getPlayer();
        if (player != null && player.horizontalCollision && player.onClimbable()) {
            //is on steel ladder.
            if (player.level.getBlockState(player.blockPosition()).getBlock().is(BlockRegister.STEEL_LADDER.get())) {
                ladderSpeed = Math.min(SteelLadderBlock.MAX_SPEED_UP, ladderSpeed + SteelLadderBlock.UP_SPEED * 0.8F);
                final Vector3d vec = player.getDeltaMovement();
                player.setDeltaMovement(vec.x, ladderSpeed, vec.z);
            } else {
                ladderSpeed = Math.max(0, ladderSpeed - SteelLadderBlock.UP_SPEED);
            }
        } else {
            ladderSpeed = 0.06F;
        }
    }

    @Override
    public PlayerEntity getPlayer() {
        return MC.player;
    }


    @OnlyIn(Dist.CLIENT)
    static class Angle {
        private double rotation;
        private double deltaRotation;
        private long lastUpdateTick;

        private Angle() {
        }

        private boolean shouldUpdate(long p_239448_1_) {
            return this.lastUpdateTick != p_239448_1_;
        }

        private void update(long p_239449_1_, double p_239449_3_) {
            this.lastUpdateTick = p_239449_1_;
            double d0 = p_239449_3_ - this.rotation;
            d0 = MathHelper.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
            this.deltaRotation += d0 * 0.1D;
            this.deltaRotation *= 0.8D;
            this.rotation = MathHelper.positiveModulo(this.rotation + this.deltaRotation, 1.0D);
        }
    }
}

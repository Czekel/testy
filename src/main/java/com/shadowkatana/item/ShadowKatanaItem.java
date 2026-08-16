package com.shadowkatana.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ShadowKatanaItem extends Item {

    private static final int DASH_COOLDOWN_TICKS = 60;

    public ShadowKatanaItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 60, 0));
        attacker.heal(1.0f);

        if (target.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.SQUID_INK,
                    target.getX(), target.getBodyY(0.5), target.getZ(),
                    12, 0.3, 0.3, 0.3, 0.02
            );
        }

        return super.postHit(stack, target, attacker);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.getItemCooldownManager().isCoolingDown(stack)) {
            return ActionResult.FAIL;
        }

        if (!world.isClient()) {
            Vec3d look = user.getRotationVec(1.0f);
            user.addVelocity(look.x * 1.8, 0.15, look.z * 1.8);
            user.velocityModified = true;

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                        ParticleTypes.PORTAL,
                        user.getX(), user.getBodyY(0.5), user.getZ(),
                        30, 0.4, 0.5, 0.4, 0.05
                );
                serverWorld.playSound(
                        null, user.getBlockPos(),
                        SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                        SoundCategory.PLAYERS, 0.7f, 1.4f
                );
            }

            user.getItemCooldownManager().set(stack, DASH_COOLDOWN_TICKS);
        }

        return ActionResult.SUCCESS;
    }
}

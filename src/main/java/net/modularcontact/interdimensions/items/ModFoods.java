package net.modularcontact.interdimensions.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder()
            .nutrition(2) // Restores 2 hunger points (1 full drumstick)
            .fast() // Can be eaten quickly
            .saturationMod(0.3f) // Provides 30% saturation
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200), 1f)
            .build();
}

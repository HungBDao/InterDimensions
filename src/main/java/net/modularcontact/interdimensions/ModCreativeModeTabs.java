package net.modularcontact.interdimensions;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.modularcontact.interdimensions.blocks.ModBlocks;
import net.modularcontact.interdimensions.items.ModItems;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InterDimensions.MOD_ID);

    public static final RegistryObject<CreativeModeTab> INTERDIMENSIONS_INGREDIENTS_TAB = CREATIVE_MODE_TABS.register(
            "interdimensions_ingredients_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.interdimensions_ingredients_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SAPPHIRE.get());
                        pOutput.accept(ModItems.RUBY.get());

                        pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                    })
                    .icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

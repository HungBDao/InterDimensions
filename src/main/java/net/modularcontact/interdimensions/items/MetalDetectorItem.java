package net.modularcontact.interdimensions.items;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            BlockPos clickPosition = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean isBlockFound = false;

            for (int i = 0; i <= clickPosition.getY() + 64; i++) {
                BlockState blockState = pContext.getLevel().getBlockState(clickPosition.below(i));
                if (isValuableBlock(blockState)) {
                    outputValuableCoordinates(clickPosition.below(i), player, blockState.getBlock());
                    isBlockFound = true;
                    break;
                }
            }

            if (!isBlockFound) {
                player.sendSystemMessage(Component.literal("No valuable blocks found within 64 blocks below."));
            }
        }
        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Valuable block found: " + I18n.get(block.getDescriptionId()) + " at " + blockPos.toShortString()));
    }

    private boolean isValuableBlock(BlockState blockState) {
        return blockState.is(Blocks.IRON_ORE) ||
                blockState.is(Blocks.GOLD_ORE) ||
                blockState.is(Blocks.DIAMOND_ORE) ||
                blockState.is(Blocks.EMERALD_ORE) ||
                blockState.is(Blocks.NETHER_GOLD_ORE) ||
                blockState.is(Blocks.ANCIENT_DEBRIS);
    }
}

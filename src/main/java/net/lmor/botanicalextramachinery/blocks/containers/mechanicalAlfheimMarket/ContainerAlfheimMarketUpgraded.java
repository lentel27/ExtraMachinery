package net.lmor.botanicalextramachinery.blocks.containers.mechanicalAlfheimMarket;

import de.melanx.botanicalmachinery.helper.UnrestrictedOutputSlot;
import io.github.noeppi_noeppi.libx.menu.BlockEntityMenu;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketUpgraded;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

public class ContainerAlfheimMarketUpgraded extends BlockEntityMenu<BlockEntityAlfheimMarketUpgraded> {
    public ContainerAlfheimMarketUpgraded(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 6, 12);
        IItemHandlerModifiable inventory = (this.blockEntity).getInventory();

        int index = this.addSlotBox(inventory, 0, 41, 18,2, 18, 3, 18);
        this.addSlotBox(inventory, index, 109, 18, 2, 18, 3, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(12, 95);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        if (level != null && level.getBlockState(pos).isAir()) {
            player.closeContainer();
            return false;
        }
        return super.stillValid(player);
    }
}

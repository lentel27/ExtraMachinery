package net.lmor.botanicalextramachinery.blocks.containers;

import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.BlockEntityGreenhouse;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerGreenhouse extends BlockEntityMenu<BlockEntityGreenhouse> {

    private final static int FLOWER_SIZE = 7;
    private final static int FUEL_SIZE = 21;
    private final static int UPGRADE_SIZE = 8;
    private final static int INVENTORY_SLOT = FLOWER_SIZE + FUEL_SIZE + UPGRADE_SIZE;

    public final static int WIDTH_GUI = 216;
    public final static int HEIGHT_GUI = 136;


    public ContainerGreenhouse(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, INVENTORY_SLOT, INVENTORY_SLOT);

        this.addSlotBox(this.getBlockEntity().getFlowerInventory(), 0, 46, 23, 7, 18, 1, 18);
        this.addSlotBox(this.getBlockEntity().getInventory(), 0, 46, 45, 7, 18, 3, 18);

        for (int i = 0; i < 4; i++){
            this.addSlot(new SlotItemHandler(this.getBlockEntity().getUpgradeInventory(), i, 22, 34 + i * 20));
            this.addSlot(new SlotItemHandler(this.getBlockEntity().getUpgradeInventory(), i + 4, 178, 34 + i * 20));
        }

        int[] x_y = ScreenAddInventory.getCoordInventorySlot(ScreenInventory.ULTIMATE, WIDTH_GUI, HEIGHT_GUI);
        this.layoutPlayerInventorySlots(x_y[0], x_y[1]);
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

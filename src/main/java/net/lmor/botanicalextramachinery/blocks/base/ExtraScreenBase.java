package net.lmor.botanicalextramachinery.blocks.base;

import com.mojang.blaze3d.systems.RenderSystem;
import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.BlockEntityGreenhouse;
import net.lmor.botanicalextramachinery.gui.*;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.moddingx.libx.menu.BlockEntityMenu;


public abstract class ExtraScreenBase<X extends BlockEntityMenu<?>> extends AbstractContainerScreen<X> {
    public ExtraScreenBase(X menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (width - this.imageWidth) / 2;
        this.topPos = (height - this.imageHeight) / 2;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;
    }

    public void render(@Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    public void drawDefaultGuiBackgroundLayer(PoseStack poseStack, ResourceLocation screenLocation,
                                              ScreenAddInventory addInventory, int[] storageAll, int[] maxStorageAll,
                                              Bars bars, SlotInfo slotInfo) {
        if (bars != null){
            bars.setGuiCoord(this.leftPos, this.topPos);
        }
        if (slotInfo != null){
            slotInfo.setGuiCoord(this.leftPos, this.topPos);
        }

        this.topPos = (this.height - addInventory.getHeightGuiMax(this.imageHeight)) / 2;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        RenderSystem.setShaderTexture(0, screenLocation);
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);


        RenderSystem.setShaderTexture(0, addInventory.getInventoryScreen().getResourceLocation());
        this.blit(poseStack,
                addInventory.getXBlitScreen(this.leftPos, addInventory.getWidthGuiMax(this.imageWidth)),
                addInventory.getYBlitScreen(this.topPos, addInventory.getHeightGuiMax(this.imageHeight)),
                0, 0, addInventory.getInventoryScreen().getWidth(), addInventory.getInventoryScreen().getHeight());

        if (storageAll.length != 0 && maxStorageAll.length != 0){
            bars.draw(poseStack, storageAll, maxStorageAll, checkUpgradeInfinityMana(slotInfo));
        }
    }

    public boolean checkUpgradeInfinityMana(SlotInfo slotInfo){
        if (slotInfo == null) return false;
        BlockEntity blockEntity = ((BlockEntityMenu<?>)this.menu).getBlockEntity();

        assert blockEntity != null;
        if (blockEntity instanceof ExtraBotanicalTile && !(blockEntity instanceof BlockEntityGreenhouse)) {
            for (Integer slot: slotInfo.getSlotUpgrade()){
                if (((ExtraBotanicalTile) blockEntity).getInventory().getStackInSlot(slot).getItem() == ModItems.catalystManaInfinity ||
                        ((ExtraBotanicalTile) blockEntity).getInventory().getStackInSlot(slot).getItem() == ModItems.catalystWaterInfinity){
                    return true;
                }
            }
        }
        return false;
    }
}

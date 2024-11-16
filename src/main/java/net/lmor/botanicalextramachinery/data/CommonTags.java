package net.lmor.botanicalextramachinery.data;

import io.github.noeppi_noeppi.libx.annotation.data.Datagen;
import io.github.noeppi_noeppi.libx.data.provider.CommonTagsProviderBase;
import io.github.noeppi_noeppi.libx.mod.ModX;
import net.lmor.botanicalextramachinery.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

@Datagen
public class CommonTags extends CommonTagsProviderBase {
    public CommonTags(ModX mod, DataGenerator generator, ExistingFileHelper fileHelper) {
        super(mod, generator, fileHelper);
    }

    public void setup() {
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.malachiteDragonstoneBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.saffronDragonstoneBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.shadowDragonstoneBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.crimsonDragonstoneBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.crystalDragonstoneBlock);

        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.malachiteIngotBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.saffronIngotBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.shadowIngotBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.crimsonIngotBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.crystalIngotBlock);

        this.block(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.malachiteDragonstoneBlock);
        this.block(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.saffronDragonstoneBlock);
        this.block(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.crystalDragonstoneBlock);
        this.block(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.malachiteIngotBlock);
        this.block(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.saffronIngotBlock);
        this.block(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.crystalIngotBlock);

        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.shadowDragonstoneBlock);
        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.crimsonDragonstoneBlock);
        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.shadowIngotBlock);
        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.crimsonIngotBlock);
    }
}

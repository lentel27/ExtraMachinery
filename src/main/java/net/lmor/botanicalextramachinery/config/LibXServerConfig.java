package net.lmor.botanicalextramachinery.config;

import io.github.noeppi_noeppi.libx.annotation.config.RegisterConfig;
import io.github.noeppi_noeppi.libx.config.Config;
import io.github.noeppi_noeppi.libx.config.Group;
import io.github.noeppi_noeppi.libx.config.validator.IntRange;

@RegisterConfig("server")
public class LibXServerConfig {
    public LibXServerConfig() {
    }


    @Group({"Mana pool settings for all types"})
    public static class ManaPoolSettings {

        public static class baseManaPool {
            @Config({"Crafting time in ticks"})
            @IntRange( min = 1 )
            public static int craftTime = 10;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 2500000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseManaPool(){}
        }

        public static class upgradedManaPool {
            @Config({"Crafting time in ticks"})
            @IntRange( min = 1 )
            public static int craftTime = 10;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 10000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedManaPool(){}
        }

        public static class advancedManaPool {
            @Config({"Crafting time in ticks"})
            @IntRange( min = 1 )
            public static int craftTime = 10;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 50000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedManaPool(){}
        }

        public static class ultimateManaPool {
            @Config({"Crafting time in ticks"})
            @IntRange( min = 1 )
            public static int craftTime = 10;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 100000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateManaPool(){}
        }
        public ManaPoolSettings() {}
    }

    @Group({"Runic Altar for all types"})
    public static class RunicAltarSettings {

        @Config({"Mana cost per craft tick. Total speed is calculated as manaCost * craftTime"})
        @IntRange( min = 1 )
        public static int manaCost = 100;

        public static class baseRunicAltar {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 2500000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseRunicAltar(){}
        }

        public static class upgradedRunicAltar {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 10000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedRunicAltar(){}
        }

        public static class advancedRunicAltar {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 50000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedRunicAltar(){}
        }

        public static class ultimateRunicAltar {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 100000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateRunicAltar(){}
        }
        public RunicAltarSettings() {}
    }

    @Group({"Daisy for all types"})
    public static class DaisySettings {

        @Config({"How many ticks until the next update"})
        @IntRange( min = 1 )
        public static int ticksToNextUpdate = 5;

        public static class baseDaisy {
            @Config({"Crafting duration. This number is multiplied by the crafting time in the recipe."})
            public static int durationTime = 1;
            @Config({"How much of an item can be stored in a slot. Affects how many items can be made at once."})
            @IntRange( min = 1, max = 64)
            public static int sizeItemSlots = 4;

            public baseDaisy(){}
        }

        public static class upgradedDaisy {
            @Config({"Crafting duration. This number is multiplied by the crafting time in the recipe."})
            public static int durationTime = 1;
            @Config({"How much of an item can be stored in a slot. Affects how many items can be made at once."})
            @IntRange( min = 1, max = 64)
            public static int sizeItemSlots = 8;
            public upgradedDaisy(){}
        }

        public static class advancedDaisy {
            @Config({"Crafting duration. This number is multiplied by the crafting time in the recipe."})
            public static int durationTime = 1;
            @Config({"How much of an item can be stored in a slot. Affects how many items can be made at once."})
            @IntRange( min = 1, max = 64)
            public static int sizeItemSlots = 16;
            public advancedDaisy(){}
        }

        public static class ultimateDaisy {
            @Config({"Crafting duration. This number is multiplied by the crafting time in the recipe."})
            public static int durationTime = 1;
            @Config({"How much of an item can be stored in a slot. Affects how many items can be made at once."})
            @IntRange( min = 1, max = 64)
            public static int sizeItemSlots = 32;
            public ultimateDaisy(){}
        }
        public DaisySettings() {}
    }

    @Group({"Apothecary for all types"})
    public static class ApothecarySettings {

        @Config({"Speed of crafting. Total speed is calculated as workingDuration * craftTime"})
        @IntRange( min = 1 )
        public static int workingDuration = 20;

        public static class baseApothecary {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Water storage"})
            @IntRange( min = 1000 )
            public static int fluidStorage = 16000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseApothecary(){}
        }

        public static class upgradedApothecary {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Water storage"})
            @IntRange( min = 1000 )
            public static int fluidStorage = 32000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedApothecary(){}
        }

        public static class advancedApothecary {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Water storage"})
            @IntRange( min = 1000 )
            public static int fluidStorage = 64000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedApothecary(){}
        }

        public static class ultimateApothecary {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Water storage"})
            @IntRange( min = 1000 )
            public static int fluidStorage = 128000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateApothecary(){}
        }
        public ApothecarySettings() {}
    }

    @Group({"Industrial Agglomeration Factory for all types"})
    public static class IndustrialAgglomerationFactorySettings {

        @Config({"Maximum mana output per recipe per tick. Total speed is calculated as workingDuration * craftTime"})
        @IntRange( min = 1 )
        public static int workingDuration = 5000;

        public static class baseAgglomeration {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 2500000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseAgglomeration(){}
        }

        public static class upgradedAgglomeration {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 10000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedAgglomeration(){}
        }

        public static class advancedAgglomeration {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1000 )
            public static int manaStorage = 50000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedAgglomeration(){}
        }

        public static class ultimateAgglomeration {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1000 )
            public static int manaStorage = 100000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateAgglomeration(){}
        }
        public IndustrialAgglomerationFactorySettings() {}
    }

    @Group({"Alfheim Market for all types"})
    public static class AlfheimMarketSettings {

        @Config({"Maximum mana output per recipe per tick. Total speed is calculated as workingDuration * craftTime"})
        @IntRange( min = 1 )
        public static int workingDuration = 25;

        @Config({"How much mana does it take to create."})
        @IntRange( min = 1 )
        public static int recipeCost = 500;

        public static class baseAlfheimMarket {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 2500000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseAlfheimMarket(){}
        }

        public static class upgradedAlfheimMarket {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 10000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedAlfheimMarket(){}
        }

        public static class advancedAlfheimMarket {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1000 )
            public static int manaStorage = 50000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedAlfheimMarket(){}
        }

        public static class ultimateAlfheimMarket {
            @Config({"Crafting speed increase factor"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Mana Storage"})
            @IntRange( min = 1000 )
            public static int manaStorage = 100000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateAlfheimMarket(){}
        }
        public AlfheimMarketSettings() {}
    }

    @Group({"Orechid for all types"})
    public static class OrechidSettings {
        @Config({"How much mana is required to create 1 ore."})
        @IntRange( min = 1 )
        public static int recipeCost = 10000;

        public static class baseOrechid {
            @Config({"'Rest' time after crafting. In ticks"})
            @IntRange( min = 1 )
            public static int cooldown = 10;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 2500000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseOrechid(){}
        }

        public static class upgradedOrechid {
            @Config({"'Rest' time after crafting. In ticks"})
            @IntRange( min = 1 )
            public static int cooldown = 10;
            @Config({"Mana Storage"})
            @IntRange( min = 1 )
            public static int manaStorage = 10000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedOrechid(){}
        }

        public static class advancedOrechid {
            @Config({"'Rest' time after crafting. In ticks"})
            @IntRange( min = 1 )
            public static int cooldown = 10;
            @Config({"Mana Storage"})
            @IntRange( min = 1000 )
            public static int manaStorage = 50000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedOrechid(){}
        }

        public static class ultimateOrechid {
            @Config({"'Rest' time after crafting. In ticks"})
            @IntRange( min = 1 )
            public static int cooldown = 10;
            @Config({"Mana Storage"})
            @IntRange( min = 1000 )
            public static int manaStorage = 100000000;
            @Config({"Max amount of crafting at a time"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateOrechid(){}
        }

        public OrechidSettings() {}
    }

    @Config({"How long will it take for a request to send resources from output slots to ME? (In ticks)"})
    @IntRange( min = 1 )
    public static int tickOutputSlots = 20;


}


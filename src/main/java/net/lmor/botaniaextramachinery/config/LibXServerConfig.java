package net.lmor.botaniaextramachinery.config;

import org.moddingx.libx.annotation.config.RegisterConfig;
import org.moddingx.libx.config.Config;
import org.moddingx.libx.config.Group;
import org.moddingx.libx.config.validate.IntRange;

import java.util.ArrayList;
import java.util.List;

@RegisterConfig("server")
public class LibXServerConfig {
    public LibXServerConfig() {
    }


    @Group({"Настройки бассейна маны всех типов"})
    public static class ManaPoolSettings {

        @Group({"Базовый бассейн маны"})
        public static class baseManaPool {
            @Config({"Время крафта в тиках"})
            @IntRange( min = 1 )
            public static int craftTime = 10;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 2500000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseManaPool(){}
        }

        @Group({"Улучшенный бассейн маны"})
        public static class upgradedManaPool {
            @Config({"Время крафта в тиках"})
            @IntRange( min = 1 )
            public static int craftTime = 10;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 10000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedManaPool(){}
        }

        @Group({"Продвинутый бассейн маны"})
        public static class advancedManaPool {
            @Config({"Время крафта в тиках"})
            @IntRange( min = 1 )
            public static int craftTime = 10;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 50000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedManaPool(){}
        }

        @Group({"Совершенный бассейн маны"})
        public static class ultimateManaPool {
            @Config({"Время крафта в тиках"})
            @IntRange( min = 1 )
            public static int craftTime = 10;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 100000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateManaPool(){}
        }
        public ManaPoolSettings() {}
    }

    @Group({"Настройки рунического алтаря всех типов"})
    public static class RunicAltarSettings {

        @Config({"Цена маны за тик крафта. Общая скорость считается как manaCost * craftTime"})
        @IntRange( min = 1 )
        public static int manaCost = 100;

        @Group({"Базовый рунический алтарь"})
        public static class baseRunicAltar {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 2500000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseRunicAltar(){}
        }

        @Group({"Улучшенный рунический алтарь"})
        public static class upgradedRunicAltar {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 10000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedRunicAltar(){}
        }

        @Group({"Продвинутый рунический алтарь"})
        public static class advancedRunicAltar {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 50000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedRunicAltar(){}
        }

        @Group({"Совершенный рунический алтарь"})
        public static class ultimateRunicAltar {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 100000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateRunicAltar(){}
        }
        public RunicAltarSettings() {}
    }

    @Group({"Настройки чистой маргаритки всех типов"})
    public static class DaisySettings {

        @Config({"Через сколько тиков будет следующее обновление"})
        @IntRange( min = 1 )
        public static int ticksToNextUpdate = 5;

        @Group({"Базовая чистая маргаритка"})
        public static class baseDaisy {
            @Config({"Длительность крафта. Данное число умножается на время изготовления в рецепте"})
            public static int durationTime = 1;
            @Config({"Сколько количества предмета может храниться в слоту. Влияет на то, сколько предметов сделается за раз"})
            @IntRange( min = 1, max = 64)
            public static int sizeItemSlots = 4;

            public baseDaisy(){}
        }

        @Group({"Улучшенная чистая маргаритка"})
        public static class upgradedDaisy {
            @Config({"Длительность крафта. Данное число умножается на время изготовления в рецепте"})
            public static int durationTime = 1;
            @Config({"Сколько количества предмета может храниться в слоту. Влияет на то, сколько предметов сделается за раз"})
            @IntRange( min = 1, max = 64)
            public static int sizeItemSlots = 8;
            public upgradedDaisy(){}
        }

        @Group({"Продвинутая чистая маргаритка"})
        public static class advancedDaisy {
            @Config({"Длительность крафта. Данное число умножается на время изготовления в рецепте"})
            public static int durationTime = 1;
            @Config({"Сколько количества предмета может храниться в слоту. Влияет на то, сколько предметов сделается за раз"})
            @IntRange( min = 1, max = 64)
            public static int sizeItemSlots = 16;
            public advancedDaisy(){}
        }

        @Group({"Совершенная чистая маргаритка"})
        public static class ultimateDaisy {
            @Config({"Длительность крафта. Данное число умножается на время изготовления в рецепте"})
            public static int durationTime = 1;
            @Config({"Сколько количества предмета может храниться в слоту. Влияет на то, сколько предметов сделается за раз"})
            @IntRange( min = 1, max = 64)
            public static int sizeItemSlots = 32;
            public ultimateDaisy(){}
        }
        public DaisySettings() {}
    }

    @Group({"Настройки лепесткового аптекаря всех типов"})
    public static class ApothecarySettings {

        @Config({"Скорость выполнения крафта. Общая скорость считается как workingDuration * craftTime"})
        @IntRange( min = 1 )
        public static int workingDuration = 20;

        @Group({"Базовый рунический алтарь"})
        public static class baseApothecary {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище воды"})
            @IntRange( min = 1000 )
            public static int fluidStorage = 16000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseApothecary(){}
        }

        @Group({"Улучшенный рунический алтарь"})
        public static class upgradedApothecary {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище воды"})
            @IntRange( min = 1000 )
            public static int fluidStorage = 32000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedApothecary(){}
        }

        @Group({"Продвинутый рунический алтарь"})
        public static class advancedApothecary {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище воды"})
            @IntRange( min = 1000 )
            public static int fluidStorage = 64000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedApothecary(){}
        }

        @Group({"Совершенный рунический алтарь"})
        public static class ultimateApothecary {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище воды"})
            @IntRange( min = 1000 )
            public static int fluidStorage = 128000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateApothecary(){}
        }
        public ApothecarySettings() {}
    }

    @Group({"Настройки завода промышленной агломерации всех типов"})
    public static class IndustrialAgglomerationFactorySettings {

        @Config({"Максимальный выход маны на рецепт в тик. Общая скорость считается как workingDuration * craftTime"})
        @IntRange( min = 1 )
        public static int workingDuration = 5000;

        @Group({"Базовый рунический алтарь"})
        public static class baseAgglomeration {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 2500000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseAgglomeration(){}
        }

        @Group({"Улучшенный рунический алтарь"})
        public static class upgradedAgglomeration {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 10000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedAgglomeration(){}
        }

        @Group({"Продвинутый рунический алтарь"})
        public static class advancedAgglomeration {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1000 )
            public static int manaStorage = 50000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedAgglomeration(){}
        }

        @Group({"Совершенный рунический алтарь"})
        public static class ultimateAgglomeration {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1000 )
            public static int manaStorage = 100000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateAgglomeration(){}
        }
        public IndustrialAgglomerationFactorySettings() {}
    }

    @Group({"Настройки обменника альфхейма всех типов"})
    public static class AlfheimMarketSettings {

        @Config({"Максимальный выход маны на рецепт в тик. Общая скорость считается как workingDuration * craftTime"})
        @IntRange( min = 1 )
        public static int workingDuration = 25;

        @Config({"Сколько маны требуется для создания."})
        @IntRange( min = 1 )
        public static int recipeCost = 500;

        @Group({"Базовый обменник альфхейма"})
        public static class baseAlfheimMarket {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 2500000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseAlfheimMarket(){}
        }

        @Group({"Улучшенный обменник альфхейма"})
        public static class upgradedAlfheimMarket {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 10000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedAlfheimMarket(){}
        }

        @Group({"Продвинутый обменник альфхейма"})
        public static class advancedAlfheimMarket {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1000 )
            public static int manaStorage = 50000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedAlfheimMarket(){}
        }

        @Group({"Совершенный обменник альфхейма"})
        public static class ultimateAlfheimMarket {
            @Config({"Коэффициент увеличения скорости крафта"})
            @IntRange( min = 1 )
            public static int craftTime = 1;
            @Config({"Хранилище маны"})
            @IntRange( min = 1000 )
            public static int manaStorage = 100000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateAlfheimMarket(){}
        }
        public AlfheimMarketSettings() {}
    }

    @Group({"Настройки рудноцвета всех типов"})
    public static class OrechidSettings {
        @Config({"Сколько маны требуется для создания на 1 руду."})
        @IntRange( min = 1 )
        public static int recipeCost = 10000;

        @Group({"Базовый обменник альфхейма"})
        public static class baseOrechid {
            @Config({"Время 'отдыха' после крафта. В тиках"})
            @IntRange( min = 1 )
            public static int cooldown = 10;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 2500000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 4;
            public baseOrechid(){}
        }

        @Group({"Улучшенный обменник альфхейма"})
        public static class upgradedOrechid {
            @Config({"\"Время 'отдыха' после крафта. В тиках"})
            @IntRange( min = 1 )
            public static int cooldown = 10;
            @Config({"Хранилище маны"})
            @IntRange( min = 1 )
            public static int manaStorage = 10000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 8;
            public upgradedOrechid(){}
        }

        @Group({"Продвинутый обменник альфхейма"})
        public static class advancedOrechid {
            @Config({"\"Время 'отдыха' после крафта. В тиках"})
            @IntRange( min = 1 )
            public static int cooldown = 10;
            @Config({"Хранилище маны"})
            @IntRange( min = 1000 )
            public static int manaStorage = 50000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 16;
            public advancedOrechid(){}
        }

        @Group({"Совершенный обменник альфхейма"})
        public static class ultimateOrechid {
            @Config({"\"Время 'отдыха' после крафта. В тиках"})
            @IntRange( min = 1 )
            public static int cooldown = 10;
            @Config({"Хранилище маны"})
            @IntRange( min = 1000 )
            public static int manaStorage = 100000000;
            @Config({"Макс. количество крафта за раз"})
            @IntRange( min = 1, max = 64)
            public static int countCraft = 32;
            public ultimateOrechid(){}
        }

        public OrechidSettings() {}
    }

    @Config({"Через сколько будет сделан запрос на отправу ресурсов из выходных слотов в мэ? (В тиках)"})
    @IntRange( min = 1 )
    public static int tickOutputSlots = 20;


}


package net.lmor.botaniaextramachinery.config;

import org.moddingx.libx.annotation.config.RegisterConfig;
import org.moddingx.libx.config.Config;
import org.moddingx.libx.config.Group;

@RegisterConfig(value = "client", client = true)
public class LibXClientConfig {
    @Config({"Показывать ли ману/воду числом, при наведение на её ёмкость?"})
    public static boolean numericalFluid = true;
    @Config({"Форматировать число через суффиксы (К, М и т.д)? Если false, то будет показываться полностью число."})
    public static boolean formattedNumberSuffix = false;

    @Config({"Показывать ли информацию о дополнительных слотах? Например слот, в который кладется катализатор в бассейне маны."})
    public static boolean slotInfo = true;

    @Group({"Настройки рендринга механизмов."})
    public static class RenderingVisualContent {
        @Config({"Если вы отключите этот параметр, специальный рендеринг будет отключен для всех машин и будет игнорировать другие параметры конфигурации."})
        public static boolean all = true;

        @Group({"Настройки рендринга бассейнов маны."})
        public static class ManaPoolSettings {
            @Config
            public static boolean manaPoolBase = true;
            @Config
            public static boolean manaPoolUpgraded = true;
            @Config
            public static boolean manaPoolAdvanced = true;
            @Config
            public static boolean manaPoolUltimate = true;

            public ManaPoolSettings(){}
        }

        @Group({"Настройки рендринга рунических алтарей."})
        public static class RunicAltarSettings {
            @Config
            public static boolean runicAltarBase = true;
            @Config
            public static boolean runicAltarUpgraded = true;
            @Config
            public static boolean runicAltarAdvanced = true;
            @Config
            public static boolean runicAltarUltimate = true;

            public RunicAltarSettings(){}
        }

        @Group({"Настройки рендринга чистой маргаритки."})
        public static class DaisySettings {
            @Config
            public static boolean daisyBase = true;
            @Config
            public static boolean daisyUpgraded = true;
            @Config
            public static boolean daisyAdvanced = true;
            @Config
            public static boolean daisyUltimate = true;

            public DaisySettings(){}
        }

        @Group({"Настройки рендринга аптекаря."})
        public static class ApothecarySettings {
            @Config
            public static boolean apothecaryBase = true;
            @Config
            public static boolean apothecaryUpgraded = true;
            @Config
            public static boolean apothecaryAdvanced = true;
            @Config
            public static boolean apothecaryUltimate = true;

            public ApothecarySettings(){}
        }

        @Group({"Настройки рендринга обменника альфхейма."})
        public static class AlfheimMarketSettings {
            @Config
            public static boolean alfheimMarketBase = true;
            @Config
            public static boolean alfheimMarketUpgraded = true;
            @Config
            public static boolean alfheimMarketAdvanced = true;
            @Config
            public static boolean alfheimMarketUltimate = true;

            public AlfheimMarketSettings(){}
        }

        public RenderingVisualContent() {
        }
    }

    public LibXClientConfig() {
    }
}

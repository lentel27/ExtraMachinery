package net.lmor.botanicalextramachinery.util;

import appbot.ae2.ManaKey;
import appeng.api.config.Actionable;
import appeng.api.networking.IGrid;
import appeng.api.networking.security.IActionSource;

public class ExportManaME {

    public int exportManaME(int currentMana, IGrid node){
        if (currentMana <= 0) return 0;

        long export = node.getStorageService().getInventory().insert(ManaKey.KEY, currentMana, Actionable.MODULATE, IActionSource.empty());
        return -(int)export;
    }
}

package net.lmor.botanicalextramachinery.util;

import java.util.HashMap;
import java.util.Map;

public class SettingPattern {

    private Map<String, String> config = new HashMap<>();

    public SettingPattern(){}

    public SettingPattern addConfig(String key, String value){
        config.put(key, value);
        return this;
    }

    public Integer getConfigInt(String key){
        try {
            return Integer.parseInt(config.get(key));
        }
        catch (NumberFormatException error){
            return 1;
        }
    }

    public Boolean getConfigBoolean(String key){
        try {
            return Boolean.parseBoolean(config.get(key));
        }
        catch (NumberFormatException error){
            return false;
        }
    }
    public String getConfigString(String key){
        return config.get(key);
    }

}

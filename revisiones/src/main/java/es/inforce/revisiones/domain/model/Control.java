package es.inforce.revisiones.domain.model;

import java.util.HashMap;

/**
 * Created by david on 2/9/2017.
 */

public class Control {

    private HashMap<String, String> values;

    public HashMap<String, String> getValues() {
        return values;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;
    }

    public String getValue(String key){
        if (values.containsKey(key)){
            return values.get(key);
        }
        return "";
    }

    public boolean setValue(String key, String value){
        try{
            values.put(key, value);
            return true;
        }catch (final Exception e){
            return false;
        }
    }
}

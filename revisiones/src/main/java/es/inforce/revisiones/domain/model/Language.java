package es.inforce.revisiones.domain.model;

import java.util.HashMap;

/**
 * Created by jossue on 06/02/2017.
 */
public class Language {

    private HashMap<String, String> texts;


    private String value;
    private String caption;

    public Language() {

    }

    public HashMap<String, String> getTexts() {
        return texts;
    }

    public void setTexts(HashMap<String, String> texts) {
        this.texts = texts;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }


}

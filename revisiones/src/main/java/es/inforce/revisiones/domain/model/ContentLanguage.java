package es.inforce.revisiones.domain.model;

import java.util.List;

/**
 * Created by jossue on 06/02/2017.
 */
public class ContentLanguage {
    List<Language> languageList;

    public ContentLanguage() {

    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }
}

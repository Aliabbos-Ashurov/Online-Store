package backend.model;

import backend.utils.Utils;
/**
 * @author Aliabbos Ashurov
 * Date: 23/February/2024  18:11
 **/
public abstract class BaseModel {
    private String id;

    public BaseModel() {
        this.id = Utils.generateUUID();
    }
    public String getId() {
        return id;
    }
}




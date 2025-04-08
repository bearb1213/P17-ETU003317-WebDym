package Util;

import java.sql.Connection;
import java.util.List;

public abstract class BaseObject {
    protected int id ;
    public BaseObject(){
    }
    public BaseObject(int id){
        this.id = id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    
    public abstract void save()throws Exception;
    public abstract void save(Connection co)throws Exception;
    public abstract void findById() throws Exception;
    public abstract void findById(Connection co) throws Exception;
    public abstract List<BaseObject> getAll()throws Exception;
    public abstract List<BaseObject> getAll(Connection co)throws Exception;
    public abstract void update()throws Exception ;
    public abstract void update(Connection co)throws Exception ;
    public abstract void delete()throws Exception ;
    public abstract void delete(Connection co)throws Exception ;
} 
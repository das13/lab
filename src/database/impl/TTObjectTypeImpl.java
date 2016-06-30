package database.impl;

import database.DBWorker;
import database.interfaces.TTObjectTypeInterface;
import database.entities.TTObjectType;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Vlad on 24.06.2016.
 */
@LocalBean
@Stateless(name = "TTObjectTypeLocalSessionEJB")
public class TTObjectTypeImpl implements TTObjectTypeInterface{

    @PersistenceContext(unitName = "objects")
    private EntityManager manager;

    @Override
    public long create(TTObjectType objectType) {
        DBWorker dbWorker = new DBWorker();
        long id = dbWorker.getId();
        objectType.setObjectTypeId(id);
        dbWorker.close();
        manager.persist(objectType);
        return id;
    }

    @Override
    public void update(TTObjectType objectType) {
        manager.merge(objectType);
    }

    @Override
    public void delete(long id) {
        TTObjectType objectType = manager.find(TTObjectType.class, id);
        manager.remove(objectType);
    }
}
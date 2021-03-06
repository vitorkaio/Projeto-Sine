package trabalho.sine.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import trabalho.sine.persistence.DatabaseHelper;

/**
 * Created by wagner on 09/12/16.
 */

public class GenericDAO<E> extends DatabaseHelper<E> {
    protected Dao<E, Long> dao;
    private Class<E> type;

    public GenericDAO(Context context, Class<E> type) {
        super(context);
        this.type = type;
        setDao();
    }

    protected void setDao(){
        try {
            dao = DaoManager.createDao(getConnectionSource(), type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<E> getAll(){
        try {
            List<E> list = dao.queryForAll();
            return list;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public E getById(Long id) {
        try {
            E obj = dao.queryForId(id);
            return obj;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void insert(E obj) {
        try {
            dao.create(obj);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(E obj){
        try {
            dao.delete(obj);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(E obj) {
        try {
            dao.update(obj);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<E> getAllOrderBy(String campo){
        try {
            QueryBuilder<E,Long> qb = dao.queryBuilder();
            qb.orderBy(campo,false);
            List<E> list = qb.query();
            return list;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}

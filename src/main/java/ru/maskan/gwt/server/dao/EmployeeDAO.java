package ru.maskan.gwt.server.dao;

import org.springframework.stereotype.Repository;
import ru.maskan.gwt.server.domain.HbmEmployee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by akonshina on 27.05.14.
 */

@Repository
public class EmployeeDAO {

    private final static String SEARCH_SQL = "SELECT * FROM employee e WHERE MATCH (description,experience,firstname,lastname,secondname) AGAINST (:criteria IN BOOLEAN MODE)";

    @PersistenceContext(unitName = "MyPUnit")
    EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public void persist(HbmEmployee entity) {
        getEntityManager().persist(entity);
    }

    public void remove(HbmEmployee entity) {
        getEntityManager().remove(entity);
    }

    public void refresh(HbmEmployee entity) {
        getEntityManager().refresh(entity);
    }

    public HbmEmployee merge(HbmEmployee entity) {
        return getEntityManager().merge(entity);
    }

    @SuppressWarnings("unchecked")
    public List<HbmEmployee> findAll() {
        String queryStr = "SELECT h FROM HbmEmployee h";
        Query query = getEntityManager().createQuery(queryStr, HbmEmployee.class);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<HbmEmployee> search(String str) {

        Query query = getEntityManager().createNativeQuery(SEARCH_SQL, HbmEmployee.class).setParameter("criteria", str);
        return query.getResultList();
    }
}

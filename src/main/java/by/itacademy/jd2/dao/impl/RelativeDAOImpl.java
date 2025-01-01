package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.RelativeDAO;
import by.itacademy.jd2.repository.RelativeEntity;

public class RelativeDAOImpl extends DAO<RelativeEntity> implements RelativeDAO {
    public RelativeDAOImpl() {
        super(RelativeEntity.class);
    }
}

package main.java.io.github.dramanebamba.pole_info.service;

import java.util.List;
import java.util.Vector;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import main.java.io.github.dramanebamba.pole_info.model.Master;
import main.java.io.github.dramanebamba.pole_info.utils.QueryHelper;

@RequestScoped
public class MasterService {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private QueryHelper helper;

	@Transactional
	public List<Master> getAll() {
		return em.createQuery(helper.selectAll(em.getCriteriaBuilder(), Master.class)).getResultList();
	}

	@Transactional
	public void persist(Master master) {
		em.persist(master);
	}
}
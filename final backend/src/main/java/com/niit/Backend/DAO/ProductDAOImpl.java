package com.niit.Backend.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Backend.model.Product;


@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionfactory;
public ProductDAOImpl(SessionFactory sessionfactory) {
this.sessionfactory = sessionfactory;

	}
@Transactional
public void saveOrUpdate(Product product){
sessionfactory.getCurrentSession().saveOrUpdate(product);
}
	
	
	@Transactional
	public void delete(String id) {
Product product = new Product();
		product.setId(id);
sessionfactory.getCurrentSession().delete(product);

	}

	public Product get(String id) {
		String hql = "from Product where id= " + "'" + id + "'";
		// from product id=100;
		Query query = sessionfactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Product> listproduct = (List<Product>) query.list();

		if (listproduct != null && !listproduct.isEmpty()) {
			return listproduct.get(0);

		}

		return null;
	}

	@Transactional

	public List<Product> list() {

		@SuppressWarnings("unchecked")
		List<Product> listProduct = (List<Product>)
				sessionfactory.getCurrentSession()
.createCriteria(Product.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listProduct;

	}

	
	

}

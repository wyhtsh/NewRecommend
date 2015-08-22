package org.yeahwa.news.dao.util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jfree.util.Log;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PageUtil extends HibernateDaoSupport{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> paging(final String hql, final Integer firstindex, final Integer size){
		try{
			List obj = getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session arg0)
						throws HibernateException, SQLException {
					Query query = arg0.createQuery(hql);
					query.setFirstResult(firstindex);
					query.setMaxResults(size);
					List list = query.list();
					return list;
				}});
			Log.info("find successful for "+hql);
			return obj;
		}catch(Exception ex){
			Log.error("find fail:"+ex.getMessage());
		}
		
		return null;
	}

}

package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers()
    {
        // get current hibernate session
        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery( "from Customer order by lastName", Customer.class );

        return query.getResultList();
    }

    @Override
    public void saveCustomer( Customer customer )
    {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate( customer );
    }

    @Override
    public Customer getCustomer( int id )
    {
        Session session = sessionFactory.getCurrentSession();

        return session.get( Customer.class, id );
    }

    @Override
    public void deleteCustomer( int id )
    {
        Session session = sessionFactory.getCurrentSession();

        session.createQuery( "delete from Customer where id=:customerId" )
               .setParameter( "customerId", id )
               .executeUpdate();

    }

    @Override
    public List<Customer> searchCustomers( String searchName )
    {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query;

        if( searchName != null && searchName.trim().length() > 0 )
        {
            query = session.createQuery( "from Customer  where lower(firstName) like :searchName or lower(lastName) like : searchName", Customer.class )
                    .setParameter( "searchName", "%" + searchName.toLowerCase() + "%" );
        }
        else
        {
            query = session.createQuery( "from Customer ", Customer.class );
        }
        return query.getResultList();
    }
}

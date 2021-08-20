package ru.gb.homework_05.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDao {

    private SessionFactory factory;
    private final String configure;

    public ProductDao(String configure) {
        this.configure = configure;
    }

    public void init() {
        this.factory = new Configuration()
                .configure(configure)
                .buildSessionFactory();
    }

    public void shutdown() {
        factory.close();
    }

    public Product findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Product> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select s from Product s", Product.class)
                    .getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    public void deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    /**
     * Метод создает объект если id=null или обновляет объект с id
     * переданным ему в качестве параметра.
     * Если объект с id не был найден в базе данных, то будет возвращено false
     *
     * @param id - Может быть null. Тогда создастся новый объект с автоинкрементированным id
     */
    public boolean saveOrUpdate(Long id, String title, int price) {
        if (id != null) {
            return update(id, title, price);
        } else {
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                Product product = new Product(title, price);
                session.save(product);
                session.getTransaction().commit();
                return true;
            }
        }
    }

    private boolean update(Long id, String title, int price) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product == null) {
                return false;
            }
            product.setTitle(title);
            product.setPrice(price);
            session.getTransaction().commit();
            return true;
        }
    }
}

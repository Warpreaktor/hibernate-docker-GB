package ru.gb.homework_05;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import ru.gb.homework_05.domain.ProductDao;

public class Main {

    public static SessionFactory factory;


    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/simple-app", "postgres", "postgrespass").load();
        flyway.migrate();
        ProductDao productDao = new ProductDao("config/hibernate.cfg.xml");
        try{
            productDao.init();
            //Create
            productDao.saveOrUpdate(null, "TV", 13000);
            productDao.saveOrUpdate(null, "Noutbook", 75000);
            productDao.saveOrUpdate(null, "Table", 5000);
            productDao.saveOrUpdate(null, "Something amazing", 100000);
            //Test1
            System.out.println(productDao.findById(3L));
            //Test2
            System.out.println(productDao.findAll());
            //Test3
            productDao.deleteById(2L);
            System.out.println(productDao.findAll());
            //Test4
            productDao.saveOrUpdate(3L, "BadaBoom", 999);
            System.out.println(productDao.findAll());

        }
        finally {
            productDao.shutdown();
        }
    }
}

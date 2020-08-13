package com.javatpoint.mypackage;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class StoreData {
	public static void main(String[] args) {

        Transaction transaction = null;
        try {
        	Session session = HibernateUtil.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            
            File inputFile = new File("file.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(inputFile, userhandler);    
            List<Employee> empList = userhandler.getEmpList();
    		
    		for (Employee employee : empList) {
    			session.save(employee);
    		}
            
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        try {
        	Session session = HibernateUtil.getSessionFactory().openSession();
            List <Employee> students = session.createQuery("from Employee", Employee.class).list();
            //students.forEach(s -> System.out.println(s.getFirstName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
}
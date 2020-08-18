package com.tcs.ahold.service;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.tcs.ahold.entity.Employee;
import com.tcs.ahold.util.UserHandler;

public class CreateEmployee {

	public static void main(String[] args) {

		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("JPAProj");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		try {
			File inputFile = new File("file.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			UserHandler userhandler = new UserHandler();
			saxParser.parse(inputFile, userhandler);
			List<Employee> empList = userhandler.getEmpList();
			
			for (Employee employee : empList) {
				entitymanager.persist(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entitymanager.getTransaction().commit();
			entitymanager.close();
			emfactory.close();
		}	
	}
}
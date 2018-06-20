package com.kasi.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
	public static void main(String[] args)
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyFirstJPAProject");
		EntityManager manager = factory.createEntityManager();
		//createEmployee(manager);
		//getEmployee(manager);
		//updateEmployee(manager);
		//deleteEmployee(manager);
		getEmployeeList(manager);
		manager.close();
		factory.close();
	}
	
	public static void createEmployee(EntityManager manager)
	{
		EntityTransaction trans = manager.getTransaction();
		trans.begin();
		Employee emp = new Employee(104,"Netravarnani",75000,20);
		manager.persist(emp);
		trans.commit();
		
	}
	public static void getEmployee(EntityManager manager)
	{
		Employee emp = manager.find(Employee.class, 103);
		System.out.println(emp.getEname()+" "+ emp.getSalary()+" "+emp.getDeptno());
		
	}
	public static void updateEmployee(EntityManager manager)
	{
		EntityTransaction trans = manager.getTransaction();
		trans.begin();
		Employee emp = manager.find(Employee.class, 102);
		emp.setSalary(95500);
		trans.commit();
	}
	public static void deleteEmployee(EntityManager manager)
	{
		Employee emp = manager.find(Employee.class, 101);
		if(emp!=null)
		{
			EntityTransaction trans = manager.getTransaction();
			trans.begin();
			manager.remove(emp);
			trans.commit();
		}else
			System.out.println("Employee Does not Exist");
	}
	public static void getEmployeeList(EntityManager manager)
	{
		Query query = manager.createQuery("from Employee e");
		List<Employee> elist = query.getResultList();
		for(Employee e: elist)
			System.out.println(e.getEmpid()+" "+e.getEname()+" "+e.getSalary()+" "+e.getDeptno());
	}
}

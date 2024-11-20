package com.learn.crud.HibernateCRUD;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class App 
{
	public static void main( String[] args )
	{
		Scanner scan=new Scanner(System.in);
		Configuration conn=new Configuration();
		conn.configure();
		SessionFactory factory= conn.buildSessionFactory();
		Session session= factory.openSession();
		Transaction trans= session.beginTransaction();
		
		
		while(true)
		{
			System.out.println("PRESS 1 : Creating the Student Object");
			System.out.println("PRESS 2 : Reading the Student Objects");
			System.out.println("PRESS 3 : Update the Student Object");
			System.out.println("PRESS 4 : Delete the Student Object");
			int option=scan.nextInt();
			switch (option) {
			case 1: create(session,trans,scan);
			break;
			case 2:read(session,trans,scan);
			break;
			case 3:update(session,trans,scan);
			break;
			case 4:delete(session,trans,scan);
			break;
			default:System.out.println("Enter the Valid Option");
			break;
			}
		}
	}

	public static void create(Session session,Transaction trans,Scanner scan) 
	{
		Student std=new Student();
		System.out.println("Enter Roll Number");
		Long roll=scan.nextLong();
		System.out.println("Enter Name");
		scan.nextLine();
		String name=scan.nextLine();
		System.out.println("Enter Email");
		String email=scan.nextLine();
		System.out.println("Enter Phone Number");
		Long phone=scan.nextLong();
		std.setS_roll(roll);
		std.setS_name(name);
		std.setS_email(email);
		std.setS_phone(phone);
		session.save(std);
		trans.commit();
		System.out.println("Student Object is stored Sucessfully");

	}
	public static void read(Session session,Transaction trans,Scanner scan) {
		while(true)
		{

			
			System.out.println("PRESS 1 : All Student Details");
			System.out.println("PRESS 2 : Perticular Student Details");
			System.out.println("PRESS 0 : EXit");
		
			int option=scan.nextInt();
			if(option==1)
			{
				try {
					String hql="from Student";
					Query query=session.createQuery(hql,Student.class);
					@SuppressWarnings("unchecked")
					List<Student> students=query.getResultList();
					for (Student student : students)
					{
						System.out.println("Roll Number :"+student.s_roll+"   "+"Name :"+student.s_name+"    "+"E mail : "+student.s_email);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(option==2)
			{
				try
				{
					System.out.println("Enter the Roll Number");
					Long roll=scan.nextLong();
					String hql="from Student where s_roll=:roll";
					
					Query query=session.createQuery(hql,Student.class);

					query.setParameter("roll", roll);
					Student std=(Student) query.getSingleResult();
					System.out.println("Roll Number : "+std.s_roll+"    "+"Name : "+std.s_name);
				}
				catch (Exception e) {
					System.out.println("Entered Roll Number is Wrong. Please Enter the valid Roll Number");
					e.printStackTrace();
				}
			}
			else if(option==0)
			{
				return;
			}
			else
			{
				System.out.println("Enter the valid Option");
				return;
			}
		}
	}
	public static void update(Session session,Transaction trans,Scanner scan) {

		try {

			System.out.println("Enter the Roll Number for updating the object");
			Long roll=scan.nextLong();

			String hql="from Student where s_roll=:roll";
			Query query=session.createQuery(hql,Student.class);
			query.setParameter("roll", roll);
			Student student=(Student)query.getSingleResult();

			while(true)
			{
				System.out.println("PRESS 1 : Roll Number");
				System.out.println("PRESS 2 : Name");
				System.out.println("PRESS 3 : Email ID");
				System.out.println("PRESS 4 : Phone Number");
				System.out.println("PRESS 0 : EXit");
				int option=scan.nextInt();
				switch(option)
				{
				case 1:System.out.println("Enter the New Roll Number");
				Long newroll=scan.nextLong();
				student.setS_roll(newroll);
				session.update(student);
				trans.commit();
				System.out.println("Roll Number is Updated");
				break;
				case 2:System.out.println("Enter the New Name");
				scan.nextLine();
				String newname=scan.nextLine();
				student.setS_name(newname);
				session.update(student);
				trans.commit();
				System.out.println("Name is Updated");
				break;	
				case 3:System.out.println("Enter the New Email");
				scan.nextLine();
				String newemail=scan.nextLine();
				student.setS_email(newemail);
				session.update(student);
				trans.commit();
				System.out.println("Name is Updated");
				break;
				case 4:System.out.println("Enter the Phone Number");
				Long newphone=scan.nextLong();
				student.setS_phone(newphone);
				session.update(student);
				trans.commit();
				System.out.println("Phone Number is Updated");
				break;
				case 0:return;
				default:System.out.println("Enter the valid option");
				}
			}
		}
		catch (Exception e) {
			System.out.println("Entered Roll Number is Wrong. Please Enter the valid Roll Number");
			e.printStackTrace();
		}
	}
	public static void delete(Session session,Transaction trans,Scanner scan) {
		try
		{
			System.out.println("Enter the roll number for deleting the object");
			Long roll=scan.nextLong();
			String hql="from Student where s_roll=:roll";
			Query query= session.createQuery(hql,Student.class);
			query.setParameter("roll", roll);
			Student student=(Student)query.getSingleResult();
			System.out.println(student);
			session.delete(student);
			trans.commit();
			System.out.println(student.s_name+" Object is deleted");
		}
		catch (Exception e) {
			System.out.println("Entered Roll Number is Wrong. Please Enter the valid Roll Number");
			e.printStackTrace();
		}
	}	
}

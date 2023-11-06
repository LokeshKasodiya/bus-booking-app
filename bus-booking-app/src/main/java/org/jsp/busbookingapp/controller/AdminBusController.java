package org.jsp.busbookingapp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.jsp.busbookingapp.AdminConfig;
import org.jsp.busbookingapp.dao.AdminDao;
import org.jsp.busbookingapp.dao.BusDao;
import org.jsp.busbookingapp.dto.Admin;
import org.jsp.busbookingapp.dto.Bus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AdminBusController {
	static AdminDao adao;
	static BusDao bdao;
	static Scanner sc = new Scanner(System.in);
	static ApplicationContext context;
	static {
		context = new AnnotationConfigApplicationContext(AdminConfig.class);
		adao = context.getBean(AdminDao.class);
		bdao = context.getBean(BusDao.class);
	}

	public static void main(String[] args) {
		boolean b = true;
		while (b) {
			System.out.println("1. Save Admin");
			System.out.println("2. Update Admin");
			System.out.println("3. Find admin by id");
			System.out.println("4. Add bus");
			System.out.println("5. Update Bus");
			System.out.println("6. Find bus by admin id");
			System.out.println("7. Find bus by from location to location on date");
			System.out.println("8. Verify admin by phone and password");
			System.out.println("9. Verify admin by email and password");
			System.out.println("10. Delete admin");

			switch (sc.nextInt()) {
			case 1: {
				save();
				break;
			}
			case 2: {
				update();
				break;
			}
			case 3: {
				find();
				break;
			}
			case 4: {
				add();
				break;
			}
			case 5: {
				updateBus();
				break;
			}
			case 6: {
				findByAdmin();
				break;
			}
			case 7: {
				bus();
				break;
			}
			case 8: {
				verifyByPhone();
				break;
			}
			case 9: {
				verifyByEmail();
				break;
			}
			case 10: {
				delete();
				break;
			}
			default: {
				System.out.println("Thank You!!!Application Closed");
				((AnnotationConfigApplicationContext)context).close();
				b=false;
			}
			}
		}
	}

	public static void save() {
		System.out.println("Enter Name, Phone Number, Email Id,Gst In and Password to Register");
		Admin a = new Admin();
		a.setName(sc.next());
		a.setPhone(sc.nextLong());
		a.setEmail(sc.next());
		a.setGst(sc.next());
		a.setPassword(sc.next());
		a = adao.saveAdmin(a);
		System.out.println("User registered with id " + a.getId());
	}

	public static void update() {
		System.out.println("Enter Id to update Admin");
		int id = sc.nextInt();
		System.out.println("Enter Name, Phone Number, Email Id,Gst In and Password to Update");
		Admin a = new Admin();
		a.setId(id);
		a.setName(sc.next());
		a.setPhone(sc.nextLong());
		a.setEmail(sc.next());
		a.setGst(sc.next());
		a.setPassword(sc.next());
		a = adao.updateAdmin(a);
		System.out.println("Admin updated successfully ");
	}

	public static void delete() {
		System.out.println("Enter id to delete Admin");
		int id = sc.nextInt();
		boolean e = adao.deleteAdmin(id);
		if (e) {
			System.out.println("Admin deleted successfully");
		} else {
			System.err.println("User not found with entered Id");
		}
	}

	public static void find() {
		System.out.println("Enter Id to fetch Admin");
		int id = sc.nextInt();
		Admin a = adao.findById(id);
		if (a != null) {
			System.out.println("Admin Id :" + a.getId());
			System.out.println("Admin Name :" + a.getName());
			System.out.println("Phone Number :" + a.getPhone());
			System.out.println("Email Id :" + a.getEmail());
			System.out.println("GST :" + a.getGst());
		} else
			System.err.println("Id not found");
	}

	public static void verifyByEmail() {
		System.out.println("Enter  Email Id and Password to verify Admin");
		String email = sc.next();
		String password = sc.next();
		Admin a = adao.verifyAdmin(email, password);
		if (a != null) {
			System.out.println("Admin Id :" + a.getId());
			System.out.println("Admin Name :" + a.getName());
			System.out.println(" Phone Number :" + a.getPhone());
			System.out.println("Email Id :" + a.getEmail());
			System.out.println("GST :" + a.getGst());
		} else
			System.err.println("Invalid email or password");
	}

	public static void verifyByPhone() {
		System.out.println("Enter  Phone Number  and Password to verify user");
		long phone = sc.nextLong();
		String password = sc.next();
		Admin a = adao.verifyAdmin(phone, password);
		if (a != null) {
			System.out.println("Admin Id :" + a.getId());
			System.out.println("Admin Name :" + a.getName());
			System.out.println(" Phone Number :" + a.getPhone());
			System.out.println("Email Id :" + a.getEmail());
			System.out.println("GST :" + a.getGst());
		} else
			System.err.println("Invalid phone or password");
	}

	public static void add() {
		System.out.println(
				"Enter the name, source location, destination, date and fare per person and admin id to add bus");
		Bus b = new Bus();
		b.setName(sc.next());
		b.setFrom_loc(sc.next());
		b.setTo_loc(sc.next());
		String date = sc.next();
		LocalDate dt = LocalDate.parse(date);
		b.setDate_of_departure(dt);
		b.setCost_per_seat(sc.nextDouble());
		int adminId = sc.nextInt();
		b = bdao.addBus(b, adminId);
		System.out.println("Bus added with id :" + b.getId());
	}

	public static void updateBus() {
		System.out.println("Enter id to update bus");
		int id = sc.nextInt();
		System.out.println(
				"Enter the name, source location, destination, date and fare per person and admin id to update bus");
		Bus b = new Bus();
		b.setId(id);
		b.setName(sc.next());
		b.setFrom_loc(sc.next());
		b.setTo_loc(sc.next());
		String date = sc.next();
		LocalDate dt = LocalDate.parse(date);
		b.setDate_of_departure(dt);
		b.setCost_per_seat(sc.nextDouble());
		int adminId = sc.nextInt();
		b = bdao.updateBus(b, adminId);
		System.out.println("Bus Updated successfully");
	}

	public static void findByAdmin() {
		System.out.println("Enter the Admin id to find the bus");
		int id = sc.nextInt();
		List<Bus> bus = bdao.findByAdminId(id);
		if (bus.size() > 0) {
			for (Bus b : bus) {
				System.out.println("Bus Name :" + b.getName());
				System.out.println("Source Location :" + b.getFrom_loc());
				System.out.println("Destination location :" + b.getTo_loc());
				System.out.println("Date of Departure :" + b.getDate_of_departure());
				System.out.println("Fare per Person" + b.getCost_per_seat());
				System.out.println("-------------******************-----------------");
			}
		} else {
			System.err.println("Bus not found with entered Admin Id");
		}

	}

	public static void bus() {
		System.out.println("Enter the source location, destination location and date to get buses");
		String source = sc.next();
		String dest = sc.next();
		String date = sc.next();
		LocalDate dt = LocalDate.parse(date);
		List<Bus> bus = bdao.findByInfo(source, dest, dt);
		if (bus.size() > 0) {
			for (Bus b : bus) {
				System.out.println("Bus Name :" + b.getName());
				System.out.println("Source Location :" + b.getFrom_loc());
				System.out.println("Destination location :" + b.getTo_loc());
				System.out.println("Date of Departure :" + b.getDate_of_departure());
				System.out.println("Fare per Person" + b.getCost_per_seat());
				System.out.println("-------------******************-----------------");
			}
		} else {
			System.err.println("Bus not found ");
		}
	}

}

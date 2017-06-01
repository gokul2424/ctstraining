package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.entity.Employee;
import com.cognizant.service.EmployeeService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class TestEmployee {
	
	static final Logger logger = Logger.getLogger(TestEmployee.class);
	
	public static void main(String[] args) {
		System.out.println(System.nanoTime());
	}
	
	@Autowired
	EmployeeService service;
	private List<Employee> employees;
	
	@Before
	public void setup(){
		Employee emp1 = new Employee("Ravi", 34344.44);
		Employee emp2 = new Employee("Priya", 35455.00);
		employees = new ArrayList<>();
		employees.add(emp1);
		employees.add(emp2);
	}
//	@Test
	public void addEmployees(){
		try {
			service.addEmployees(employees);
			logger.log(Priority.ERROR, "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not supplied valid name");
		}
	}
	
//	@Test(expected=Exception.class)
	public void addEmployeesWithNamesEmpty() throws Exception{
		for(Employee emp: employees){
			emp.setName("");
		}		
		service.addEmployees(employees);	
	}
//	@Test
	public void testSalariesLessThanLimit(){
		List<Employee> employees = service.findEmployeesBySalary(35000);
//		employees.size()
		logger.warn(employees);
		assertEquals(employees.size(), 1);
	}
	@Test
	public void testSalaryIncrement(){
		logger.debug("ONe Message ***");
		logger.warn("Test *******");
double amount = 5000;
		Employee updatedEmp;
		try {
			updatedEmp = service.incrementSalary(1, amount);
			logger.info(updatedEmp);
		} catch (Exception e) {
		// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
//		assertSame(updatedEmp.getSalary(),54344.44);
//		assertEquals(updatedEmp.getSalary(), 59344.44);
//		assertEquals(updatedEmp.getSalary(), employees.get(0).getSalary()+ amount);
	}

}

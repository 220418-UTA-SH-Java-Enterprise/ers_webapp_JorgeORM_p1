package project1;



import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.sql.Delete;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.revature.dao.EmployeeDAOImpl;
import com.revature.models.Employee;
import com.revature.services.EmployeeServiceImpl;

public class EmployeeServiceTest {
	
	private EmployeeDAOImpl mockdao;
	private EmployeeServiceImpl employeev;
	private Employee e1, e2;
	List<Employee> dummyDb;

	@Before
	public void setUp() {
		
		mockdao = Mockito.mock(EmployeeDAOImpl.class);
		
		employeev = new EmployeeServiceImpl(mockdao);
		
		e1 = new Employee(1, "jorgeusername", "password", "Real", "Employee");
		e2 = new Employee(2, "jorgeusername2", "password", "Real2", "Testing");
		
		dummyDb = new ArrayList<Employee>();
		dummyDb.add(e1);
		dummyDb.add(e2);
		
		
	}

	@Test
	public void testlogin_sucess() {
		
		when(mockdao.selectAll()).thenReturn(dummyDb);
		
		assertEquals(e2, employeev.login("jorgeusername2", "password"));
		
	}
	
	
	@Test
	public void testlogin_failure() {
		
		when(mockdao.selectAll()).thenReturn(dummyDb);
		
		assertNotEquals(e2, employeev.login("notcorrectUsername", "password"));
		
	}

}
	


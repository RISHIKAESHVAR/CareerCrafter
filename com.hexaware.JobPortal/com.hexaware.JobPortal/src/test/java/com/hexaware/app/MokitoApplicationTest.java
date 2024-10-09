package com.hexaware.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hexaware.app.Dao.EmployerRepository;
import com.hexaware.app.Entity.Employer;
import com.hexaware.app.Exception.IDnotfoundException;
import com.hexaware.app.Service.EmployerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MokitoApplicationTest {

    @Autowired
    private EmployerService empservice;
    
    @MockBean
    private EmployerRepository emprepository;

//    @Test
//    public void getjobsTest() {
//        when(emprepository.findAll()).thenReturn(
//            Stream.of(
//                new Employer(1, "google", "developing company", "var@gmail.com", "890000000", "Coimbatore", "www.google.com"),
//                new Employer(3, "Microsoft", "service company", "vis@gmail.com", "788900087767", "Coimbatore", "www.microsoft.com"),
//                new Employer(4, "Hexaware", "developing company", "ram@gmail.com", "890000000", "Bangalore", "www.hexaware.com")
//            ).collect(Collectors.toList())
//        );
//
//        assertEquals(3, empservice.getempd().size());
//    }
    
    
//    @Test
//    public void getempbyemail() {
//        String email = "ram@gmail.com";
//        
//        when(emprepository.findByEmail(email)).thenReturn(
//            new Employer(4, "Hexaware", "developing company", "ram@gmail.com", "890000000", "Bangalore", "www.hexaware.com")
//        );
//
//        Employer result = empservice.getEmployeeByEmail(email);
//
//        assertEquals("ram@gmail.com", result.getEmail());
//        assertEquals("Hexaware", result.getCompanyName());
//    }
    
    
//    @Test
//    public void saveempTest() {
//        Employer emp = new Employer(5, "Google", "Product Company", "google@gmail.com", "9345818942", "Chennai", "www.google.com");
//        
//        when(emprepository.save(emp)).thenReturn(emp);
//        
//        assertEquals(emp, empservice.Register(emp));
//    }
    
    
    @Test
    public void deleteempTest() {
        int employerId = 5;
        Employer emp = new Employer(employerId, "Google", "Product Company", "google@gmail.com", "9345818942", "Chennai", "www.google.com");
        

        when(emprepository.findById(employerId)).thenReturn(Optional.of(emp));
        
        try {

            empservice.removeEmployer(employerId);

            verify(emprepository, times(1)).delete(emp);
        } catch (IDnotfoundException e) {
            fail("IDnotfoundException was thrown: " + e.getMessage());
        }
    }


}



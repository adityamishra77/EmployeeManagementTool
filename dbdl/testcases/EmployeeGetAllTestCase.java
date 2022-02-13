import com.thinking.machines.dl.exceptions.*;
import com.thinking.machines.dl.interfaces.dto.*;
import com.thinking.machines.dl.interfaces.dao.*;
import com.thinking.machines.dl.dao.*;
import com.thinking.machines.dl.dto.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class EmployeeGetAllTestCase
{
public static void main(String gg[])
{
try
{
EmployeeDAOInterface employeeDAO;
employeeDAO=new EmployeeDAO();
Set<EmployeeDTOInterface> employees;
employees=employeeDAO.getAll();
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
for(EmployeeDTOInterface employeeDTO:employees)
{
System.out.println("Employee Id : "+employeeDTO.getEmployeeId());
System.out.println("Name : "+employeeDTO.getName());
System.out.println("Designation code : "+employeeDTO.getDesignationCode());
System.out.println("Date Of Birth : "+simpleDateFormat.format(employeeDTO.getDateOfBirth()));
System.out.println("Gender : "+employeeDTO.getGender());
System.out.println("Is Indian : "+employeeDTO.getIsIndian());
System.out.println("Basic Salary : "+employeeDTO.getBasicSalary().toPlainString());
System.out.println("PAN Number : "+employeeDTO.getPANNumber());
System.out.println("Aadhar card Number : "+employeeDTO.getAadharCardNumber());
System.out.println("*************************************");
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}

}
}
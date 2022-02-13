import com.thinking.machines.bl.exceptions.*;
import com.thinking.machines.bl.interfaces.pojo.*;
import com.thinking.machines.bl.interfaces.managers.*;
import com.thinking.machines.bl.pojo.*;
import com.thinking.machines.bl.managers.*;
import java.util.*;
import java.math.*;
import java.util.*;
import com.thinking.machines.enums.*;
class EmployeeManagerGetEmployeeByDesignationCodeTestCase
{
public static void main(String gg[])
{
DesignationInterface d=new Designation();
d.setCode(23);
try
{
EmployeeManagerInterface employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
Set<EmployeeInterface> employees;
employees=employeeManager.getEmployeesByDesignationCode(d.getCode());
DesignationInterface dsDesignation;
DesignationInterface designation;
for(EmployeeInterface employee:employees)
{
System.out.println("Employee Id. : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
dsDesignation=employee.getDesignation();
designation=new Designation();
designation.setCode(dsDesignation.getCode());
System.out.println("Designation : "+designation.getCode());
System.out.println("Date Of Birth : "+employee.getDateOfBirth());
System.out.println("Gender : "+employee.getGender());
System.out.println("Is Indian : "+employee.getIsIndian());
System.out.println("Basic Salary : "+employee.getBasicSalary());
System.out.println("PAN number : "+employee.getPANNumber());
System.out.println("Aadhar card number : "+employee.getAadharCardNumber());
}

}catch(BLException blException)
{
List<String> properties=blException.getProperties();
for(String property:properties)
{
System.out.println(blException.getException(property));
}
}
}
}
import com.thinking.machines.bl.exceptions.*;
import com.thinking.machines.bl.interfaces.pojo.*;
import com.thinking.machines.bl.interfaces.managers.*;
import com.thinking.machines.bl.pojo.*;
import com.thinking.machines.bl.managers.*;
import java.util.*;
import java.math.*;
import java.util.*;
import com.thinking.machines.enums.*;
class EmployeeManagerGetEmployeeByAadharCardNumberTestCase
{
public static void main(String gg[])
{
String aadharCardNumber=gg[0];
try
{
EmployeeInterface employee;
EmployeeManagerInterface employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
employee=employeeManager.getEmployeeByAadharCardNumber(aadharCardNumber);
System.out.println("Employee Id. : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
DesignationInterface dsDesignation;
DesignationInterface designation;
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
import com.thinking.machines.bl.exceptions.*;
import com.thinking.machines.bl.interfaces.pojo.*;
import com.thinking.machines.bl.interfaces.managers.*;
import com.thinking.machines.bl.pojo.*;
import com.thinking.machines.bl.managers.*;
import java.util.*;
import java.math.*;
import java.util.*;
import com.thinking.machines.enums.*;
class EmployeeManagerPANNumberExistsTestCase
{
public static void main(String gg[])
{
String panNumber=gg[0];
try
{
EmployeeManagerInterface employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
System.out.println("PAN number exists : "+employeeManager.panNumberExists(panNumber));
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
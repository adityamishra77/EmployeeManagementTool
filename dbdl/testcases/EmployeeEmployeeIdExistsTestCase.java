import com.thinking.machines.dl.exceptions.*;
import com.thinking.machines.dl.interfaces.dto.*;
import com.thinking.machines.dl.interfaces.dao.*;
import com.thinking.machines.dl.dao.*;
import com.thinking.machines.dl.dto.*;
public class EmployeeEmployeeIdExistsTestCase
{
public static void main(String gg[])
{
String employeeId=gg[0];
try
{
System.out.println("Employee Id : "+employeeId+" exists : "+new EmployeeDAO().employeeIdExists(employeeId));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}

}
}
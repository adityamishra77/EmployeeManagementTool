import com.thinking.machines.dl.exceptions.*;
import com.thinking.machines.dl.interfaces.dto.*;
import com.thinking.machines.dl.interfaces.dao.*;
import com.thinking.machines.dl.dao.*;
import com.thinking.machines.dl.dto.*;
public class DesignationDeleteTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
designationDAO.delete(code);
System.out.printf("Designation Deleted");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}

}
}
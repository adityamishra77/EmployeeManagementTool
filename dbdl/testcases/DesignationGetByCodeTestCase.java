import com.thinking.machines.dl.exceptions.*;
import com.thinking.machines.dl.interfaces.dto.*;
import com.thinking.machines.dl.interfaces.dao.*;
import com.thinking.machines.dl.dao.*;
import com.thinking.machines.dl.dto.*;
public class DesignationGetByCodeTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
DesignationDTOInterface designationDTO;
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
designationDTO=designationDAO.getByCode(code);
System.out.printf("code : %d Title : %s\n", designationDTO.getCode(),designationDTO.getTitle());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}

}
}
import com.thinking.machines.bl.exceptions.*;
import com.thinking.machines.bl.interfaces.managers.*;
import com.thinking.machines.bl.interfaces.pojo.*;
import com.thinking.machines.bl.managers.*;
import com.thinking.machines.bl.pojo.*;
import java.util.*;
class DesignationManagerGetDesignationsTestCase
{
public static void main(String gg[])
{
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
Set<DesignationInterface> designations;
designations=designationManager.getDesignations();
designations.forEach((designation)->{
System.out.printf("Code : %d , Title : %s\n",designation.getCode(),designation.getTitle());
});
}catch(BLException blException)
{
if(blException.hasGenericException())
{
System.out.println(blException.getGenericException());
}
List<String>properties=blException.getProperties();
for(String property:properties)
{
System.out.println(blException.getException(property));
}
}
}
} 

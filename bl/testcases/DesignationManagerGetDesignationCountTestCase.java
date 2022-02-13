import com.thinking.machines.bl.exceptions.*;
import com.thinking.machines.bl.interfaces.managers.*;
import com.thinking.machines.bl.interfaces.pojo.*;
import com.thinking.machines.bl.managers.*;
import com.thinking.machines.bl.pojo.*;
import java.util.*;
class DesignationManagerGetDesignationCountTestCase
{
public static void main(String gg[])
{
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
System.out.printf("Number of designations : "+designationManager.getDesignationCount());
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

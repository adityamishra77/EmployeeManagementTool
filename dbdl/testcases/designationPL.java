import com.thinking.machines.dl.dto.*;
import com.thinking.machines.dl.dao.*;
import com.thinking.machines.dl.interfaces.dto.*;
import com.thinking.machines.dl.interfaces.dao.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
class DesignationTableModel extends AbstractTableModule
{
private Object data[];
private String title[];
DesignationTableModel()
{
populateDataStructures();
}
public void populateDataStructures()
{
DesignationDTOInterface dto=new DesignaitonDTO();
dto=new DesignationDAO().getAll();
title=new String[2];
title[1]="Code";
title[2]="Title";
data=new Object[][];
for(DesignationDTOInterface data:dto)
{
data[0][0]=dto.getCode();
data[0][1]=dto.getT
}
}

}
class DesignationPL extends JFrame
{
private JTable table;
private JScrollPane jsp;
private DesignationTableModel designationTableModel;
private Container container;
DesignationPL()
{
designaitonTableModel=new DesignationTableModel();
table=new JTable(designationTableModel);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
container.setLayout(new GridLayout());
container.add(jsp);
}
}
class DesignationPLpsp 
{
public static void main(String gg[])
{
DesignationPL dp=new DesignationPL();
}
}
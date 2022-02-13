import javax.swing.*;
import java.awt.*;
import com.thinking.machines.pl.model.*;
import com.thinking.machines.bl.exceptions.*;
import javax.swing.table.*;
class DesignationModelTestCase extends JFrame
{
private JTable tb;
private JScrollPane jsp;
private DesignationModel designationModel;
private Container container;
DesignationModelTestCase()
{
designationModel=new DesignationModel();
tb=new JTable(designationModel);
jsp=new JScrollPane(tb,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
container.setLayout(new FlowLayout());
container.add(jsp);
setLocation(400,300);
setSize(500,600);
setVisible(true);
}
public static void main(String gg[])
{
DesignationModelTestCase dmtc=new DesignationModelTestCase();
}
}

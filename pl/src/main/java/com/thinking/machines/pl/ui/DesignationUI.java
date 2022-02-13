package com.thinking.machines.pl.ui;
import com.thinking.machines.pl.model.*;
import com.thinking.machines.bl.exceptions.*;
import com.thinking.machines.bl.interfaces.pojo.*;
import com.thinking.machines.bl.pojo.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
public class DesignationUI extends JFrame implements DocumentListener,ListSelectionListener
{
//Designation UI Structure
private JLabel titleLabel;
private JLabel searchLabel;
private JLabel searchErrorLabel;
private JTextField searchTextField;
private JButton clearSearchTextFieldButton;
private JTable designationTable;
private JScrollPane scrollPane;
private DesignationModel designationModel;
private DesignationPanel designationPanel;
private Container container;
private enum MODE{VIEW,ADD,EDIT,DELETE,EXPORT_TO_PDF};
private MODE mode;
private ImageIcon logoIcon;
private ImageIcon addIcon;
private ImageIcon editIcon;
private ImageIcon cancelIcon;
private ImageIcon deleteIcon;
private ImageIcon pdfIcon;
private ImageIcon saveIcon;
private ImageIcon clearIcon;
public DesignationUI()
{
super("HR Manager");
initComponents();
setAppearance();
addListeners();
setViewMode();
designationPanel.setViewMode();
}
//To initialize fields
public void initComponents()
{
clearIcon=new ImageIcon(this.getClass().getResource("/icons/clear_icon.png"));
logoIcon=new ImageIcon(this.getClass().getResource("/icons/logo_icon.png"));
addIcon=new ImageIcon(this.getClass().getResource("/icons/add_icon.png"));
editIcon=new ImageIcon(this.getClass().getResource("/icons/edit_icon.png"));
saveIcon=new ImageIcon(this.getClass().getResource("/icons/save_icon.png"));
cancelIcon=new ImageIcon(this.getClass().getResource("/icons/cancel_icon.png"));
deleteIcon=new ImageIcon(this.getClass().getResource("/icons/delete_icon.png"));
pdfIcon=new ImageIcon(this.getClass().getResource("/icons/pdf_icon.png"));
setIconImage(logoIcon.getImage());
titleLabel=new JLabel("Designation");
searchLabel=new JLabel("Search");
searchTextField=new JTextField(40);
searchErrorLabel=new JLabel("");
clearSearchTextFieldButton=new JButton(clearIcon);
designationModel=new DesignationModel();
designationTable=new JTable(designationModel);
scrollPane=new JScrollPane(designationTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
designationPanel=new DesignationPanel();
container=getContentPane();
}
//setting appearance of components
private void setAppearance()
{
Font titleFont=new Font("Verdana",Font.BOLD,18);
Font captionFont=new Font("Verdana",Font.BOLD,16);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
Font headerFont=new Font("Verdana",Font.BOLD,16);
Font searchErrorFont=new Font("Verdana",Font.BOLD,12);

titleLabel.setFont(titleFont);
searchLabel.setFont(captionFont);
searchErrorLabel.setFont(searchErrorFont);
searchErrorLabel.setForeground(Color.red);
searchTextField.setFont(dataFont);
designationTable.setFont(dataFont);
designationTable.setRowHeight(30);
JTableHeader header=designationTable.getTableHeader();
header.getColumnModel().getColumn(0).setPreferredWidth(20);
header.getColumnModel().getColumn(1).setPreferredWidth(400);
header.setResizingAllowed(false);
header.setReorderingAllowed(false);
header.setFont(headerFont);
designationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
container.setLayout(null);
int lm=0;
int tm=0;
titleLabel.setBounds(lm+10,tm+10,200,40);
searchLabel.setBounds(lm+10,tm+10+40+10,100,30);
searchTextField.setBounds(lm+10+100+5,tm+10+40+10,400,30);
clearSearchTextFieldButton.setBounds(lm+10+100+5+400+5,tm+10+40+10,30,30);
searchErrorLabel.setBounds(lm+10+100+5+400+5-80,tm+10+30,100,20);
scrollPane.setBounds(lm+10,tm+10+40+10+40,565,250);
designationPanel.setBounds(lm+10,tm+10+40+10+40+250+10,565,210);
container.add(titleLabel);
container.add(searchLabel);
container.add(searchTextField);
container.add(clearSearchTextFieldButton);
container.add(searchErrorLabel);
container.add(scrollPane);
container.add(designationPanel);
int width,height;
width=600;
height=630;
setSize(width,height);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
int x=(d.width/2)-(width/2);
int y=(d.height/2)-(height/2);
setLocation(x,y);
}

private void addListeners()
{
searchTextField.getDocument().addDocumentListener(this);
clearSearchTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
searchTextField.setText("");
searchTextField.requestFocus();
}
});
//to display the selected row of table in inner container 
designationTable.getSelectionModel().addListSelectionListener(this);
}

//code for displaying selected value of table in below container (in label)
public void valueChanged(ListSelectionEvent ev)
{
int selectedRowIndex=designationTable.getSelectedRow();
try
{
DesignationInterface designation=designationModel.getDesignationAt(selectedRowIndex);
designationPanel.setDesignation(designation);
}catch(BLException blException)
{
designationPanel.clearDesignation();
}
}

//code to search designation,select and display in visible area
private void searchDesignation()
{
searchErrorLabel.setText("");
String title=searchTextField.getText().trim();
if(title.length()==0) return;
int rowIndex=0;
try
{
rowIndex=designationModel.indexOfTitle(title,true);
}catch(BLException blExceptin)
{
searchErrorLabel.setText("Not found");
return;
}
designationTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle=designationTable.getCellRect(rowIndex,0,true);
designationTable.scrollRectToVisible(rectangle);
}
//In order to implement document listener for search textfield we have to write the follwing three methods
public void changedUpdate(DocumentEvent ev)
{
searchDesignation();
}
public void removeUpdate(DocumentEvent ev)
{
searchDesignation();
}
public void insertUpdate(DocumentEvent ev)
{
searchDesignation();
}

//Modes for outer components
private void setViewMode()
{
this.mode=MODE.VIEW;
if(designationTable.getRowCount()==0)
{
this.searchTextField.setEnabled(false);
this.clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
else
{
this.searchTextField.setEnabled(true);
this.clearSearchTextFieldButton.setEnabled(true);
designationTable.setEnabled(true);
}
}
private void setAddMode()
{
this.mode=MODE.ADD;
this.searchTextField.setEnabled(false);
this.clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
private void setEditMode()
{
this.mode=MODE.EDIT;
this.searchTextField.setEnabled(false);
this.clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
private void setDeleteMode()
{
this.mode=MODE.DELETE;
this.searchTextField.setEnabled(false);
this.clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
private void setExportToPDFMode()
{
this.mode=MODE.EXPORT_TO_PDF;
this.searchTextField.setEnabled(false);
this.clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}

// inner class starts
class DesignationPanel extends JPanel
{
private JLabel titleCaptionLabel;
private JLabel titleLabel;
private JTextField titleLabelTextField;
private JButton clearTitleTextFieldButton;
private JPanel buttonPanel;
private JButton addButton;
private JButton editButton;
private JButton cancelButton;
private JButton deleteButton;
private JButton exportToPDFButton;
private DesignationInterface designation;
DesignationPanel()
{
setBorder(BorderFactory.createLineBorder(new Color(165,165,165)));
initComponents(); //initialize the components
setAppearance();  //setting the components
addListeners();  //event programming of components
}
public void setDesignation(DesignationInterface designation) 
{
this.designation=designation;
titleLabel.setText(designation.getTitle());
}
public void clearDesignation() 
{
this.designation=null;
titleLabel.setText("");
}
public void initComponents()
{
designation=null;
titleCaptionLabel=new JLabel("Designation");
titleLabel=new JLabel("");
titleLabelTextField=new JTextField();
clearTitleTextFieldButton=new JButton(clearIcon);
buttonPanel=new JPanel();
addButton=new JButton(addIcon);
editButton=new JButton(editIcon);
cancelButton=new JButton(cancelIcon);
deleteButton=new JButton(deleteIcon);
exportToPDFButton=new JButton(pdfIcon);
}
private void setAppearance()
{
Font captionFont=new Font("Verdana",Font.BOLD,16);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
titleCaptionLabel.setFont(captionFont);
titleLabel.setFont(dataFont);
titleLabelTextField.setFont(dataFont);
setLayout(null);
int lm,tm;
lm=0;
tm=0;
titleCaptionLabel.setBounds(lm+10,tm+20,110,30);
titleLabel.setBounds(lm+10+110,tm+20,400,30);
titleLabelTextField.setBounds(lm+10+110+5,tm+20,350,30);
clearTitleTextFieldButton.setBounds(lm+10+110+10+350+5,tm+20,30,30);

buttonPanel.setBounds(50,20+30+30,460,80);
buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(165,165,165)));
buttonPanel.setLayout(null);
addButton.setBounds(70,15,50,50);
editButton.setBounds(70+20+50,15,50,50);
cancelButton.setBounds(70+50+50+20+20,15,50,50);
deleteButton.setBounds(70+50+50+50+20+20+20,15,50,50);
exportToPDFButton.setBounds(70+50+50+50+50+20+20+20+20,15,50,50);
buttonPanel.add(addButton);
buttonPanel.add(editButton);
buttonPanel.add(cancelButton);
buttonPanel.add(deleteButton);
buttonPanel.add(exportToPDFButton);

add(titleCaptionLabel);
add(titleLabel);
add(titleLabelTextField);
add(clearTitleTextFieldButton);
add(buttonPanel);
}
//Method for add and save new designation
public boolean addDesignation()
{
String title=titleLabelTextField.getText().trim();
if(title.length()==0)
{
JOptionPane.showMessageDialog(this,"Designation Required");
titleLabelTextField.requestFocus();
return false;
}
DesignationInterface d=new Designation();
d.setTitle(title);
try
{
designationModel.add(d);
int rowIndex=0;
try
{
rowIndex=designationModel.indexOfDesignation(d);
}catch(BLException blException)
{
//do nothing
}
designationTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle=designationTable.getCellRect(rowIndex,0,true);
designationTable.scrollRectToVisible(rectangle);
return true;
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
titleLabelTextField.requestFocus();
return false;
}
}
//Method for edit and update new designation
public boolean updateDesignation()
{
String title=titleLabelTextField.getText().trim();
if(title.length()==0)
{
JOptionPane.showMessageDialog(this,"Designation Required");
titleLabelTextField.requestFocus();
return false;
}
DesignationInterface d=new Designation();
d.setCode(this.designation.getCode());
d.setTitle(title);
try
{
designationModel.update(d);
int rowIndex=0;
try
{
rowIndex=designationModel.indexOfDesignation(d);
}catch(BLException blException)
{
//do nothing
}
designationTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle=designationTable.getCellRect(rowIndex,0,true);
designationTable.scrollRectToVisible(rectangle);
return true;
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
titleLabelTextField.requestFocus();
return false;
}
}
//Method for delete the designation
private void removeDesignation()
{
try
{
int code=this.designation.getCode();
String title=this.designation.getTitle();
int selectedOption=JOptionPane.showConfirmDialog(this,"Confirmation","Delete "+title+" ?",JOptionPane.YES_NO_OPTION);
if(selectedOption==JOptionPane.NO_OPTION) return;
designationModel.remove(code);
JOptionPane.showMessageDialog(this,title+" deleted");
this.clearDesignation();
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
}
}
public void addListeners()
{
clearTitleTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
titleLabelTextField.setText("");
titleLabelTextField.requestFocus();
}
});
this.exportToPDFButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
JFileChooser jfc=new JFileChooser();
jfc.setCurrentDirectory(new File("."));
jfc.setAcceptAllFileFilterUsed(false);
jfc.addChoosableFileFilter(new javax.swing.filechooser.FileFilter(){
public boolean accept(File file)
{
if(file.isDirectory()) return true;
if(file.getName().endsWith(".pdf")) return true;
return false;
}
public String getDescription()
{
return "Pdf files";
}
});
try
{
int selectedOption=jfc.showSaveDialog(DesignationUI.this);
if(selectedOption==jfc.APPROVE_OPTION)
{
File selectedFile=jfc.getSelectedFile();
String pdfFile=selectedFile.getAbsolutePath();
if(pdfFile.endsWith(".")) pdfFile+="pdf";
else if(pdfFile.endsWith(".pdf")==false) pdfFile+=".pdf";
File file=new File(pdfFile);
if(file.exists()) 
{
int selectOption=JOptionPane.showConfirmDialog(DesignationUI.this,"Do you want to delete the file?","File exists",JOptionPane.YES_NO_OPTION);
if(selectOption==JOptionPane.YES_OPTION)
{
file.delete();
}
else
{
return;
}
}

File parent=new File(file.getParent());
if(parent.exists()==false || parent.isDirectory()==false)
{
JOptionPane.showMessageDialog(DesignationUI.this,"Incorrect Path :"+file.getAbsolutePath());
}
designationModel.exportToPDF(file);
JOptionPane.showMessageDialog(DesignationUI.this,"Data exported to "+file.getAbsolutePath());
}
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(DesignationUI.this,blException.getGenericException());
}
}catch(Exception e)
{
System.out.println(e);
}
}
});



this.addButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW)
{
setAddMode();
}
else
{
if(addDesignation())
{
setViewMode();
}
}
}
});
this.editButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW)
{
setEditMode();
}
else
{
if(updateDesignation())
{
setViewMode();
}
}
}
});
this.cancelButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
setViewMode();
}
});

deleteButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
setDeleteMode();
}
});
}
void setViewMode()
{
DesignationUI.this.setViewMode();
titleLabel.setVisible(true);
titleLabelTextField.setEnabled(false);
clearTitleTextFieldButton.setVisible(false);
this.addButton.setIcon(addIcon);
this.editButton.setIcon(editIcon);
addButton.setEnabled(true);
cancelButton.setEnabled(false);
if(designationModel.getRowCount()>0)
{
editButton.setEnabled(true);
deleteButton.setEnabled(true);
exportToPDFButton.setEnabled(true);
}
else
{
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
}
void setAddMode()
{
DesignationUI.this.setAddMode();
this.titleLabelTextField.setText("");
this.titleLabel.setVisible(false);
this.titleLabelTextField.setEnabled(true);
clearTitleTextFieldButton.setVisible(true);
clearTitleTextFieldButton.setIcon(clearIcon);
addButton.setIcon(saveIcon);
editButton.setEnabled(false);
cancelButton.setEnabled(true);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}

void setEditMode()
{
if(designationTable.getSelectedRow()<0 || designationTable.getSelectedRow()>designationModel.getRowCount())
{
JOptionPane.showMessageDialog(this,"Select Designation to edit");
return;
}
DesignationUI.this.setEditMode();
this.titleLabelTextField.setText(this.designation.getTitle());
this.titleLabel.setVisible(false);
this.titleLabelTextField.setEnabled(true);
this.clearTitleTextFieldButton.setVisible(true);
clearTitleTextFieldButton.setIcon(clearIcon);
editButton.setIcon(saveIcon);
addButton.setEnabled(false);
cancelButton.setEnabled(true);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
void setDeleteMode()
{
if(designationTable.getSelectedRow()<0 || designationTable.getSelectedRow()>designationModel.getRowCount())
{
JOptionPane.showMessageDialog(this,"Select Designation to delete");
return;
}
DesignationUI.this.setDeleteMode();
addButton.setEnabled(false);
cancelButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
removeDesignation();
DesignationUI.this.setViewMode();
this.setViewMode();
}
void setExportToPDFMode()
{
DesignationUI.this.setExportToPDFMode();
addButton.setEnabled(false);
cancelButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
} //inner class ends
}
package com.thinking.machines.bl.interfaces.managers;
import com.thinking.machines.bl.exceptions.*;
import com.thinking.machines.bl.interfaces.pojo.*;
import com.thinking.machines.bl.pojo.*;
import java.util.*;
public interface DesignationManagerInterface 
{
public void addDesignation(DesignationInterface designation) throws BLException;
public void updateDesignation(DesignationInterface desgination) throws BLException;
public void removeDesignation(int code) throws BLException;
public DesignationInterface getDesignationByCode(int code) throws BLException;
public DesignationInterface getDesignationByTitle(String title) throws BLException;
public boolean designationCodeExists(int code); 
public boolean designationTitleExists(String title);
public Set<DesignationInterface> getDesignations() throws BLException;
public int getDesignationCount();
}
package com.thinking.machines.bl.interfaces.managers;
import com.thinking.machines.bl.exceptions.*;
import com.thinking.machines.bl.interfaces.pojo.*;
import com.thinking.machines.bl.pojo.*;
import java.util.*;
public interface EmployeeManagerInterface 
{
public void addEmployee(EmployeeInterface employee) throws BLException;
public void updateEmployee(EmployeeInterface desgination) throws BLException;
public void removeEmployee(String employeeId) throws BLException;
public EmployeeInterface getByEmployeeId(String employeeId) throws BLException;
public EmployeeInterface getByPANNumber(String panNumber) throws BLException;
public EmployeeInterface getByAadharCardNumber(String aadharCardNumber) throws BLException;
public boolean employeeIdExists(String employeeId) throws BLException; 
public boolean panNumberExists(String panNumber) throws BLException;
public boolean aadharCardNumberExists(String aadharCardNumber) throws BLException;
public Set<EmployeeInterface> getEmployees() throws BLException;
public Set<EmployeeInterface> getEmployeesByDesignation(int designationCode) throws BLException;
public boolean isDesignationAlloted(int designationCode) throws BLException;
public int getCountByDesignationCode(int designationCode) throws BLException;
public int getEmployeeCount() throws BLException;
}
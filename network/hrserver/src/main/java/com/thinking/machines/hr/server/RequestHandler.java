package com.thinking.machines.hr.server;
import com.thinking.machines.network.common.*;
import com.thinking.machines.network.server.*;
import com.thinking.machines.bl.exceptions.*;
import com.thinking.machines.bl.interfaces.pojo.*;
import com.thinking.machines.bl.interfaces.managers.*;
import com.thinking.machines.bl.managers.*;
public class RequestHandler implements RequestHandlerInterface
{
public DesignationManagerInterface designationManager=null;
public RequestHandler()
{
try
{
designationManager=DesignationManager.getDesignationManager();
}catch(BLException blException)
{
//do nothing
}
}
public Response process(Request request) 
{
Response response=new Response();
String manager=request.getManager();
String action=request.getAction();
Object arguments=request.getArguments();
if(manager.equals("DesignationManager"))
{
if(designationManager==null)
{
BLException blException=new BLException();
blException.setGenericException("Designation Manager required");
}
if(action.equals("getDesignations"))
{
Object result=designationManager.getDesignations();
response.setSuccess(true);
response.setResult(result);
response.setException(null);
}
if(action.equals("addDesignation"))
{
DesignationInterface designation=(DesignationInterface)arguments;
try
{
designationManager.addDesignation(designation);
}catch(BLException blException)
{
//will never occur
}
response.setSuccess(true);
response.setResult(null);
response.setException(null);
}
if(action.equals("updateDesignation"))
{
DesignationInterface designation=(DesignationInterface)arguments;
try
{
designationManager.updateDesignation(designation);
}catch(BLException blException)
{
//will never occur
}
response.setSuccess(true);
response.setResult(null);
response.setException(null);
}
if(action.equals("removeDesignation"))
{
int code=(int)arguments;
try
{
designationManager.removeDesignation(code);
}catch(BLException blException)
{
//will never occur
}
response.setSuccess(true);
response.setResult(null);
response.setException(null);
}

if(action.equals("getDesignationByCode"))
{
int code=(int)arguments;
Object result=null;
try
{
result=(Object)designationManager.getDesignationByCode(code);
}catch(BLException blException)
{
}
response.setSuccess(true);
response.setResult(result);
response.setException(null);
}
if(action.equals("getDesignationByTitle"))
{
String title=(String)arguments;
Object result=null;
try
{
result=(Object)designationManager.getDesignationByTitle(title);
}catch(BLException blException)
{
}
response.setSuccess(true);
response.setResult(result);
response.setException(null);
}
if(action.equals("DesignationCodeExists"))
{
int code=(int)arguments;
Object result=null;
result=(Object)designationManager.designationCodeExists(code);
response.setSuccess(true);
response.setResult(result);
response.setException(null);
}
if(action.equals("DesignationTitleExists"))
{
String title=(String)arguments;
Object result=null;
result=(Object)designationManager.designationTitleExists(title);
response.setSuccess(true);
response.setResult(result);
response.setException(null);
}
if(action.equals("getDesignationCount"))
{
Object result=(Object)designationManager.getDesignationCount();
response.setSuccess(true);
response.setResult(result);
response.setException(null);
}



}
return response;
}
}
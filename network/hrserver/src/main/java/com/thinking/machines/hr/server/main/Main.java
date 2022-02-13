package com.thinking.machines.hr.server.main;
import com.thinking.machines.hr.server.*;
import com.thinking.machines.network.server.*;
import com.thinking.machines.network.common.exceptions.*;
class Main 
{
public static void main(String gg[])
{
try
{
RequestHandlerInterface requestHandler=new RequestHandler();
NetworkServer networkServer=new NetworkServer(requestHandler);
networkServer.start();
}catch(NetworkException ne)
{
System.out.println(ne);
}
}
}
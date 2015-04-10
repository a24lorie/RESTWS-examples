package org.alb.sse.server.listeners;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 
 *
 */
public class Timer extends HttpServlet
{
    private static final long serialVersionUID = 4115131310355778749L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/event-stream; charset=utf-8");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("connection", "keep-alive");
        PrintWriter out = response.getWriter();
        
        while(true){
            Date date = new Date();
            out.print("event: time\n" );
            out.print("data:"+date.toString() + "\n\n");
            out.flush();

            try{
        	Thread.sleep(1000);
        	
            }catch(InterruptedException e){
        	e.printStackTrace();
            }
        }
    }
}

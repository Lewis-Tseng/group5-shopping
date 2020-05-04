package com.gro.group.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gro.group.groupService;


public class ScheduleGroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    Timer timer;
    
    public ScheduleGroServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/plain");                                
		PrintWriter out = res.getWriter(); 
	} 

	
	public void init() throws ServletException{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date firstTime = calendar.getTime();
		
		timer = new Timer();
		
		TimerTask task = new TimerTask() {   
			   public void run() {   
				   groupService groupSvc = new groupService();
				   groupSvc.finishGroup();
			   }   
		};  
		
		timer.scheduleAtFixedRate(task,firstTime,86400000);

	}
	
	public void destory() {
		super.destroy();
        timer.cancel();
	}
	
	

}

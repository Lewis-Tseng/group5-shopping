package com.emp_auth.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp_auth.model.EaService;
import com.emp_auth.model.EaVO;

public class EaServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getAuths_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.��隢�� ****************************************/
				String emp_id = new String(req.getParameter("emp_id"));

				/*************************** 2.���閰Ｚ��� ****************************************/
				EaService eaSvc = new EaService();
				Set<EaVO> set = eaSvc.getAuthsByEmp(emp_id);

				/*************************** 3.�閰Ｗ���,皞��漱(Send the Success view) ************/
				req.setAttribute("listAuths_ByEmp", set);    // 鞈�澈����et�隞�,摮request
		
				String url = null;
				
					url = "back_end/ea/listAuthsByEmp.jsp";        // ����漱 dept/listEmps_ByDeptno.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** �隞���隤方��� ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

}
}

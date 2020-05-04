package com.administrator.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.administrator.model.*;

import com.emp_auth.model.EaService;
import com.emp_auth.model.EaVO;
import com.reportmem.model.ReportMemService;
import com.emp_auth.model.EaService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 50 * 1024 * 1024)
public class AdmServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String empid = req.getParameter("empid");
				if (empid == null || (empid.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/SelectEmp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				AdmService admSvc = new AdmService();
				AdmVO admVO = admSvc.getOneAdm(empid);
				if (admVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/SelectEmp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("admVO", admVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/adm/EmpOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page3.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String emp_id = new String(req.getParameter("emp_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				AdmService admSvc = new AdmService();
				AdmVO admVO = admSvc.getOneAdm(emp_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("admVO", admVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/adm/update_adm_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String emp_id = new String(req.getParameter("emp_id").trim());

				String emp_name = req.getParameter("emp_name");
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(emp_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String emp_psw = req.getParameter("emp_psw").trim();
				if (emp_psw == null || emp_psw.trim().length() == 0) {
					errorMsgs.add("員工密碼請勿空白");
				}

				String auth_id = new String(req.getParameter("auth_id").trim());

				AdmVO admVO = new AdmVO();
				admVO.setEmp_id(emp_id);
				admVO.setEmp_name(emp_name);
				admVO.setEmp_psw(emp_psw);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adm/update_adm_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 *****************************************/
				AdmService admSvc = new AdmService();
				admVO = admSvc.updateAdm(emp_id, emp_name, emp_psw);

				/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
				EaService eaSvc = new EaService();
//				if(requestURL.equals("/ea/listAuth_id_ByEmp_id.jsp") || requestURL.equals("/ea/listAllAuth.jsp"))
//					req.setAttribute("listAuth_id_ByEmp_id",eaSvc.getAuthsByEmp(emp_id)); // 資料庫取出的list物件,存入request

				if (requestURL.equals("/adm/listEmps_ByCompositeQuery.jsp")) {
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
					List<AdmVO> list = admSvc.getAll(map);
					req.setAttribute("listEmps_ByCompositeQuery", list); // 複合查詢, 資料庫取出的list物件,存入request
				}

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adm/update_adm_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insertEmp".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String emp_name = req.getParameter("emp_name");

				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(emp_nameReg)) {
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String psw = passWord("password");
				String email = req.getParameter("emp_email");
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("員工信箱: 請勿空白");
				}
			
//				寄驗證信開始
				String subject = "密碼通知";
				String name = emp_name;
				String passRandom = psw;
				String job = "恭喜你成為JUST強身健身館的員工";
				String messageText = "Hello!" + name + "請謹記此密碼:了 " + passRandom + "\n" + " (已經啟用)" + "\n" + job;
				MailService mailService = new MailService();
				mailService.sendMail(email, subject, messageText);
//				寄驗證信結束
				
				System.out.println("成功寄出");
				
				Part part=req.getPart("file");
				String header = part.getHeader("content-disposition");
				System.out.println(header);
				String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
				System.out.println("filename=" + filename);
				if(filename.isEmpty()) {
					errorMsgs.add("未選擇圖片");
				}
				byte[] pic = getPictureByteArray(req.getPart("file").getInputStream());
				/* 新增員工基本資料開始 */
				AdmVO admVO = new AdmVO();
				admVO.setEmp_name(emp_name);
				admVO.setEmp_psw(psw);
				admVO.setEmp_email(email);
				admVO.setEmp_pic(pic);
				AdmService admSvc = new AdmService();
				admVO = admSvc.addAdm(emp_name, psw, email,pic);
				/* 新增員工基本資料結束 */
				/* 新增員工權限編號 */
				EaService EaSvc = new EaService();
				AdmService empSvc = new AdmService();
				AdmVO empVO = empSvc.getOneEmpName(emp_name);
				String empid = empVO.getEmp_id();
				String[] authid = req.getParameterValues("authid");
				if (authid != null) {
					for (int i = 0; i < authid.length; i++) {
						EaSvc.addEa(empid, authid[i]);
						System.out.println(empid);
						System.out.println("authid=" + authid[i]);
					}
				}
				String url = "/back_end/adm/ListAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adm/AddEmp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("listEmps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map", map1);
					map = map1;
				}

				/*************************** 2.開始複合查詢 ***************************************/
				AdmService admSvc = new AdmService();
				List<AdmVO> list = admSvc.getAll(map);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/adm/listEmps_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page3.jsp");
				failureView.forward(req, res);
			}
		}
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String empid = req.getParameter("empid").trim();
				System.out.println(empid);
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("員工編號不能空白");
				}
				String emp_psw = req.getParameter("password").trim();
				System.out.println(emp_psw);
				if (emp_psw == null || emp_psw.trim().length() == 0) {
					errorMsgs.add("員工密碼請勿空白");
				}
				AdmService empSvc = new AdmService();
				AdmVO empVO = (AdmVO) empSvc.getOneAdm(empid);
				System.out.println("database:psw=" + empVO.getEmp_psw());
				HttpSession session = req.getSession();

				if (emp_psw.equals(empVO.getEmp_psw())) {
					session.setAttribute("empVO", empVO);
				}
				String url = "/back_end/adm/SelectEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adm/EmpLogin.jsp");
				failureView.forward(req, res);
			}
		}
		if ("DeleteAuth".equals(action)) {
			System.out.println("1111");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String empid = req.getParameter("empid");
				String authid = req.getParameter("authid");

				/*************************** 2.開始刪除資料 ***************************************/
				EaService eaSvc = new EaService();
				eaSvc.deleteEa(empid, authid);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/adm/update_One_Emp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adm/update_One_Emp.jsp");
				failureView.forward(req, res);
			}

		}
		if ("AddAuth".equals(action)) {
			System.out.println("2222");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String empid = req.getParameter("empid");
				String authid = req.getParameter("authid");
				/*************************** 2.開始新增資料 ***************************************/
				EaService eaSvc = new EaService();
				eaSvc.addEa(empid, authid);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/adm/update_One_Emp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adm/update_One_Emp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("signout".equals(action)) {
			System.out.println("1111111111111111");
			HttpSession session = req.getSession();
			System.out.println(session.toString());
			session.removeAttribute("empVO");
			System.out.println("已移除");
			String url = "/back_end/adm/EmpLogin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/* 查詢員工權限並修改 */
		if ("getOne_Emp".equals(action)) { // 來自update_One_Emp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String empid = req.getParameter("empid");
				if (empid == null || (empid.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/update_One_Emp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				AdmService admSvc = new AdmService();
				AdmVO admVO = admSvc.getOneAdm(empid);
				if (admVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/update_One_Emp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("admVO", admVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/adm/update_One_Emp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/update_One_Emp.jsp");
				failureView.forward(req, res);
			}
		}

	}

	public static String passWord(String password) {
		Integer num = 0;
		num = (int) (Math.random() * 14578652) + 1;
		String psw = Integer.toString(num);

		return psw;
	}
	public static byte[] getPictureByteArray(InputStream path) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = path.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();

		return baos.toByteArray();
	}

}

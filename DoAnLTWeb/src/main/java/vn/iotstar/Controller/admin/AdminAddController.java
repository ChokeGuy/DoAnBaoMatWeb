package vn.iotstar.Controller.admin;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.Entity.Admin;
import vn.iotstar.Service.IAdminService;
import vn.iotstar.Service.Impl.AdminServiceImpl;
import vn.iotstar.Dao.Impl.AdminDaoImpl;
@WebServlet(urlPatterns = { "/admin/admin/add" })
public class AdminAddController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IAdminService adminService = new AdminServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String username = req.getParameter("admin-username");
//		String password = req.getParameter("admin-password");
//		Admin admin = new Admin();
//		admin.setName(req.getParameter("name"));
//		if(adminService.checkAdminLogin(username,password) != null) {
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/addadmin.jsp");
//			dispatcher.forward(req, resp);
//		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/addadmin.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		String admin_username = req.getParameter("admin-username");
		String admin_password = req.getParameter("admin-password");
		String admin_name = req.getParameter("admin-name");
		Admin admin = new Admin();
		
		admin.setUsername(admin_username);
		try {
			String hashPassword = AdminDaoImpl.hashPassword(admin_password);
			admin.setPassword(hashPassword);
			admin.setName(admin_name);
			adminService.insert(admin);
			resp.sendRedirect(req.getContextPath() + "/admin/admin/list");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return;
		}

	}

}

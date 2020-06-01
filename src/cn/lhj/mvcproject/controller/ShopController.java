package cn.lhj.mvcproject.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"*.pdo"})
public class ShopController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println(req.getServletPath());
		String path = req.getServletPath();
		String mn = path.substring(1,path.lastIndexOf('.'));
		
		try {
			Method method = this.getClass().getDeclaredMethod(mn, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	private void shopping(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String pname = req.getParameter("pname");
		System.out.println(pname);
		req.setAttribute("p", pname);
		req.getRequestDispatcher("/productdetails.jsp").forward(req, resp);
	}
	
	private void addcar(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setCharacterEncoding("UTF-8");
		
		String pname = req.getParameter("pname");
		HttpSession session = req.getSession(true);
		List<String> products = (List<String>) session.getAttribute("car");
		if(products==null) {
			products = new ArrayList<String>();
		}
		
		products.add(pname);
		session.setAttribute("car", products);
		resp.sendRedirect(req.getContextPath()+"/shoppingcar.jsp");
	}
}

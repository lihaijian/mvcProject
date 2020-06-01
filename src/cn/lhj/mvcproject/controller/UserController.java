package cn.lhj.mvcproject.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.jws.WebService;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.lhj.mvcproject.model.Ponline;
import cn.lhj.mvcproject.model.User;
import cn.lhj.mvcproject.service.FactoryService;
import cn.lhj.mvcproject.service.PonlineService;
import cn.lhj.mvcproject.service.UserService;
import cn.lhj.mvcproject.utils.CookieUtils;
import oracle.net.aso.p;

@WebServlet(urlPatterns = { "*.udo" })
public class UserController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService = FactoryService.getUserService();
	private PonlineService ponlineService = FactoryService.getPonlineService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// req.setCharacterEncoding("UTF-8");
		// resp.setCharacterEncoding("UTF-8");
		// System.out.println(req.getRealPath(""));
		String sp = req.getServletPath();
		String mn = sp.substring(1, sp.lastIndexOf("."));
		// System.out.println(sp);
		// System.out.println(mn);

		try {
			Method method = this.getClass().getDeclaredMethod(mn, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String passwd = req.getParameter("passwd");
		String address = req.getParameter("address");
		String phoneno = req.getParameter("phoneno");
		User user = new User(username, passwd, phoneno, address);
		int rows = userService.save(user);
		// System.out.println(rows);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	/**
	 * 实现首页的模糊查询,但有sql注入攻击的风险
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String address = req.getParameter("address");
		String phoneno = req.getParameter("phoneno");
		// System.out.println(req.getRealPath(""));
		List<User> list = userService.query(username, address, phoneno);
		req.setAttribute("users", list);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		int rows = userService.deleteUserById(id);
		// System.out.println(rows);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.valueOf(req.getParameter("id"));
		User user = userService.get(id);
		req.setAttribute("user", user);
		req.getRequestDispatcher("update.jsp").forward(req, resp);

	}

	private void updateDo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.valueOf(req.getParameter("id"));
		String username = req.getParameter("username");
		String passwd = req.getParameter("passwd");
		String address = req.getParameter("address");
		String phoneno = req.getParameter("phoneno");

		User oriUser = userService.get(id);
		if (!username.equals(oriUser.getUsername()) && userService.getCountByName(username) > 0) {
			req.setAttribute("error", "用户名已被占用");
			// System.out.println(req.getAttribute("user"));
			// req.setAttribute("user", oriUser);
			req.getRequestDispatcher("/update.udo?id=" + id).forward(req, resp);
			return;
		}

		User user = new User(username, passwd, phoneno, address);
		user.setId(id);
//		System.out.println(user);
		int rows = userService.updateUserById(user);
//		System.out.println(rows);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		HttpSession session = req.getSession(true);
//		session.setMaxInactiveInterval(20*60);

		String username = req.getParameter("username");
		String passwd = req.getParameter("passwd");
		String expiredays = req.getParameter("expiredays");
//		System.out.println(username);
//		System.out.println(passwd);
//		System.out.println(expiredays);
//		

		User user = userService.login(username, passwd);
		if (user != null) { // 用户密码是对的
			// System.out.println("correct");
			switch (expiredays) {
			case "7":
				CookieUtils.createCookie(username, req, resp, 7 * 24 * 60 * 60);
				break;
			case "30":
				CookieUtils.createCookie(username, req, resp, 30 * 24 * 60 * 60);
				break;
			case "100":
				CookieUtils.createCookie(username, req, resp, Integer.MAX_VALUE);
				break;
			default:
				CookieUtils.createCookie(username, req, resp, -1);
				break;
			}
			req.getSession().setAttribute("user", username);

			// 登录成功之后将Traveller改为username
			HttpSession session = req.getSession();
			Ponline ponline = ponlineService.getOnlineBySsid(session.getId());
			if (ponline != null) {
				ponline.setUsername(username);
				ponlineService.updateOnline(ponline);
			}

			resp.sendRedirect(req.getContextPath() + "/login.jsp");

		} else {
			req.setAttribute("error", "用户名或密码错误");
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}

	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String userKey = null;
		String ssid = null;
		Cookie[] cookies = req.getCookies();
		if (cookies != null && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userKey") || cookie.getName().equals("ssid")) {
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
				}
			}
		}

		HttpSession session = req.getSession();
		session.removeAttribute("user");

		resp.sendRedirect(req.getContextPath() + "/login.jsp");
	}

	private void ponline(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("online in");
		List<Ponline> list = ponlineService.getAllOnline();
		System.out.println(list.size());
		req.setAttribute("ponline", list);
		req.getRequestDispatcher("/ponline.jsp").forward(req, resp);
	}
}

package com.ohathaway.servlet;

import com.ohathaway.model.entity.User;
import com.ohathaway.store.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * - @WebServlet(urlPatterns = "/reg.do")
 * 4. Регистрация пользователя.
 * Security
 * ATTENTION! -
 * удален файл web.xml, произведена замена во всех классах
 * на аннотацию @WebServlet(urlPattern = " маппинг имя")
 */
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    /**
     * Если пользователь ввел верную почту и пароль, то мы записываем в HttpSession
     * детали этого пользователя.
     * HttpSession sc = req.getSession();
     * User admin = new User();
     * admin.setName("Admin");
     * admin.setEmail(email);
     * sc.setAttribute("user", admin);
     * Теперь эти данные можно получить на другой странице.
     * <p>
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = UserRepository.instOf().findByEmail(email).get(0);
        if (user != null) {
            req.setAttribute("error", "данный пользователь уже существует");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        if (name != null && email != null && password != null) {
            HttpSession sc = req.getSession();
            User admin = new User();
            admin.setName(name);
            admin.setEmail(email);
            admin.setPassword(password);
            sc.setAttribute("user", admin);
            UserRepository.instOf().saveUser(admin);
            resp.sendRedirect(req.getContextPath() + "/posts.do");
        } else {
            req.setAttribute("error", "Не верный email, пароль или имя");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}

package ru.job4j.cars.servlet;

import ru.job4j.cars.model.User;
import ru.job4j.cars.store.AdRepository;

import javax.servlet.http.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * - @WebServlet(urlPatterns = "/auth.do")
 * 0. Страница Login.jsp
 * Сервлет проверяет почту и пароль, если они совпадают, то переходит на страницу вакансий.
 * Если нет, то возвращает обратно на страницу Login.
 *4. Регистрация пользователя.
 * Авторизация и регистрацию сделайте через Store. В предыдущих уроках мы загрузали пользователя в коде.
 * User admin = new User();
 * admin.setName("Admin");
 * admin.setEmail(email);
 * sc.setAttribute("user", admin);
 * @author SlartiBartFast-art
 * @since 20.10.21
 */
public class AuthServlet extends HttpServlet {

    /**
     * Если пользователь ввел верную почту и пароль, то мы записываем в HttpSession
     * детали этого пользователя.
     * HttpSession sc = req.getSession();
     * User admin = new User();
     * admin.setName("Admin");
     * admin.setEmail(email);
     * sc.setAttribute("user", admin);
     * Теперь эти данные можно получить на другой странице.
     *
     *4. Регистрация пользователя. [#283110 #209712]
     *   Уровень : 3. МидлКатегория : 3.2. Servlet JSPТопик : 3.2.6. Filter, Security
     *   Авторизация и регистрацию сделайте через Store. В предыдущих уроках мы загрузали пользователя в коде.
     *   User admin = new User();
     *   admin.setName("Admin");
     *  admin.setEmail(email);
     *   sc.setAttribute("user", admin);
     *   Нужно это переделать на Store.instOf().findByEmail().
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = AdRepository.instOf().findByEmail(email).get(0);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession sc = req.getSession();
            User admin = new User();
            admin.setName(user.getName());
            admin.setEmail(user.getEmail());
            sc.setAttribute("user", admin);
            resp.sendRedirect(req.getContextPath() + "/candidates.do"); // перевести на страницу где все
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    /* Было
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if ("root@local".equals(email) && "root".equals(password)) {
            HttpSession sc = req.getSession();
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail(email);
            sc.setAttribute("user", admin);
            resp.sendRedirect(req.getContextPath() + "/posts.do");
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
     */
}

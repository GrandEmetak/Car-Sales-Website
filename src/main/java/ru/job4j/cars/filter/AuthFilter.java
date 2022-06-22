package ru.job4j.cars.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * - @WebFilter(urlPatterns = "*.do")
 * Filter
 *
 * на страницу posts.do может переходить только авторизованный пользователь.
 *  мы будем фильтровать все запросы.
 * Если запрос не связан с пользователем, то будем перенаправлять такой запрос на страницу авторизации.
 * Чтобы этого добиться в Java используется javax.servlet.Filter интерфейс.
 * Все запросы с расширением *.do будут обрабатываться нашим фильтром.
 * В AuthFilter добавлено игнорировние сервлета reg.do.
 * <filter-mapping>
 * <filter-name>AuthFilter</filter-name>
 * <url-pattern>*.do</url-pattern>В url-pattern можно указать полный путь или маску поиска.
 * </filter-mapping>
 * полный путь или маску поиска * - все запросы.
 * Здесь используется маска *.do. *.do - это дань старому фреймворку Struts.
 */
public class AuthFilter implements Filter {

    /**
     * Интерфейс Filter содержит метод doFilter.
     * Через этот метод будут проходить запросы к сервлетам.
     * Если запрос идет на сервлет авторизации, то проверку делать не будем.
     * if (uri.endsWith("auth.do")) {
     * chain.doFilter(sreq, sresp);
     * return; }
     * Для всех остальных запросов мы проверяем текущего пользователя. Если его нет,
     * то отправляем на страницу авторизации.
     * if (req.getSession().getAttribute("user") == null) {
     * resp.sendRedirect(req.getContextPath() + "/login.jsp");
     * return;
     * }
     *
     * @param sreq ServletRequest
     * @param sresp ServletResponse
     * @param chain FilterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sreq;
        HttpServletResponse resp = (HttpServletResponse) sresp;
        String uri = req.getRequestURI();
        if (uri.endsWith("auth.do") || uri.endsWith("reg.do")) {
            chain.doFilter(sreq, sresp);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        chain.doFilter(sreq, sresp);
    }
}

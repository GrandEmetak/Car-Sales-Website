package ru.job4j.cars.servlet;

import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.store.AdRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 /**
 * - @WebServlet(urlPatterns = "/title.do")
 * 2. CandidateServlet.
 * Создание Post (Супер объект, хранит в себе информацию Объявление-Машина-Владелец).
 * В методу doGet мы загружаем в request список объявлений.
 * req.setAttribute("posts", Store.instOf().findAllPosts());
 * Обратите внимание в методе doPost тоже изменен адрес.
 * resp.sendRedirect(req.getContextPath() + "/candidates.do"); -- was candidates.jsp
 * 3. Редактирование вакансии. [#277566 #207261]02
 * Уровень : 3. Мидл Категория : 3.2. Servlet JSP Топик : 3.2.3. Servlet
 * В этом уроке мы добавим возможность редактировать вакансию.
 * Последний элемент - это загрузка id в сервлет.
 * Integer.valueOf(req.getParameter("id")),
 * аннотацию @WebServlet(urlPattern = " маппинг имя")
 *
 * @author SlartiBartFast-art
 * @version 1
 * @since 26.11.21
 */
public class TitleServlet extends HttpServlet {
    private User userNew = null;

    /**
     * мы перенаправляем запрос в index.jsp.
     * req.getRequestDispatcher("index.jsp").forward(req, resp);
     * В методу doGet мы загружаем в request список вакансий.
     * req.setAttribute("posts", Store.instOf().findAllPosts());
     * открытый интерфейс ServletRequest
     * Определяет объект для предоставления сервлету информации о запросе клиента.
     * Контейнер сервлета создает ServletRequest объект и передает его в качестве
     * аргумента service методу сервлета .
     * Интерфейс RequestDispatcher предоставляет два метода. Они есть:
     * <p>
     * public void forward (запрос ServletRequest, ответ ServletResponse) выдает исключение ServletException,
     * java.io.IOException: перенаправляет запрос от сервлета к другому ресурсу
     * (сервлету, файлу JSP или файлу HTML) на сервере.
     * Если требуемый ресурс находится в том же контексте, что и сервлет, который его вызывает,
     * то для получения ресурса необходимо использовать метод
     * public RequestDispatcher getRequestDispatcher(String path);
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override // загрузка при запросе вывода этой страницы
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        var userid = session.getAttribute("user");
        System.out.println("Что за ID Юзера Get: " + userid);
        userNew = (User) userid;
        List<Post> postList = AdRepository.instOf().findAllPost();
        postList.stream().forEach(post -> post.setCreated(AdRepository.instOf().convertDays(post.getCreated())));

        req.setAttribute("posts", postList); // forEach str 88
        req.setAttribute("users", userNew);
        req.getRequestDispatcher("title.jsp").forward(req, resp);
    }
}

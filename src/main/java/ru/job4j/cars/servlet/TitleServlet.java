package ru.job4j.cars.servlet;

import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.store.PostRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Титульная страница - выводит объявления - (Post object),
 * (Супер объект, хранит в себе информацию Объявление-Машина-Владелец) всех авторов активные и нет
 * - @WebServlet(urlPatterns = "/title.do")
 * В методу doGet мы загружаем в request список объявлений.
 * req.setAttribute("posts", Store.instOf().findAllPosts());
 * аннотацию @WebServlet(urlPattern = " маппинг имя")
 *
 * @author SlartiBartFast-art
 * @since 26.11.21
 */
public class TitleServlet extends HttpServlet {
    private User userNew = null;

    /**
     * загрузка при запросе вывода этой страницы
     * В методу doGet мы загружаем в request список объявлений.
     * req.getRequestDispatcher("title.jsp").forward(req, resp);
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
     * для получения информации использоватть -
     * HttpSession session = req.getSession();
     * var userid = session.getAttribute("user");
     * System.out.println("Что за ID Юзера Get: " + userid);
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        var userid = session.getAttribute("user");

        userNew = (User) userid;
        List<Post> postList = PostRepository.instOf().findAllPost();
        postList.stream().forEach(post -> post.setCreated(PostRepository.instOf().convertDays(post.getCreated())));

        req.setAttribute("posts", postList);
        req.setAttribute("users", userNew);
        req.getRequestDispatcher("title.jsp").forward(req, resp);
    }
}

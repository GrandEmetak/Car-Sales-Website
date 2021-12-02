package ru.job4j.cars.servlet;

import ru.job4j.cars.model.Post;
import ru.job4j.cars.store.AdRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * - @WebServlet(urlPatterns = "/candidateT.do")
 * Серлет отвечает за посты объявлений за последне 24 часа
 * аннотация @WebServlet(urlPattern = " маппинг имя")
 * @author SlartiBartFast-art
 * @since 29.11.21
 */
public class CandidateTodayServlet extends HttpServlet {
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
     * для получения содержимого доавить в doGet postLis.stream().forEach(System.out::println);
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> postLis = AdRepository.instOf().lastDay();
        req.setAttribute("posts", postLis);
        postLis.stream().forEach(post -> post.setCreated(AdRepository.instOf().convertDays(post.getCreated())));

        req.getRequestDispatcher("candidateToday.jsp").forward(req, resp);
    }
}

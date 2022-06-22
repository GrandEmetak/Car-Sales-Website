package ru.job4j.cars.servlet;

import ru.job4j.cars.model.Post;
import ru.job4j.cars.store.PostRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * - @WebServlet(urlPatterns = "/candidateT.do")
 * Серлет отвечает за посты объявлений за последне 24 часа
 * аннотация @WebServlet(urlPattern = " маппинг имя")
 *
 * @author SlartiBartFast-art
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
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> postLis = PostRepository.instOf().lastDay();
        req.setAttribute("posts", postLis);
        postLis.stream().forEach(post -> post.setCreated(PostRepository.instOf().convertDays(post.getCreated())));

        req.getRequestDispatcher("candidateToday.jsp").forward(req, resp);
    }
}

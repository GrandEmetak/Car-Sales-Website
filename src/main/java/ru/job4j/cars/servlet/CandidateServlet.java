package ru.job4j.cars.servlet;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.store.AdRepository;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * - @WebServlet(urlPatterns = "/candidate.do")
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
public class CandidateServlet extends HttpServlet {
    private User userNew = null;

    /**
     *
     * В методе doGet мы загружаем в request список постов/объявлений.
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
     * req.setAttribute("posts", postList); // forEach str 88
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
        List<Post> postList = AdRepository.instOf().findPostByUserId(userNew.getId());

        postList.stream().forEach(post -> post.setCreated(AdRepository.instOf().convertDays(post.getCreated())));

        req.setAttribute("posts", postList);
        req.setAttribute("users", userNew);
        req.getRequestDispatcher("candidate.jsp").forward(req, resp);
    }

    /**
     * сюда попадают введенные данные из web/candidate/edit.jsp после валидации онклик()
     * то что было получено в форме изминения данных объявления
     * для проверки содержимого использовать
     * User userid = (User) session.getAttribute("user");
     * System.out.println("Что за ID Юзера Post: " + userid);
     * <p>
     * int postID = Integer.parseInt(req.getParameter("id"));
     * System.out.println("То что пришло по id-post : " + postID);
     * <p>
     * Post post = Post.of(req.getParameter("header"),
     * req.getParameter("description"),
     * req.getParameter("price"), bln);
     * System.out.println(" Те что пустые : " + req.getParameter("body")
     * + " " + req.getParameter("transm")
     * + " " +  req.getParameter("drive"));
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        boolean bln = Boolean.parseBoolean(req.getParameter("status"));
        HttpSession session = req.getSession();
        User userid = (User) session.getAttribute("user");

        int postID = Integer.parseInt(req.getParameter("id"));

        Post post = Post.of(req.getParameter("header"),
                req.getParameter("description"),
                req.getParameter("price"), bln);

        Car car = Car.of(req.getParameter("mark"),
                req.getParameter("body"),
                req.getParameter("engine"),
                req.getParameter("transm"),
                req.getParameter("color"),
                req.getParameter("drive"),
                req.getParameter("mileage")
        );
        var post1 = AdRepository.instOf().findPostBiId(postID).get(0);

        post1.setHeader(post.getHeader());
        post1.setDescription(post.getDescription());
        post1.setPrice(post.getPrice());
        post1.setStatus(post.isStatus());
        post1.addCar(car);

        AdRepository.instOf().savePost(post1);

        resp.sendRedirect(req.getContextPath() + "/candidate.do");
    }
}

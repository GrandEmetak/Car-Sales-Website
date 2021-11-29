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
        List<Post> postList =  AdRepository.instOf().findAllPost(); // forEach str 88
        List<Post> afterList = new ArrayList<>();
        for (Post post : postList) {
            var d = post.getCreated();
            post.setCreated(AdRepository.instOf().convertDays(d));
//           var st = post.getStatus();
//            System.out.println("Candidate  Post status : -" + post.getStatus());
//            var gt = AdRepository.instOf().convertStatus(st);
//            System.out.println("Candidate  Post status : -" + gt);
//           post.setStatus(gt);
             afterList.add(post);
        }
//        req.setAttribute("posts", AdRepository.instOf().findAllPost()); // forEach str 88
        req.setAttribute("posts", afterList); // forEach str 88
        req.getRequestDispatcher("candidate.jsp").forward(req, resp);
    }

    @Override//сюда прилетает введенные данные из web/candidate/edit.jsp после валидации онклик()
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        boolean bln = Boolean.parseBoolean(req.getParameter("status"));
//        String bln = req.getParameter("status");

        int postID = Integer.parseInt(req.getParameter("id"));
        System.out.println("То что пришло по id-post : " + postID);
        // сохранение в Бд Поста или машины?
        Post post = Post.of(req.getParameter("header"),
                req.getParameter("description"),
                req.getParameter("price"), bln);
//        req.getParameter("price"), bln);
        Car car = Car.of(req.getParameter("mark"),
                req.getParameter("color"),
                req.getParameter("validation1"),
                req.getParameter("validation3"),
                req.getParameter("validation4"),
                req.getParameter("validation5"),
                req.getParameter("mileage")
        );
        User user = null;
        user = AdRepository.instOf().findUserById(postID).get(0);

        post.addCar(car);
        post.addUser(user);

        AdRepository.instOf().savePost(post);

        resp.sendRedirect(req.getContextPath() + "/candidate.do");
    }
}

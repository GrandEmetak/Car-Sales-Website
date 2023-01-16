package com.carsales.servlet;

import com.carsales.model.Car;
import com.carsales.model.Photo;
import com.carsales.model.User;
import com.carsales.model.Post;
import com.carsales.store.PostRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * /postnew.do
 * Сервлет отвечает за создание новых постов -
 * Post Object - (объявлений для зарегестрированных пользователей)
 * аннотация @WebServlet(urlPattern = " маппинг имя")
 *
 * @author SlartiBartFast-art
 * @since 29.11.21
 */
public class PostServlet extends HttpServlet {

    /**
     * В данный метод, попадают введенные данные из web/candidate/postnew.jsp после валидации онклик()
     * происходит запись в БД и перенаправдение на страницу с осталльными постами объявлений пользователя
     * для проверки содержимого использовать -
     * HttpSession session = req.getSession();
     * User userid = (User) session.getAttribute("user");
     * System.out.println("Что за ID Юзера Post: " + userid);
     * var desc1 = (String) req.getParameter("body");
     * var desc2 = (String) req.getParameter("transm");
     * var desc3 = req.getParameter("drive");
     * System.out.println("Описание сервлет ПостСервел : "
     * + desc1 + " " + desc2 + " " + desc3);
     * var t = AdRepository.instOf().savePost(post);
     * System.out.println("PostServlet save " + t);
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
        String fht = (String) req.getParameter("header");
        Photo photo = Photo.of(fht + ".jpg");
        post.addUser(userid);
        post.addCar(car);
        post.addPhoto(photo);
        PostRepository.instOf().savePost(post);

        resp.sendRedirect(req.getContextPath() + "/candidate.do");
    }
}

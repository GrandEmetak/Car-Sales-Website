package com.carsales.servlet;

import com.carsales.model.Car;
import com.carsales.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.carsales.model.Post;
import com.carsales.store.PostRepository;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * - @WebServlet(urlPatterns = "/candidate.do")
 * аннотацию @WebServlet(urlPattern = " маппинг имя")
 *
 */
public class CandidateServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(CandidateServlet.class.getName());

    private User userNew = null;

    /**
     * Метод doGet загружаем в request список постов/объявлений.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        var userid = session.getAttribute("user");

        userNew = (User) userid;
        List<Post> postList = PostRepository.instOf().findPostByUserId(userNew.getId());

        postList.stream().forEach(post -> post.setCreated(PostRepository.instOf().convertDays(post.getCreated())));
        LOGGER.info("List -> " + postList);
        req.setAttribute("posts", postList);
        req.setAttribute("users", userNew);
        req.getRequestDispatcher("candidate.jsp").forward(req, resp);
    }

    /**
     * В этот метод, попадают введенные данные из web/candidate/edit.jsp
     * после валидации онклик()
     * то что было получено в форме изминения данных объявления
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
        var post1 = PostRepository.instOf().findPostBiId(postID).get(0);

        post1.setHeader(post.getHeader());
        post1.setDescription(post.getDescription());
        post1.setPrice(post.getPrice());
        post1.setStatus(post.isStatus());
        post1.addCar(car);

        PostRepository.instOf().savePost(post1);

        resp.sendRedirect(req.getContextPath() + "/candidate.do");
    }
}

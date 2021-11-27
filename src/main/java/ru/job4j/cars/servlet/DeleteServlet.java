package ru.job4j.cars.servlet;

import ru.job4j.cars.store.AdRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * -@WebServlet(urlPatterns = "/delete.do")
 * Серлет проводит действия по удалению Post
 */
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("posts", AdRepository.instOf().findAllPost());
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

    /**
     * удаление кандидата из БД
     * int id = PsqlStore.instOf().deleteCandidateId(Integer.parseInt(req.getParameter("id"))).getId();
     * удаление пути к рисунку
     * new File("c\\cars\\" + name.getName()).delete();
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var s = (req.getParameter("id"));
         AdRepository.instOf().deletePostId(Integer.parseInt(s));
        for (File name : new File("c:\\cars\\").listFiles()) {
            System.out.println("path : " + name);
            if (name.getName().startsWith(s)) {
                new File("c\\cars\\" + name.getName()).delete();
            }
        }
        doGet(req, resp);
    }
}


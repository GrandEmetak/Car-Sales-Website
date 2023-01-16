package com.carsales.servlet;

import com.carsales.store.PostRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * -@WebServlet(urlPatterns = "/delete.do")
 * Серлет проводит действия по удалению Post Object - глобальный объект,
 * хранящий все данные
 *
 * @author SlartiBartFast-art
  */
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("posts", PostRepository.instOf().findAllPost());
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

    /**
     * удаление кандидата(Post Object) из БД
     * AdRepository.instOf().deletePostId(Integer.parseInt(s));
     * проверить путь -   for (File name : new File("c:\\cars\\").listFiles()) {
     * System.out.println("path : " + name);
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
        PostRepository.instOf().deletePostId(Integer.parseInt(s));
        for (File name : new File("c:\\cars\\").listFiles()) {
            if (name.getName().startsWith(s)) {
                new File("c\\cars\\" + name.getName()).delete();
            }
        }
        doGet(req, resp);
    }
}


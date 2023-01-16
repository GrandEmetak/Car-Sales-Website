package com.carsales.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.carsales.store.PostRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * -@WebServlet(urlPatterns = "/upload")
 * Загрузка и скачивание файла.
 * Это servlet будет обрабатывать загрузку файла на сервер.
 * аннотация @WebServlet(urlPattern = " маппинг имя")
 *
 * @author SlartiBartFast-art
 */
public class UploadServlet extends HttpServlet {
    /**
     * Передает по ключу candidates данные полученные в MemStore.instOf().findAllCandidates())
     * перенаправляет на страницу candidates.do
     * AdRepository.instOf().findAllPost()) - показать все объявления из БД posts
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("candidates", PostRepository.instOf().findAllPost());
        String name = req.getParameter("id") + ".png";
        System.out.println("urlPatterns = \"/upload\")\n"
                + "public class UploadServlet   NAME + " + name);
        resp.sendRedirect(req.getContextPath() + "/candidate.do");
    }

    /**
     * Метод doPost загружает выбранный файл на сервер в папку c:\cars\
     * чтобы посмотреть седержимое List<FileItem> items
     * System.out.println("what is come : " + item);
     * + также System.out.println("d String : " + d[0] + " " + d[1]);
     * h = s + "." + d[1];
     * System.out.println("in the end :" + h);
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String s = req.getParameter("id");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        String h = null;
        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                var f = item.toString().split(",");
                var g = f[0].split("=");
                var d = g[1].split("\\.");
                h = s + "." + d[1];
            }
            File folder = new File("c:\\cars\\");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(folder + File.separator + h);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }
}

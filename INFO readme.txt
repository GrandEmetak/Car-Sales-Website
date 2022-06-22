    public class AuthFilter implements Filter
/**
 * - @WebFilter(urlPatterns = "*.do")
 * Filter
 *
 * на страницу posts.do может переходить только авторизованный пользователь.
 *  мы будем фильтровать все запросы.
 * Если запрос не связан с пользователем, то будем перенаправлять такой запрос на страницу авторизации.
 * Чтобы этого добиться в Java используется javax.servlet.Filter интерфейс.
 * Так же вспомните принцип единой ответственности SRP.
 * Фильтр должен выполнять задачи для одной группы пользователей.
 * Все запросы с расширением *.do будут обрабатываться нашим фильтром.
 * В AuthFilter добавьте игнорировние сервлета reg.do.
 * <filter-mapping>
 * <filter-name>AuthFilter</filter-name>
 * <url-pattern>*.do</url-pattern>В url-pattern можно указать полный путь или маску поиска.
 * </filter-mapping>
 * полный путь или маску поиска * - все запросы.
 * Здесь используется маска *.do. *.do - это дань старому фреймворку Struts.
 */

 public class Car
 /**
  * Модель данных описывающая Автомобиль
  * Реализовать площадку продаж машин. [#4747]
  * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.2. Mapping
  * - Спроектируйте SQL схему для сайта по продажам машин.
  * - Добавьте POJO классы и hibernate mapping. В этом задании нужно использовать аннотации.
  * - Загрузите схему в корень репозитория в папку db.
  *
  * @author SlartiBartFast-art
  * @version 01
  * @since 22.11.21
  */


 public class Photo
  /**
  * Модель данных изображения для объявления(Post)
  * Реализовать площадку продаж машин. [#4747]
  * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.2. Mapping
  * - Спроектируйте SQL схему для сайта по продажам машин.
  * - Добавьте POJO классы и hibernate mapping. В этом задании нужно использовать аннотации.
  * - Загрузите схему в корень репозитория в папку db.
  * @author SlartiBartFast-art
  * @version 01
  * @since 24.11.21
  */

  public class Post
  /**
   * Модель данных описывающая объявление(Post)
   * Реализовать площадку продаж машин. [#4747]
   * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.2. Mapping
   * - Спроектируйте SQL схему для сайта по продажам машин.
   * - Добавьте POJO классы и hibernate mapping. В этом задании нужно использовать аннотации.
   * - Загрузите схему в корень репозитория в папку db.
   *
   * @author SlartiBartFast-art
   * @version 01
   * @since 22.11.21
   */

   public class User
   /**
    * Модель данных описывает пользователя/владельца объявления(Post)
    * Реализовать площадку продаж машин. [#4747]
    * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.2. Mapping
    * - Спроектируйте SQL схему для сайта по продажам машин.
    * - Добавьте POJO классы и hibernate mapping. В этом задании нужно использовать аннотации.
    * - Загрузите схему в корень репозитория в папку db.
    *
    * @author SlartiBartFast-art
    * @version 01
    * @since 22.11.21
    */

    public class DownloadServlet extends HttpServlet
     /**
         * - @WebServlet(urlPatterns = "/download")
         * 1. Загрузка и скачивание файла.
         * Это servlet будет отдавать файл, который лежит на сервере.
         * ознакомеление с возможностью servlet загружать файлы на сервер и отдавать их клиенту.
         * Http протокол позволяет передавать файлы между клиентом и сервером.
         * - @WebServlet(urlPattern = " маппинг имя")
         *
         * @author SlartiBartFast-art
         */


  public class AuthFilter implements Filter {

  }


  public class CandidateServlet extends HttpServlet {
   * - @WebServlet(urlPatterns = "/candidate.do")
   * CandidateServlet - Создание Post Object - это,
   *  (Супер объект, хранит в себе информацию Объявление-Машина-Владелец).
   * В методу doGet мы загружаем в request список объявлений.
   * req.setAttribute("posts", Store.instOf().findAllPosts());
   * Последний элемент - это загрузка id в сервлет.
   * Integer.valueOf(req.getParameter("id")),
   * аннотацию @WebServlet(urlPattern = " маппинг имя")
   *
   * @author SlartiBartFast-art


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
        * req.setAttribute("posts", postList); - forEach str 88
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
          }

           /**
                * В этот метод, попадают введенные данные из web/candidate/edit.jsp после валидации онклик()
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
       }
  }

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
       * для получения содержимого доБавить в doGet postLis.stream().forEach(System.out::println);
       *
       * @param req HttpServletRequest
       * @param resp HttpServletResponse
       * @throws ServletException
       * @throws IOException
       */
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        }
  }

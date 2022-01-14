package ru.job4j.cars.temp;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.store.PostRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * класс представляет из себя тест работы методов в основном представленнных в PostRepository
 */
public class Temp {
    public static Date subtractDays() {
        Date date = new Date(System.currentTimeMillis());
        int days = 1;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    public static void main(String[] args) {
        System.out.println("Data day - one: " + subtractDays());
        PostRepository adRepository = new PostRepository();
 /*       Post post = adRepository.findPostBiId(1).get(0);
        System.out.println("adRepository.findPostBiId(1).get(0) - Post : " + post);
        var ft = post.getCreated();
        var t = adRepository.convertDays(ft);
        System.out.println("Data Gregor calendar : " + t);

        var st = adRepository.convertStatus(post.getStatus());
        System.out.println("Post staus : " + post.getStatus());
        System.out.println("Convert boolean status - : " + st);*/

        User user = User.of("John Smith", "SmithJohn@gmail.com", "John");
        Post post = Post.of("Lexus RX200t, 2016 год",
                "- Климат-контроль 2-зонный, Обогрев рулевого колеса, Подогрев передних сидений,"
                        + " Электрообогрев лобового стекла,"
                        + " Электроскладывание зеркал, Система доступа без ключа, Светодиодные фары",
                "3 649 000 ₽",
                false
        );
        Car car = Car.of("Lexus RX200t",
                "SUV",
                "бензин, 2.0 л., 238 л.с., налог ",
                "АКПП",
                "белый",
                "4WD",
                "107 432"
        );
        Photo photoF = Photo.of("Lexus RX200t.jpg");

        post.addUser(user);
        post.addCar(car);
        post.addPhoto(photoF);
        /* System.out.println("Id new Post Object : " + post.getId());*/
        var rsl =  adRepository.findAllPost();
        rsl.stream().forEach(System.out::println);

      var savePost = adRepository.savePost(post);
        System.out.println("То что сохранили в БД Объект пост : " + savePost);

   /*     var lasD = adRepository.lastDay();
        for (Post post1 : lasD) {
            System.out.println("ТО что нашли за последние день : " + post1);
        }
        var photo = adRepository.whenPhotoTrue();
        for (Post post1 : photo) {
            System.out.println("То что нашли объявления с фото : " + post1);
        }*/

 /*       var makrAuto = adRepository.whenMarkAuto("Kia Sportage");
        for (Post post1 : makrAuto) {
            System.out.println("То что нашли объявления по марке авто : " + post1);
        }*/

  /*      var emailFind = adRepository.findByEmail("root@local");
        for (User user1 : emailFind) {
            System.out.println("То что нашли user email : " + user1);
        }*/

/*        var userBuID = AdRepository.instOf().findUserById(1);
        for (User user1 : userBuID) {
            System.out.println("То что нашли User by ID : " + user1);
        }*/
/*        var postById = adRepository.findPostBiId(2);
        for (Post post1 : postById) {
            System.out.println("То что нашли объявления с фото : " + post1);
        }*/
 /*       var listPost = adRepository.findPostByUserId(1);
        listPost.stream().forEach(System.out::println);*/

       /*var listPost  = adRepository.findPostByUserIdAndHeader(1, "Продажа Porsche Cayenne, 2015 год ");
        listPost.stream().forEach(System.out::println);*/

  /*     var listPost  = adRepository.findPostByUserIdAndHeader(1, "Продажа Porsche Cayenne, 2015 год ").get(0);
        System.out.println("ТО что нашли в БД по id and Header : " + listPost);
        listPost.setStatus(true);
        listPost.setPrice("100500 rub");
        listPost.getCar().setBodyType("wagon superT");
        var rsl = adRepository.savePost(listPost);
        System.out.println("ТО что получили после сохранения Post : " + rsl);*/
    }
}

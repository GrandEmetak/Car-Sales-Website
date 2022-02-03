package ru.job4j.cars.store;

import org.junit.Test;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Photo;

import java.util.List;

import static org.junit.Assert.*;

public class PostRepositoryTest {

    @Test
    public void instOf() {
    }

    @Test
    public void savePost() {

        PostRepository postRepository = new PostRepository();

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

        var savePost = postRepository.savePost(post);
        assertEquals(post.getDescription(), savePost.getDescription()
        );
    }

    @Test
    public void savePhoto() {
        PostRepository postRepository = new PostRepository();
        Photo photoF = Photo.of("Lexus RX200t.jpg");
       Photo rsl = postRepository.savePhoto(photoF);
        assertEquals(photoF.getImageName(), rsl.getImageName());
    }

    @Test
    public void findAllPost() {
        PostRepository postRepository = new PostRepository();

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

        var savePost = postRepository.savePost(post);

        var rsl =  postRepository.findAllPost();

        assertEquals(post.getHeader(), rsl.get(0).getHeader());

    }

    @Test
    public void lastDay() {
        PostRepository postRepository = new PostRepository();

        User user = User.of("Jona Hill", "JohnaHill@gmail.com", "Jona");
        Post post = Post.of("Ford F500, 2019 год",
                "- Климат-контроль 2-зонный, Обогрев рулевого колеса, Подогрев передних сидений,",
                "5 750 000 ₽",
                false
        );
        Car car = Car.of("Ford F500",
                "TRUCK",
                "бензин, 3.5 л., 238 л.с., налог ",
                "АКПП",
                "белый",
                "4WD",
                "17 432"
        );
        Photo photoF = Photo.of("Ford 500.jpg");

        post.addUser(user);
        post.addCar(car);
        post.addPhoto(photoF);

        var savePost = postRepository.savePost(post);

        var rsl =  postRepository.findAllPost();
        rsl.stream().forEach(System.out::println);

        assertEquals(post.getHeader(), rsl.get(0).getHeader());
    }

    @Test
    public void whenPhotoTrue() {
        PostRepository postRepository = new PostRepository();

        User user = User.of("Jona Hill", "JohnaHill@gmail.com", "Jona");
        Post post = Post.of("Ford F500, 2019 год",
                "- Климат-контроль 2-зонный, Обогрев рулевого колеса, Подогрев передних сидений,",
                "5 750 000 ₽",
                false
        );
        Car car = Car.of("Ford F500",
                "TRUCK",
                "бензин, 3.5 л., 238 л.с., налог ",
                "АКПП",
                "белый",
                "4WD",
                "17 432"
        );
        Photo photoF = Photo.of("Ford 500.jpg");

        post.addUser(user);
        post.addCar(car);
        post.addPhoto(photoF);
        Post savePostwhithFoto = postRepository.savePost(post);

        User user1 = User.of("John Smith", "SmithJohn@gmail.com", "John");
        Post post1 = Post.of("Lexus RX200t, 2016 год",
                "- Климат-контроль 2-зонный, Обогрев рулевого колеса, Подогрев передних сидений,"
                        + " Электрообогрев лобового стекла,"
                        + " Электроскладывание зеркал, Система доступа без ключа, Светодиодные фары",
                "3 649 000 ₽",
                false
        );
        Car car1 = Car.of("Lexus RX200t",
                "SUV",
                "бензин, 2.0 л., 238 л.с., налог ",
                "АКПП",
                "белый",
                "4WD",
                "107 432"
        );
        Photo photoF1 = null;

        post1.addUser(user1);
        post1.addCar(car1);
        post1.addPhoto(null);

        var savePost = postRepository.savePost(post1);

        var rsl =  postRepository.whenPhotoTrue();

        assertEquals(savePostwhithFoto.getHeader(), rsl.get(0).getHeader());
    }

    @Test
    public void whenMarkAuto() {
        PostRepository postRepository = new PostRepository();

        User user = User.of("Jona Hill", "JohnaHill@gmail.com", "Jona");
        Post post = Post.of("Ford F500, 2019 год",
                "- Климат-контроль 2-зонный, Обогрев рулевого колеса, Подогрев передних сидений,",
                "5 750 000 ₽",
                false
        );
        Car car = Car.of("Ford F500",
                "TRUCK",
                "бензин, 3.5 л., 238 л.с., налог ",
                "АКПП",
                "белый",
                "4WD",
                "17 432"
        );
        Photo photoF = Photo.of("Ford 500.jpg");

        post.addUser(user);
        post.addCar(car);
        post.addPhoto(photoF);
        Post savePostwhithFoto = postRepository.savePost(post);
        List<Post> resultList = postRepository.whenMarkAuto("Ford F500");
        assertEquals(savePostwhithFoto.getHeader(), resultList.get(0).getHeader());
    }

    @Test
    public void findPostById() {
        PostRepository postRepository = new PostRepository();

        User user = User.of("Jona Hill", "JohnaHill@gmail.com", "Jona");
        Post post = Post.of("Ford F500, 2019 год",
                "- Климат-контроль 2-зонный, Обогрев рулевого колеса, Подогрев передних сидений,",
                "5 750 000 ₽",
                false
        );
        Car car = Car.of("Ford F500",
                "TRUCK",
                "бензин, 3.5 л., 238 л.с., налог ",
                "АКПП",
                "белый",
                "4WD",
                "17 432"
        );
        Photo photoF = Photo.of("Ford 500.jpg");

        post.addUser(user);
        post.addCar(car);
        post.addPhoto(photoF);
        Post savePost = postRepository.savePost(post);
        List<Post> resultList = postRepository.findPostBiId(1);
        assertEquals(savePost.getHeader(), resultList.get(0).getHeader());
    }

    @Test
    public void findPostByUserId() {
        PostRepository postRepository = new PostRepository();

        User user = User.of("Jona Hill", "JohnaHill@gmail.com", "Jona");
        Post post = Post.of("Ford F500, 2019 год",
                "- Климат-контроль 2-зонный, Обогрев рулевого колеса, Подогрев передних сидений,",
                "5 750 000 ₽",
                false
        );
        Car car = Car.of("Ford F500",
                "TRUCK",
                "бензин, 3.5 л., 238 л.с., налог ",
                "АКПП",
                "белый",
                "4WD",
                "17 432"
        );
        Photo photoF = Photo.of("Ford 500.jpg");

        post.addUser(user);
        post.addCar(car);
        post.addPhoto(photoF);
        Post savePost = postRepository.savePost(post);
        List<Post> resultList = postRepository.findPostBiId(savePost.getId());
        assertEquals(savePost, resultList.get(0));
    }

    @Test
    public void findPostByUserIdAndHeader() {
        PostRepository postRepository = new PostRepository();

        User user = User.of("Jona Hill", "JohnaHill@gmail.com", "Jona");
        Post post = Post.of("Ford F500, 2019 год",
                "- Климат-контроль 2-зонный, Обогрев рулевого колеса, Подогрев передних сидений,",
                "5 750 000 ₽",
                false
        );
        Car car = Car.of("Ford F500",
                "TRUCK",
                "бензин, 3.5 л., 238 л.с., налог ",
                "АКПП",
                "белый",
                "4WD",
                "17 432"
        );
        Photo photoF = Photo.of("Ford 500.jpg");

        post.addUser(user);
        post.addCar(car);
        post.addPhoto(photoF);
        Post savePost = postRepository.savePost(post);
        List<Post> resultList = postRepository.findPostByUserIdAndHeader(savePost.getId(), post.getHeader());
        assertEquals(savePost, resultList.get(0));
    }

    @Test
    public void deletePostId() {
        PostRepository postRepository = new PostRepository();

        User user = User.of("Jona Hill", "JohnaHill@gmail.com", "Jona");
        Post post = Post.of("Ford F500, 2019 год",
                "- Климат-контроль 2-зонный, Обогрев рулевого колеса, Подогрев передних сидений,",
                "5 750 000 ₽",
                false
        );
        Car car = Car.of("Ford F500",
                "TRUCK",
                "бензин, 3.5 л., 238 л.с., налог ",
                "АКПП",
                "белый",
                "4WD",
                "17 432"
        );
        Photo photoF = Photo.of("Ford 500.jpg");

        post.addUser(user);
        post.addCar(car);
        post.addPhoto(photoF);
        Post savePost = postRepository.savePost(post);
        boolean d = postRepository.deletePostId(savePost.getId());
        assertEquals(true, d);
    }
}


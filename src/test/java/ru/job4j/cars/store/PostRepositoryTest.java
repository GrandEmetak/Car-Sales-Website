package ru.job4j.cars.store;

import org.junit.Test;
import ru.job4j.cars.model.Post;

import java.util.List;

import static org.junit.Assert.*;

public class PostRepositoryTest {

    @Test
    public void instOf() {
    }

    @Test
    public void savePost() {
    }

    @Test
    public void savePhoto() {

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
        /* System.out.println("Id new Post Object : " + post.getId());*/

        var savePost = postRepository.savePost(post);
        System.out.println("То что сохранили в БД Объект пост : " + savePost);
        var rsl =  postRepository.findAllPost();
//        assertThat(rsl.size(), is(1));
//        assertThat(rsl.get(0).getDescription(),
//                is("- Климат-контроль 2-зонный, Обогрев рулевого колеса, Подогрев передних сидений,"
//                + " Электрообогрев лобового стекла,"
//                + " Электроскладывание зеркал, Система доступа без ключа, Светодиодные фары"));
//        assertThat(rsl.get(0).getId(), is(1));
        assertEquals(post, rsl.get(0));

    }

    @Test
    public void lastDay() {
    }

    @Test
    public void whenPhotoTrue() {
    }

    @Test
    public void whenMarkAuto() {
    }

    @Test
    public void findPostBiId() {
    }

    @Test
    public void findPostByUserId() {
    }

    @Test
    public void findPostByUserIdAndHeader() {
    }

    @Test
    public void deletePostId() {
    }

    @Test
    public void convertDays() {
    }
}
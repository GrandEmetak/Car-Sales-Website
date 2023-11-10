package com.ohathaway.store;

import com.ohathaway.model.entity.Image;
import com.ohathaway.model.entity.Post;

import java.util.List;

/**
 * Интерфейс, общие методы для работы с сущностями
 * - показать объявления за последний день
 * - показать объявления с фото
 * - показать объявления определенной марки.
 * - поиск пользователей по email
 *  - и др.
  * @since 26.11.21
 */
public interface Store {

    public Post savePost(Post post);

    public List<Post> findAllPost();

    public List<Post> lastDay();

    public List<Post> whenPhotoTrue();

    public List<Post> whenMarkAuto(String name);

    public List<Post> findPostBiId(int id);

    public boolean deletePostId(int id);

    public Image savePhoto(Image image);

    public List<Post> findPostByUserId(int id);

    public List<Post> findPostByUserIdAndHeader(int id, String header);

}

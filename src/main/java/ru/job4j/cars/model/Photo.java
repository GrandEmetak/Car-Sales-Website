package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

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
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imageName;

    /*@Lob
    private byte[] image;*/

    public Photo() {
    }

    public static Photo of(String name, byte[] image) {
        Photo photo = new Photo();
        photo.imageName = name;
       /* photo.image = image;*/
        return photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

  /*  public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Photo{"
                + "id=" + id
                + ", imageName='" + imageName + '\''
                + '}';
    }
}

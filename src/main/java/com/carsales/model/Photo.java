package com.carsales.model;

import javax.persistence.*;
import java.util.Objects;

 /**
 * Модель данных изображения для объявления(Post)
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

    public static Photo of(String name) {
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

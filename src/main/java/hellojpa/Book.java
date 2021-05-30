package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") // 자식 테이블의 타입명 정의 가능
public class Book extends Item{

    private String author;

    private String isbn;
}

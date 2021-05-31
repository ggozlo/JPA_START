package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

  @Id @GeneratedValue
  private Long id;

  private String name;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
  // 영속성 전이 - 해당 클래스가 영속상태가 변경될때 해당 객체에 참조되어 있고 casecade 가 활성화된 객체들에 영속상태 변화가 전이됨
  // 고아 객체 - 부모객체와 관계가 끊어지면 삭제쿼리 발생시킴
  private List<Child> childList = new ArrayList<>();

  public void addChild(Child child){
      childList.add(child);
      child.setParent(this);
  }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

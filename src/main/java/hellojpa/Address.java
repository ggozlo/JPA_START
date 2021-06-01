package hellojpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable // 내장객체 지정
public class Address {
    private String city;
    private String street;
    @Column(name = "ZIP")
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }
    // 임베디드 타입이 참조 필드를 가진다면 임베디드타입간의 동등성 검사를 위해선 equals 메서드를 재정의 해야함

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
    // 컬렉션 을 위해서 hash코드도 재정이 함이 좋음

    public Address() {
    }

    public String getCity() {
        return city;
    }


    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}

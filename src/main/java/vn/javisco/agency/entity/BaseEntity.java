package vn.javisco.agency.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;


@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
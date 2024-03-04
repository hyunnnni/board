package com.green.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends CreatedAtEntity{
    @LastModifiedDate // 엔티티가 수정될 때 시간이 자동 저장
    @JsonIgnore
    private LocalDateTime updatedAt;
}

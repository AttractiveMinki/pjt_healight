package com.ssafy.kiwi.model.domain.authentication.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Table(name = "refresh_token")
@Entity
public class RefreshToken {

    @Id
    private String id;
    private String token;

    public RefreshToken updateValue(String token) {
        this.token = token;
        return this;
    }

    @Builder
    public RefreshToken(String keys, String values) {
        this.id = keys;
        this.token = values;
    }
}
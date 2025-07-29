package com.example.springdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage> {

    @Id
    private String uuid;
    private String fileName;
    private int ord;

    @ManyToOne
    private Board board;

    @Override
    public int compareTo(BoardImage other) {
        return this.ord - other.ord;
    }

    public void changeBoard(Board board) {
        this.board = board;
    }
}

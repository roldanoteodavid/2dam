package main;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
class Room {
    private String id;
    private List<Door> doorList;
    private String description;
}

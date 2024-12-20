package timetogether.when2meet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Users {
    private final String userName;
    private final List<Days> days;
}

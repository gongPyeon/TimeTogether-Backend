package timetogether.group.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupAddDatesRequestDto {
  private String groupName;
  private String groupTimes;

  @Builder
  public GroupAddDatesRequestDto(String groupName,String groupTimes) {
    this.groupName = groupName;
    this.groupTimes = groupTimes;
  }
}

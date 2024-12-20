package timetogether.group.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import timetogether.group.Group;
import timetogether.groupMeeting.MeetType;

@Getter
@NoArgsConstructor
public class GroupCreateResponseDto {
  private Long groupId;
  private String groupName;
  private String groupImg;
  private String groupMgrId;
  private String groupTimes;
  private String groupIntro;
  private String groupurl;

  @Builder
  public GroupCreateResponseDto(Long groupId, String groupName, String groupImg, String groupMgrId, String groupIntro,String groupTimes, String groupurl) {
    this.groupId = groupId;
    this.groupName = groupName;
    this.groupImg = groupImg;
    this.groupMgrId = groupMgrId;
    this.groupIntro = groupIntro;
    this.groupTimes = groupTimes;
    this.groupurl = groupurl;
  }

  public static GroupCreateResponseDto from(Group savedGroup) {
    return GroupCreateResponseDto.builder()
            .groupId(savedGroup.getId())
            .groupName(savedGroup.getGroupName())
            .groupImg(savedGroup.getGroupImg())
            .groupMgrId(savedGroup.getGroupMgrId())
            .groupIntro(savedGroup.getGroupIntro())
            .groupTimes(savedGroup.getGroupTimes())
            .groupurl(savedGroup.getGroupUrl())
            .build();
  }
}
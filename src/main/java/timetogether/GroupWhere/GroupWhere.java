package timetogether.GroupWhere;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import timetogether.group.Group;
import timetogether.groupMeeting.GroupMeeting;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "group_where")
public class GroupWhere {
  @Column(name = "groupWhere_id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "group_id")
  private Group group;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "groupMeet_id")
  private GroupMeeting groupMeeting;

  private String groupWhereName;
  private String groupWhereUrl;
  private int count = 0;
  private boolean chooseThis = false;

  @Builder
  public GroupWhere(String groupWhereName,String groupWhereUrl,Group group,GroupMeeting groupMeeting) {
    this.groupWhereName = groupWhereName;
    this.groupWhereUrl = groupWhereUrl;
    this.group = group;
    this.groupMeeting = groupMeeting;
  }

  public void addCount(){
    this.count++;
  }
  public void doneChooseThis(){
    this.chooseThis = true;
  }
}
package timetogether.groupMeeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import timetogether.group.Group;
import timetogether.groupMeeting.GroupMeeting;
import timetogether.meeting.Meeting;
import timetogether.oauth2.entity.User;

import java.util.List;
import java.util.Optional;

public interface GroupMeetingRepository extends JpaRepository<GroupMeeting, Long> {
    @Override
    Optional<GroupMeeting> findById(Long groupMeetId);

    GroupMeeting findByGroupAndGroupMeetingTitleAndUser(Group group, String groupMeetingTitle, User user);
    GroupMeeting findByGroupAndGroupMeetingTitle(Group group, String groupMeetingTitle);
    List<GroupMeeting> findByGroupMeetingTitle(String groupMeetingTitle);

    List<GroupMeeting> findByGroupId(Long groupId);
}

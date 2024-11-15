package timetogether.group.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import timetogether.group.Group;
import timetogether.group.dto.GroupShowResponseDto;
import timetogether.group.dto.UserNameResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GroupQueryRepository {
  @PersistenceContext
  EntityManager entityManager;
  public Optional<Group> findByGroupNameAndIsMgr(String socialId, String groupName) {
    String query = "SELECT m FROM Group m " +
            "where m.groupName =: groupName " +
            "AND m.groupMgrId =: socialId";
    try{
      Group result = entityManager.createQuery(query, Group.class)
              .setParameter("groupName" , groupName)
              .setParameter("socialId", socialId)
              .getSingleResult();
      return Optional.ofNullable(result);
    }catch(NoResultException e){
      return Optional.empty();
    }
  }

  public Optional<String> findById(Long groupId) {
    String query = "SELECT m.groupUrl FROM Group m " +
            "WHERE m.id =: groupId";
    try {
      String result = entityManager.createQuery(query, String.class)
              .setParameter("groupId", groupId)
              .getSingleResult();
      return Optional.ofNullable(result);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  public Optional<Group> findByGroupId(Long groupId) {
    String query = "SELECT m FROM Group m "
            + "where m.id =: groupId";
    try {
      Group result = entityManager.createQuery(query, Group.class)
              .setParameter("groupId", groupId)
              .getSingleResult();
      return Optional.ofNullable(result);
    } catch (NoResultException e) {
      return Optional.empty();
    }

  }

  public List<GroupShowResponseDto> findGroupsWhereSocialIdIn(String socialId) {
    // 먼저 Group 정보를 조회
    String query = "SELECT u FROM User u " +
            "JOIN g.groupUserList u " +
            "WHERE u.socialId = :socialId " +
            "OR g.groupMgrId = :socialId";

    List<Group> groups = entityManager.createQuery(query, Group.class)
            .setParameter("socialId", socialId)
            .getResultList();

    // Group 정보를 DTO로 변환
    return groups.stream()
            .map(group -> new GroupShowResponseDto(
                    group.getId(),
                    group.getGroupName(),
                    group.getGroupImg(),
                    group.getGroupMgrId(),
                    // User 리스트를 UserNameResponse로 변환
                    group.getGroupUserList().stream()
                            .map(user -> new UserNameResponse(user.getUserName()))
                            .collect(Collectors.toList())
            ))
            .collect(Collectors.toList());
  }

  public Optional<Long> findGroupIdByGroupUrl(String groupUrl) {
    String query = "SELECT m.id FROM Group m "
            + "where m.groupUrl =: groupUrl";
    try {
      Long result = entityManager.createQuery(query, Long.class)
              .setParameter("groupUrl", groupUrl)
              .getSingleResult();
      return Optional.ofNullable(result);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  public List<Group> findGroupsByManagerId(String socialId) {
    String query = "SELECT g FROM Group g " +
            "WHERE g.groupMgrId = :socialId";

    return entityManager.createQuery(query, Group.class)
            .setParameter("socialId", socialId)
            .getResultList();
  }
}

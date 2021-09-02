package com.fiap.sociallearn;

import com.fiap.sociallearn.model.*;
import com.fiap.sociallearn.repository.*;
import com.fiap.sociallearn.request.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MatchTest {

  @Autowired
  ContentAreaRepository contentAreaRepository;

  @Autowired
  ProfileRepository profileRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  LearningContentRepository learningContentRepository;

  @Autowired
  UserLearningContentRepository userLearningContentRepository;


  @Before
  public void init() {
    saveProfiles();
    saveUsers();
    // saveContentArea();
    // saveLearningContent();
    // associateUsersWithLearningContent();
  }

  public void saveProfiles() {
    Profile studentProfileEntity = ProfileRequest.builder().name("student").build().toEntity();
    Profile teacherProfileEntity = ProfileRequest.builder().name("teacher").build().toEntity();

    Profile savedStudentProfile = profileRepository.save(studentProfileEntity);
    Profile savedTeacherProfile = profileRepository.save(teacherProfileEntity);

    assertNotNull(savedStudentProfile);
    assertNotNull(savedTeacherProfile);
  }

  private void saveUsers() {
    User studentUserEntity = UserRequest.builder()
        .name("Debora Santos")
        .email("deborasantos@gmail.com")
        .gender(Gender.FEMALE)
        .password("123456")
        .build()
        .toEntity();

    User teacherUserEntity = UserRequest.builder()
        .name("Rodolfo Nascimento")
        .email("rodolfonascimento@gmail.com")
        .gender(Gender.MALE)
        .password("654321")
        .build()
        .toEntity();

    User savedStudent = userRepository.save(studentUserEntity);
    User savedTeacher = userRepository.save(teacherUserEntity);

    assertNotNull(savedStudent);
    assertNotNull(savedTeacher);
  }

  public void saveContentArea() {
    ContentArea contentAreaEntity = ContentAreaRequest.builder().name("Cloud Computing").build().toEntity();
    ContentArea savedContentArea = contentAreaRepository.save(contentAreaEntity);
    assertNotNull(savedContentArea);
  }

  private void saveLearningContent() {
    List<Long> contentAreaIdsList = new ArrayList<>();
    contentAreaIdsList.add(1L);

    LearningContent learningContentEntity = LearningContentRequest.builder()
        .name("Amazon Web Services - EC2")
        .build()
        .toEntity();

    LearningContent savedLearningContent = learningContentRepository.save(learningContentEntity);

    assertNotNull(savedLearningContent);
  }

  private void associateUsersWithLearningContent() {
    UserLearningContent studentAssociationEntity = UserLearningContentRequest.builder().userId("").learningContentId("").build().toEntity();
    UserLearningContent teacherAssociationEntity = UserLearningContentRequest.builder().userId("").learningContentId("").build().toEntity();
    UserLearningContent savedStudentAssociation = userLearningContentRepository.save(studentAssociationEntity);
    UserLearningContent savedTeacherAssociation = userLearningContentRepository.save(teacherAssociationEntity);

    assertNotNull(savedStudentAssociation);
    assertNotNull(savedTeacherAssociation);
  }


  @Test
  public void testFindTeachersByLearningContent() {
    String learningContentId = "";
    String userProfileId = "";
    List<User> teachers = userRepository.findByLearningContentIdAndProfileId(learningContentId, userProfileId);
    User teacher1 = userRepository.findById("").orElse(null);

    assertTrue(teachers.contains(teacher1));
  }
}

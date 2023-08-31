package com.fastcampus.pass.repository.packaze;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class PackageRepositoryTest {
  @Autowired
  private PackageRepository packageRepository;;

  @Test
  public void test_save(){
    PackageEntity packageEntity=new PackageEntity();
    packageEntity.setPackageName("바디 챌린지 PT 12주");
    packageEntity.setPeriod(84);

    packageRepository.save(packageEntity);

    assertNotNull(packageEntity.getPackageSeq());
  }

  @Test
  public void test_findByCreatedAtAfter(){
    LocalDateTime dateTime=LocalDateTime.now().minusMinutes(1);

    PackageEntity packageEntity0=new PackageEntity();
    packageEntity0.setPackageName("학생 전용 3개월");
    packageEntity0.setPeriod(90);
    packageRepository.save(packageEntity0);

    PackageEntity packageEntity1=new PackageEntity();
    packageEntity1.setPackageName("학생 전용 6개월");
    packageEntity1.setPeriod(180);
    packageRepository.save(packageEntity1);

    final List<PackageEntity> packageEntityList=packageRepository.findByCreatedAtAfter(dateTime, PageRequest.of(0,1, Sort.by("packageSeq").descending()));

    assertEquals(1,packageEntityList.size());
    assertEquals(packageEntity1.getPackageSeq(),packageEntityList.get(0).getPackageSeq());
  }

  @Test
  public void test_updateCountAndPeriod(){
    PackageEntity packageEntity=new PackageEntity();
    packageEntity.setPackageName("바디프로필 이벤트 4개월");
    packageEntity.setPeriod(90);
    packageRepository.save(packageEntity);

    int updatedCount=packageRepository.updateCountAndPeriod(packageEntity.getPackageSeq(),30,120);
    final PackageEntity updatedPackageEntity=packageRepository.findById(packageEntity.getPackageSeq()).get();

    assertEquals(1,updatedCount);
    assertEquals(30,updatedPackageEntity.getCount());
    assertEquals(120,updatedPackageEntity.getPeriod());
  }

  @Test
  public void test_delete(){
    PackageEntity packageEntity=new PackageEntity();
    packageEntity.setPackageName("제거할 이용권");
    packageEntity.setCount(1);
    PackageEntity newPackageEntity=packageRepository.save(packageEntity);

    packageRepository.deleteById(newPackageEntity.getPackageSeq());

    assertTrue(packageRepository.findById(newPackageEntity.getPackageSeq()).isEmpty());
  }
}
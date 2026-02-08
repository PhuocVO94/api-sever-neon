package com.huuphuoc.api.profile.repository;


import com.huuphuoc.api.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Component
public interface IProfileRepository extends JpaRepository<Profile, UUID> {

}

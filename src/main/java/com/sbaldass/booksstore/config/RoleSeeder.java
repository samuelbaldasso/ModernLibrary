package com.sbaldass.booksstore.config;

import com.sbaldass.booksstore.models.Role;
import com.sbaldass.booksstore.models.RoleName;
import com.sbaldass.booksstore.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private RoleRepository roleRepository;

    @Autowired
    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        RoleName[] roleNames = new RoleName[]{RoleName.USER, RoleName.ADMIN, RoleName.SUPER_ADMIN};
        Map<RoleName, String> roleDescriptionMap = Map.of(
                RoleName.USER, "Default user role",
                RoleName.ADMIN, "Administrator role",
                RoleName.SUPER_ADMIN, "Super admin role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(roleName);
                roleToCreate
                        .setDescription(roleDescriptionMap.get(roleName));

                roleRepository.save(roleToCreate);
            });
        });
    }
}

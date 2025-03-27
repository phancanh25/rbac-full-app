package com.nexgenus.rbac_app.config;
import com.nexgenus.rbac_app.entity.Permission;
import com.nexgenus.rbac_app.entity.Role;
import com.nexgenus.rbac_app.entity.User;
import com.nexgenus.rbac_app.repository.PermissionRepository;
import com.nexgenus.rbac_app.repository.RoleRepository;
import com.nexgenus.rbac_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create example permissions
        Permission readPermission = permissionRepository.findByName("READ_PRIVILEGES")
                .orElseGet(() -> permissionRepository.save(new Permission("READ_PRIVILEGES")));
        Permission writePermission = permissionRepository.findByName("WRITE_PRIVILEGES")
                .orElseGet(() -> permissionRepository.save(new Permission("WRITE_PRIVILEGES")));
        Set<Permission> u = new HashSet<Permission>();
        u.add(readPermission);
        // Create or fetch the USER role with read permission
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("USER");
                    role.setPermissions(u);
                    role.getPermissions().add(readPermission);
                    return roleRepository.save(role);
                });

        // Create or fetch the ADMIN role with both read and write permissions
        Set<Permission> a = new HashSet<Permission>();
        a.add(writePermission);
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ADMIN");
                    role.setPermissions(a);
                    role.getPermissions().add(readPermission);
                    role.getPermissions().add(writePermission);
                    return roleRepository.save(role);
                });

    }
}

package ro.sd.firstapp.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.sd.firstapp.model.Admin;
import ro.sd.firstapp.repository.AdminRepository;

import java.util.Optional;

/**
 * Service for admin operations
 */
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final static Logger logger = LoggerFactory.getLogger(AdminService.class.getName());

    /**
     * Saves in the database
     *
     * @param administrator instance to be saved
     * @return the saved administrator
     */
    public Admin save(Admin administrator) {
        logger.info("Admin {} saved to database", administrator);
        Admin a = Admin.AdminBuilder()
                .username(administrator.getUsername())
                .firstName(administrator.getFirstName())
                .lastName(administrator.getLastName())
                .password(administrator.getPassword())
                .build();
        return adminRepository.save(a);
    }

    /**
     * Searches for an admin from the database by username
     *
     * @param username of the admin
     * @return the admin
     */
    public Admin findByUsername(String username) {
        logger.info("Retrieve administrator: {} from the database", username);

        Optional<Admin> administrator = adminRepository.findByUsername(username);

        return administrator.orElse(null);
    }
}

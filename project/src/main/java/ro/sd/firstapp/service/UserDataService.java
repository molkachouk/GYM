package ro.sd.firstapp.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.sd.firstapp.model.Admin;
import ro.sd.firstapp.model.Customer;
import ro.sd.firstapp.model.Trainer;
import ro.sd.firstapp.model.UserData;
import ro.sd.firstapp.model.dto.LoginDTO;
import ro.sd.firstapp.model.dto.UserDataDTO;
import ro.sd.firstapp.model.mapper.AdminMapper;
import ro.sd.firstapp.model.mapper.CustomerMapper;
import ro.sd.firstapp.model.mapper.TrainerMapper;
import ro.sd.firstapp.model.mapper.UserDataMapper;
import ro.sd.firstapp.repository.AdminRepository;
import ro.sd.firstapp.repository.CustomerRepository;
import ro.sd.firstapp.repository.TrainerRepository;

import java.util.Optional;

/**
 * Service used to handle the UserData model
 */
@Service
@RequiredArgsConstructor
public class UserDataService {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final TrainerRepository trainerRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserDataService.class.getName());

    /**
     * Retrieves an user account from the database
     *
     * @param username of the account
     * @return the DTO of the retrieved account
     */
    public UserDataDTO findByUsername(String username) {
        logger.info("Find account with username: {}", username);
        Optional<Admin> admin = adminRepository.findByUsername(username);
        Optional<Trainer> trainer = trainerRepository.findByUsername(username);
        if (admin.isPresent()) {
            //delete
            System.out.println(admin);
            return admin.map(value -> AdminMapper.getInstance().convertToDTO(value)).orElse(null);
        }else if(trainer.isPresent()){
            System.out.println(trainer);
            return trainer.map(value -> TrainerMapper.getInstance().convertToDTO(value)).orElse(null);
        } else{
            Optional<Customer> customer = customerRepository.findByUsername(username);
            //delete
            System.out.println(customer);
            return customer.map(value -> CustomerMapper.getInstance().convertToDTO(value)).orElse(null);
        }
    }

    /**
     * Retrieves an user data account by LoginDTO (containing username and password)
     *
     * @param loginDTO containing username and password
     * @return the DTO of the retrieved user data
     */
    public UserDataDTO getUserDataDTO(LoginDTO loginDTO) throws Exception {
        UserDataDTO userDataDTO = this.findByUsername(loginDTO.getUsername());
        if (userDataDTO == null) {
            logger.warn("No account with the username: {} was found", loginDTO.getUsername());
            throw new Exception("No userData found");
        }
        UserData userData = UserDataMapper.getInstance().convertFromDTO(userDataDTO);

        if (loginDTO.getPassword().equals(userData.getPassword())) {
            return userDataDTO;
        }
        throw new Exception("Incorrect Password!");
    }

    /**
     * Logins an account
     *
     * @param loginDTO containing username and password
     * @return the DTO of the logged in account
     */
    public UserDataDTO logIn(LoginDTO loginDTO) throws Exception {
        logger.info("Logged in {}", loginDTO.getUsername());
        return this.getUserDataDTO(loginDTO);
    }
}

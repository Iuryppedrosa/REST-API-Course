package dev.iury.project.service;

import dev.iury.project.Exceptions.ResourceNotFoundException;
import dev.iury.project.Exceptions.RiqueredObjectsNullException;
import dev.iury.project.controllers.PersonController;
import dev.iury.project.dataVO.PersonVO;
import dev.iury.project.dataVO2.PersonVOV2;
import dev.iury.project.mapper.DozerMapper;
import dev.iury.project.mapper.custom.PersonMapper;
import dev.iury.project.model.Person;
import dev.iury.project.repository.PersonRepository;
import dev.iury.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserServices implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Searching for user by username: " + username);
        var user = userRepository.findByUserName(username);
        if (user != null) {
            return user;
        } else{
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
    }
}

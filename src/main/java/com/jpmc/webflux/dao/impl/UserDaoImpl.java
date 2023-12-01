package com.jpmc.webflux.dao.impl;

import com.jpmc.webflux.dao.UserDao;
import com.jpmc.webflux.dto.UserDocumentDto;
import com.jpmc.webflux.dto.UserDto;
import com.jpmc.webflux.entity.UserDocumentEntity;
import com.jpmc.webflux.entity.UserEntity;
import com.jpmc.webflux.mapper.UserEntityMapper;
import com.jpmc.webflux.repository.UserDocumentRepository;
import com.jpmc.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDocumentRepository userDocumentRepository;

    @Override
    public Flux<UserDto> getAllUsers() {
        Flux<UserEntity> allUsers = userRepository.findAll();
        return allUsers.map(userEntityMapper::convertToDto);
    }

    @Override
    public Mono<UserDto> getUser(Long userId) {
        Mono<UserEntity> userEntityMono = userRepository.findById(userId);
        return userEntityMono.map(userEntityMapper::convertToDto);
    }

    @Override
    public Mono<UserDto> createUser(UserDto userDto) {
        UserEntity userEntity = userEntityMapper.convertToEntity(userDto);
        return userRepository.save(userEntity).map(userEntityMapper::convertToDto);
    }

    @Override
    public Mono<UserDocumentDto> createUserDocument(UserDocumentDto userDocumentDto) {
        UserDocumentEntity userDocumentEntity = userEntityMapper.convertToUserDocEntity(userDocumentDto);
        return userDocumentRepository.save(userDocumentEntity).map(userEntityMapper::convertToUserDocumentDto);

    }
}

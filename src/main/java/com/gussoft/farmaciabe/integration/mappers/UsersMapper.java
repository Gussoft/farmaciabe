package com.gussoft.farmaciabe.integration.mappers;

import com.gussoft.farmaciabe.core.models.Users;
import com.gussoft.farmaciabe.integration.transfer.response.UsersResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersMapper {

    @Autowired
    ModelMapper mapper;

    public UsersResponse toUsersResponse(Users user) {
        return mapper.map(user, UsersResponse.class);
    }

    public List<UsersResponse> toUsersListResponse(List<Users> users) {
        return users.stream()
                .map(user -> mapper.map(user, UsersResponse.class))
                .collect(Collectors.toList());
    }

}

package com.wora.ebanking.mapper;

import com.wora.ebanking.common.mapper.BaseMapper;
import com.wora.ebanking.domain.User;
import com.wora.ebanking.dto.request.RegisterationRequestDto;
import com.wora.ebanking.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class)
public interface UserMapper extends BaseMapper<User, RegisterationRequestDto, UserResponseDTO> {
}

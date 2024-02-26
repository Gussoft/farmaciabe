package com.gussoft.farmaciabe.integration.mappers;

import com.gussoft.farmaciabe.core.models.Laboratory;
import com.gussoft.farmaciabe.integration.transfer.response.LaboratoryResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LaboratoryMapper {

    @Autowired
    ModelMapper mapper;

    public LaboratoryResponse toLaboratoryResponse(Laboratory laboratory) {
        return mapper.map(laboratory, LaboratoryResponse.class);
    }

    public List<LaboratoryResponse> toLaboratoryListResponse(List<Laboratory> laboratories) {
        return laboratories.stream()
                .map(laboratory -> mapper.map(laboratory, LaboratoryResponse.class))
                .collect(Collectors.toList());
    }

}

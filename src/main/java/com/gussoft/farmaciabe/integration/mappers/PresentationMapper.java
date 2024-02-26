package com.gussoft.farmaciabe.integration.mappers;

import com.gussoft.farmaciabe.core.models.Presentation;
import com.gussoft.farmaciabe.integration.transfer.response.PresentationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PresentationMapper {

    @Autowired
    ModelMapper mapper;

    public PresentationResponse toPresentationResponse(Presentation presentation) {
        return mapper.map(presentation, PresentationResponse.class);
    }

    public List<PresentationResponse> toPresentationListResponse(List<Presentation> presentations) {
        return presentations.stream()
                .map(presentation -> mapper.map(presentation, PresentationResponse.class))
                .collect(Collectors.toList());
    }

}

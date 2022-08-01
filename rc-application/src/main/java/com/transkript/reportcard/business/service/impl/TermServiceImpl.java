package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.TermMapper;
import com.transkript.reportcard.business.service.i.TermService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
import com.transkript.reportcard.data.entity.Term;
import com.transkript.reportcard.data.repository.TermRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class TermServiceImpl implements TermService {

    private final TermRepository termRepository;
    private final TermMapper termMapper;
    private final String entityName = EntityName.TERM;

    @Override
    public EntityResponse create(TermDto termDto) {
        Term term = termMapper.mapDtoToTerm(termDto);
        term.setId(null);
        term = termRepository.save(term);
        return new EntityResponse(term.getId(), ResponseMessage.SUCCESS.created(entityName), true);
    }

    @Override
    public List<TermDto> getAllDto() {
        return termRepository.findAll().stream().map(termMapper::mapTermToDto).collect(Collectors.toList());
    }

    @Override
    public TermDto getDto(Long id) {
        return termMapper.mapTermToDto(getEntity(id));
    }

    @Override
    public Term getEntity(Long id) {
        return termRepository.findById(id).orElseThrow(() -> new EntityException.NotFound("term", id));
    }

    @Override
    public EntityResponse update(TermDto termDto) {
        Term term = getEntity(termDto.id());
        term.setName(termDto.name());
        term = termRepository.save(term);
        return new EntityResponse(term.getId(), ResponseMessage.SUCCESS.updated(entityName), true);
    }

    @Override
    public void delete(Long id) {
        termRepository.deleteById(id);
    }
}

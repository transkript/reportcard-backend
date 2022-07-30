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
    public EntityResponse addTerm(TermDto termDto) {
        Term term = termMapper.mapDtoToTerm(termDto);
        term.setId(null);
        term = termRepository.save(term);
        return new EntityResponse(term.getId(), ResponseMessage.SUCCESS.created(entityName), true);
    }

    @Override
    public List<TermDto> getTerms() {
        return termRepository.findAll().stream().map(termMapper::mapTermToDto).collect(Collectors.toList());
    }

    @Override
    public TermDto getTerm(Long id) {
        return termMapper.mapTermToDto(getTermEntity(id));
    }

    @Override
    public Term getTermEntity(Long id) {
        return termRepository.findById(id).orElseThrow(() -> new EntityException.NotFound("term", id));
    }

    @Override
    public EntityResponse updateTerm(Long id, TermDto termDto) {
        if (id != null && id.equals(termDto.id()) && termRepository.existsById(id)) {
            Term term = termRepository.findById(id).orElseThrow(() -> new EntityException.NotFound("term", id));

            if (!Objects.equals(term.getName(), termDto.name())) {
                term.setName(termDto.name());
            }
            term = termRepository.save(term);
            return new EntityResponse(term.getId(), ResponseMessage.SUCCESS.updated(entityName), true);
        }
        throw new EntityException.NotFound("term" + id);
    }

    @Override
    public String deleteTerm(Long id) {
        if (id != null && termRepository.existsById(id)) {
            termRepository.deleteById(id);
            return "Successfully deleted term with id: " + id;
        }
        throw new EntityException.NotFound("Term with id: " + id);
    }
}

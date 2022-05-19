package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.business.mapper.TermMapper;
import com.transkript.reportcard.business.service.AcademicYearService;
import com.transkript.reportcard.business.service.TermService;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.Term;
import com.transkript.reportcard.data.repository.TermRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class TermServiceImpl implements TermService {

    TermRepository termRepository;
    TermMapper termMapper;
    AcademicYearService academicYearService;

    @Override
    public String addTerm(TermDto termDto) {
        Term term = termMapper.mapDtoToTerm(termDto);
        AcademicYear academicYear = academicYearService.getAcademicYearById(termDto.getAcademicYearId());
        term.setAcademicYear(academicYear);
        term.setId(null);
        termRepository.save(term);
        return "Successfully added Term with Name: " + term.getName();
    }

    @Override
    public List<TermDto> getTerms() {
        return termRepository.findAll().stream()
                .map(termMapper::mapTermToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TermDto getTerm(Long id) {
        return termMapper.mapTermToDto(
                termRepository.findById(id).orElseThrow(
                        () -> {
                            throw new EntityException.EntityNotFoundException("Term with id: " + id);
                        }
                )
        );
    }

    @Override
    public Term getTermById(Long id) {
        return termRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityException.EntityNotFoundException("Term with id: " + id);
                }
        );
    }

    @Override
    public String updateTerm(Long id, TermDto termDto) {
        if (id != null && termRepository.existsById(id)) {
            Term term = termMapper.mapDtoToTerm(termDto);
            term.setId(id);
            term.setAcademicYear(academicYearService.getAcademicYearById(termDto.getAcademicYearId()));
            termRepository.save(term);
            return "Successfully updated Term with id: " + id;
        }
        throw new EntityException.EntityNotFoundException("Term with id: " + id);
    }

    @Override
    public String deleteTerm(Long id) {
        if (id != null && termRepository.existsById(id)) {
            termRepository.deleteById(id);
            return "Successfully deleted term with id: " + id;
        }
        throw new EntityException.EntityNotFoundException("Term with id: " + id);
    }
}

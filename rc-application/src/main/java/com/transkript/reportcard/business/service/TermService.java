package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.data.entity.Term;

import java.util.List;

public interface TermService {
    String addTerm(TermDto termDto);

    List<TermDto> getTerms();

    TermDto getTerm(Long id);

    Term getTermById(Long id);

    String updateTerm(Long id, TermDto termDto);

    String deleteTerm(Long id);
}

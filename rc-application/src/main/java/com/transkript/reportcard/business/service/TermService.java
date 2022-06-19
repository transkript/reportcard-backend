package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.Term;

import java.util.List;

public interface TermService {
    EntityResponse addTerm(TermDto termDto);

    List<TermDto> getTerms();

    TermDto getTerm(Long id);

    Term getTermEntity(Long id);

    EntityResponse updateTerm(Long id, TermDto termDto);

    String deleteTerm(Long id);
}
